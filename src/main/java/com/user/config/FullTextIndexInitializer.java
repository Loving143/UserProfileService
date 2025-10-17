package com.user.config;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class FullTextIndexInitializer {

    private final JdbcTemplate jdbcTemplate;

    public FullTextIndexInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initFullTextIndex() {
        // Step 1: Add generated column if it doesn't exist
        String alterSql = "DO $$ " +
                          "BEGIN " +
                          "IF NOT EXISTS (SELECT 1 FROM information_schema.columns " +
                          "WHERE table_name='medicines' AND column_name='fulltext_vector') THEN " +
                          "ALTER TABLE medicines " +
                          "ADD COLUMN fulltext_vector tsvector GENERATED ALWAYS AS (" +
                          "setweight(to_tsvector('english', coalesce(name, '')), 'A') || " +
                          "setweight(to_tsvector('english', coalesce(brand_name, '')), 'B') || " +
                          "setweight(to_tsvector('english', coalesce(composition, '')), 'C') || " +
                          "setweight(to_tsvector('english', coalesce(description, '')), 'D')" +
                          ") STORED; " +
                          "END IF; " +
                          "END $$;";
        jdbcTemplate.execute(alterSql);

        // Step 2: Create GIN index if not exists
        String indexSql = "CREATE INDEX IF NOT EXISTS idx_medicine_fulltext_weighted " +
                          "ON medicines USING GIN (fulltext_vector);";
        jdbcTemplate.execute(indexSql);

        System.out.println("✅ Full-text weighted column and index ready.");
    }


}

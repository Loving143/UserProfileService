package com.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.master.entity.MedicineCategory;
import com.user.master.entity.MedicineSubCategory;

public interface MedicineSubCategoryRepository extends JpaRepository<MedicineSubCategory, Long>{

	Optional<MedicineCategory> findBySubCategoryCode(String subCategoryCode);

}

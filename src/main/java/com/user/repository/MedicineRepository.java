package com.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

	Optional<Medicine> findByMedicineCode(String medicineCode);
}

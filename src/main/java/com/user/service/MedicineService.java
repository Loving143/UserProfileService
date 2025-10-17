package com.user.service;

import java.util.List;

import com.user.entity.Medicine;
import com.user.request.MedicineRequest;
import com.user.response.MedicineResponse;

public interface MedicineService {

	MedicineResponse fetchMedicineByMedicineCode(String medicineCode);

	void addMedicine(MedicineRequest req);

	List<MedicineResponse> getAll();

	Medicine getByCodeAndBatch(String medicineCode, String batchNumber);

	Medicine updateByCodeAndBatch(String medicineCode, String batchNumber, MedicineRequest req);

	void deleteByCodeAndBatch(String medicineCode, String batchNumber);
	
	List<Medicine> findByBrandName(String brandName);

    List<Medicine> findByManufacturer(String manufacturer);

    List<Medicine> searchMedicines(String keyword);

    List<Medicine> findAllActiveMedicines();

    List<Medicine> findExpiredMedicines();

	List<Medicine> searchMedicinesFullText(String query);

	List<Medicine> getByBrandName(String brandName);

	List<Medicine> getByManufacturer(String manufacturer);

	List<Medicine> getByName(String name);

	Medicine getByCode(String code);

}

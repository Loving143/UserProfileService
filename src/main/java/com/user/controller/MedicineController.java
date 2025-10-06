package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.MedicineService;

@RestController
public class MedicineController {
	
	@Autowired
	private MedicineService medicineService;
	@GetMapping("fetchMedicine/{medicineCode}")
	public ResponseEntity<?> fetchMedicineByMedicineCode(@PathVariable String medicineCode){
		medicineService.fetchMedicineByMedicineCode(medicineCode);
		return null;
	}

}

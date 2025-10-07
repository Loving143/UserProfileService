package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.user.response.MedicineResponse;
import com.user.response.ResponseMessage;
import com.user.service.MedicineService;

@RestController
public class MedicineController {
	
	@Autowired
	private MedicineService medicineService;
	@GetMapping("fetchMedicine/{medicineCode}")
	public MedicineResponse fetchMedicineByMedicineCode(@PathVariable String medicineCode){
		MedicineResponse res =  medicineService.fetchMedicineByMedicineCode(medicineCode);
		return res;
	}

}

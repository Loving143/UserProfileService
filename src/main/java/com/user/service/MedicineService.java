package com.user.service;

import com.user.response.MedicineResponse;

public interface MedicineService {

	MedicineResponse fetchMedicineByMedicineCode(String medicineCode);

}

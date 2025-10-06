package com.user.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.entity.Medicine;
import com.user.repository.MedicineRepository;
import com.user.response.MedicineResponse;
import com.user.service.MedicineService;

import jakarta.ws.rs.BadRequestException;

@Service
public class MedicineServiceImpl implements MedicineService{

	@Autowired
	private MedicineRepository medicineRepository;
	@Override
	public MedicineResponse fetchMedicineByMedicineCode(String medicineCode) {
		Medicine medicine = medicineRepository.findByMedicineCode(medicineCode).
				orElseThrow(()-> new BadRequestException("Medicine with MedicineCode does not exists!!"));
		MedicineResponse res= new MedicineResponse(medicine);
		return res;
	}

}

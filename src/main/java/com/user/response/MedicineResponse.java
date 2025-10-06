package com.user.response;

import com.user.entity.Medicine;

public class MedicineResponse {

	 private Long id;
	 private String name;
	 private String dosage;
	 private String frequency;
	 private String notes;
	 
	public MedicineResponse(Medicine med) {
		this.id = med.getId();
		this.name = med.getName();
		this.dosage = med.getDosage();
		this.frequency = med.getFrequency();
		this.notes = med.getNotes();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	 
	 
}

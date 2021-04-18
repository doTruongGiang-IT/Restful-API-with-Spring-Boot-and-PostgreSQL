package com.example.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springboot.entity.SmartPhone;
import com.example.springboot.repository.SmartPhoneRepository;
import com.example.springboot.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v1/")
public class SmartPhoneController {
	@Autowired
	SmartPhoneRepository smartPhoneRepository;
	
	// Get all smart phone
	@GetMapping("smartphones")
	public List<SmartPhone> getAllSmartPhones() {
		return this.smartPhoneRepository.findAll();
	};
	
	// Get smart phone by id
	@GetMapping("smartphones/{id}")
	public ResponseEntity<SmartPhone> getSmartPhoneById(@PathVariable(value = "id") long smartPhoneId) {
		SmartPhone smartPhone = smartPhoneRepository.findById(smartPhoneId).orElseThrow(() -> new ResourceNotFoundException("Smart phone not found"));
		return ResponseEntity.ok().body(smartPhone);
	};
	
	// Create new smart phone
	@PostMapping("smartphones")
	public SmartPhone createSmartPhone(@Valid @RequestBody SmartPhone smartPhone) {
		return smartPhoneRepository.save(smartPhone);
	};
	
	// Update smart phone by id
	@PutMapping("smartphones/{id}")
	public ResponseEntity<SmartPhone> updateSmartPhone(@PathVariable(value = "id") long smartPhoneId, @Valid @RequestBody SmartPhone updateSmartPhone) {
		SmartPhone smartPhone = smartPhoneRepository.findById(smartPhoneId).orElseThrow(() -> new ResourceNotFoundException("Smart phone not found"));
		smartPhone.setName(updateSmartPhone.getName());
		smartPhone.setPrice(updateSmartPhone.getPrice());
		smartPhone.setStatus(updateSmartPhone.isStatus());
		SmartPhone editSmartPhone = smartPhoneRepository.save(smartPhone);
		return ResponseEntity.ok().body(editSmartPhone);
	};
	
	// Delete smart phone by id
	@DeleteMapping("smartphones/{id}")
	public Map<String, Boolean> deleteSmartPhone(@PathVariable(value = "id") long smartPhoneId) {
		SmartPhone smartPhone = smartPhoneRepository.findById(smartPhoneId).orElseThrow(() -> new ResourceNotFoundException("Smart phone not found"));
		this.smartPhoneRepository.delete(smartPhone);
		Map<String, Boolean> respone = new HashMap<>();
		respone.put("deleted: ", Boolean.TRUE);
		return respone;
	};
}

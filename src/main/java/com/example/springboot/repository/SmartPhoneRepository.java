package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.springboot.entity.SmartPhone;

@Repository
public interface SmartPhoneRepository extends JpaRepository<SmartPhone, Long> {

}

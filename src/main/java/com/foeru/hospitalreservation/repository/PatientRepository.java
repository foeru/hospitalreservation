package com.foeru.hospitalreservation.repository;

import com.foeru.hospitalreservation.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
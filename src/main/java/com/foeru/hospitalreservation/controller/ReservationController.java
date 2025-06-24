package com.foeru.hospitalreservation.controller;

import com.foeru.hospitalreservation.entity.Patient;
import com.foeru.hospitalreservation.repository.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.foeru.hospitalreservation.dto.ResponseMessage;

@RestController
@RequestMapping("/api/patient")
public class ReservationController {

    private final PatientRepository patientRepository;

    public ReservationController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // 1. 환자 전체 조회 API
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // 2. 특정 환자 조회 API (ID로 조회)
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. 환자 추가 API
    @PostMapping
    public ResponseEntity<ResponseMessage> addPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientRepository.save(patient); // 저장된 엔티티
        ResponseMessage response = new ResponseMessage(
            "예약이 완료되었습니다!", // 메시지 추가
            savedPatient // 저장된 환자 데이터
        );
        return ResponseEntity.ok(response); // JSON 응답으로 반환
    }

    // 4. 환자 수정 API
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient newPatientData) {
        return patientRepository.findById(id)
            .map(existingPatient -> {
                // 기존 데이터를 새로운 데이터로 업데이트
                existingPatient.setName(newPatientData.getName());
                existingPatient.setGender(newPatientData.getGender());
                existingPatient.setBirthDate(newPatientData.getBirthDate());
                existingPatient.setContact(newPatientData.getContact());
                // 업데이트 후 저장
                Patient savedPatient = patientRepository.save(existingPatient);
                return ResponseEntity.ok(savedPatient);
            })
            .orElse(ResponseEntity.notFound().build()); // id를 찾지 못하면 404 반환
    }

    // 5. 환자 삭제 API
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable Long id) {
        return patientRepository.findById(id)
            .map(patient -> {
                patientRepository.delete(patient);
                return ResponseEntity.<Void>noContent().build(); // 명시적으로 제네릭 타입 지정
            })
            .orElse(ResponseEntity.notFound().build());

    }
}
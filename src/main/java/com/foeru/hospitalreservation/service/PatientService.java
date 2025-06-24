package com.foeru.hospitalreservation.service;

import com.foeru.hospitalreservation.entity.Patient;
import com.foeru.hospitalreservation.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

//    public Patient findById(Long id) {
//        Optional<Patient> patient = patientRepository.findById(id);
//        return patient.orElse(null);
//    }
}


/*
병원 선택(병원 이름) > 예약 가능 시간 확인 (시간 선택) > 선택 > 환자 정보 입력 (이름, 생년월일, 성별, 연락처) > 예약 완료
예약 정보 저장(병원 이름, 예약 시간, 환자 정보)

 */
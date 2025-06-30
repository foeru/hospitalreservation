package com.foeru.hospitalreservation.mapper;

import com.foeru.hospitalreservation.dto.ReservationRequest;
import com.foeru.hospitalreservation.entity.Hospital;
import com.foeru.hospitalreservation.entity.Patient;
import com.foeru.hospitalreservation.entity.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReservationMapper {
    public Reservation toEntity(ReservationRequest request, Patient patient, Hospital hospital) {
        if (patient == null) {
            throw new IllegalArgumentException("유효한 Patient 객체가 필요합니다.");
        }
        if (hospital == null) {
            throw new IllegalArgumentException("유효한 Hospital 객체가 필요합니다.");
        }
        if (request.getReservationTime() == null || request.getReservationTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Reservation time은 미래 시점이어야 합니다.");
        }

        Reservation reservation = new Reservation();
        reservation.setReservationTime(request.getReservationTime());
        reservation.setPatient(patient);
        reservation.setHospital(hospital);
        reservation.setStatus("예약완료");
        return reservation;
    }
}
package com.foeru.hospitalreservation.mapper;

import com.foeru.hospitalreservation.dto.ReservationRequest;
import com.foeru.hospitalreservation.entity.Patient;
import com.foeru.hospitalreservation.entity.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    public Reservation toEntity(ReservationRequest request, Patient patient) {
        Reservation reservation = new Reservation();
        reservation.setReservationDate(request.getReservationTime());
        reservation.setPatient(patient);  // Patient는 서비스에서 조회한 결과를 사용
        return reservation;
    }
}
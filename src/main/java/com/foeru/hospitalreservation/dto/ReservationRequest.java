package com.foeru.hospitalreservation.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ReservationRequest {

    @NotNull(message = "Patient ID는 필수입니다.")
    private Long patientId;

    @NotNull(message = "Reservation Time은 필수입니다.")
    @Future(message = "Reservation Time은 미래 시점이어야 합니다.")
    private LocalDateTime reservationTime;

    // Getters 추가
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }
}
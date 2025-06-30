package com.foeru.hospitalreservation.controller;

import com.foeru.hospitalreservation.entity.Hospital;
import com.foeru.hospitalreservation.entity.Reservation;
import com.foeru.hospitalreservation.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // 특정 병원의 특정 날짜에 이미 예약된 시간 반환
    @GetMapping
    public ResponseEntity<List<String>> getReservedTimes(
            @RequestParam Long hospitalId,
            @RequestParam String date) {

        List<String> reservedTimes = reservationService.getReservedTimes(hospitalId, date);
        return ResponseEntity.ok(reservedTimes);
    }
}
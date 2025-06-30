package com.foeru.hospitalreservation.service;

import com.foeru.hospitalreservation.entity.Reservation;
import com.foeru.hospitalreservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // 특정 병원의 특정 날짜에 예약된 시간 가져오기
    public List<String> getReservedTimes(Long hospitalId, String date) {
        LocalDate localDate = LocalDate.parse(date);
        
        // 해당 날짜의 시작과 끝 시간 계산
        LocalDateTime startOfDay = localDate.atStartOfDay();
        LocalDateTime endOfDay = localDate.atTime(LocalTime.MAX);

        // 예약된 시간 조회
        List<Reservation> reservations = reservationRepository
                .findAllByHospitalIdAndReservationTimeBetween(hospitalId, startOfDay, endOfDay);

        // 예약된 시간대를 문자열 형식 목록으로 반환
        return reservations.stream()
                .map(reservation -> reservation.getReservationTime().toLocalTime().toString())
                .collect(Collectors.toList());
    }
}
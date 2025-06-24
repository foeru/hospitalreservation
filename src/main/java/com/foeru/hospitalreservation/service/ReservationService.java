package com.foeru.hospitalreservation.service;

import com.foeru.hospitalreservation.dto.ReservationResponse;
import com.foeru.hospitalreservation.entity.Reservation;
import com.foeru.hospitalreservation.repository.ReservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // 1. 모든 예약 조회
    public Page<Reservation> getReservations(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }

    // 2. 예약 저장
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // 3. 특정 예약 ID로 ReservationResponse 반환
    public ReservationResponse getReservationResponseById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("예약 정보를 찾을 수 없습니다. ID: " + id));


        // Reservation -> ReservationResponse로 변환
        return new ReservationResponse(
                reservation.getId(),
                "success",
                "예약 조회 성공"
        );
    }
}
package com.foeru.hospitalreservation.repository;

import com.foeru.hospitalreservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // 병원 ID로 예약된 모든 항목 조회
    List<Reservation> findAllByHospitalId(Long hospitalId);

    // 특정 병원의 특정 시간에 이미 예약된 항목이 있는지 확인
    boolean existsByHospitalIdAndReservationTime(Long hospitalId, java.time.LocalDateTime reservationTime);

    List<Reservation> findAllByHospitalIdAndReservationTimeBetween(Long hospitalId, LocalDateTime start, LocalDateTime end);
}
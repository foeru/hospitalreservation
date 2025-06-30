package com.foeru.hospitalreservation.repository;

import com.foeru.hospitalreservation.entity.HospitalSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalScheduleRepository extends JpaRepository<HospitalSchedule, Long> {
    List<HospitalSchedule> findByHospitalIdAndReservedFalse(Long hospitalId);
}
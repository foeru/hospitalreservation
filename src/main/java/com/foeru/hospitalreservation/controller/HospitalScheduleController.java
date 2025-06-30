package com.foeru.hospitalreservation.controller;

import com.foeru.hospitalreservation.entity.HospitalSchedule;
import com.foeru.hospitalreservation.repository.HospitalScheduleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals/{hospitalId}/schedules")
public class HospitalScheduleController {

    private final HospitalScheduleRepository hospitalScheduleRepository;

    public HospitalScheduleController(HospitalScheduleRepository hospitalScheduleRepository) {
        this.hospitalScheduleRepository = hospitalScheduleRepository;
    }

    @GetMapping
    public List<HospitalSchedule> getAvailableSchedules(@PathVariable Long hospitalId) {
        return hospitalScheduleRepository.findByHospitalIdAndReservedFalse(hospitalId);
    }
}
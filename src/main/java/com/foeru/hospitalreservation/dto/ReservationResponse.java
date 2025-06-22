package com.foeru.hospitalreservation.dto;

public class ReservationResponse {
    private Long id;
    private String status;
    private String message;
    private PatientInfo patient;

    // 필드를 포함한 생성자
    public ReservationResponse(Long id, String status, String message, PatientInfo patient) {
        this.id = id;
        this.status = status;
        this.message = message;
        this.patient = patient;
    }

    // Overloaded constructor (환자 정보 없이 생성 가능)
    public ReservationResponse(Long id, String status, String message) {
        this.id = id;
        this.status = status;
        this.message = message;
        this.patient = null; // 환자 정보가 null로 설정됩니다.
    }

    // Getter와 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PatientInfo getPatient() {
        return patient;
    }

    public void setPatient(PatientInfo patient) {
        this.patient = patient;
    }
}
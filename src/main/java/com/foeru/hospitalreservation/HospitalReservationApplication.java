package com.foeru.hospitalreservation;

import org.apache.catalina.Host;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.zip.CheckedOutputStream;

@SpringBootApplication
public class HospitalReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalReservationApplication.class, args);

    }
}
package com.example.payload;


import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HospitalDto {
    private long patientId;
    @Size(min = 2, max = 50, message = "Should be more than 2 characters")
    private String patientName;
    @Size(min = 10, max = 10, message = "Number Should be 10 digits")
    private String patientNumber;
    private String patientAddress;
    private String patientCity;
    private String patientDoctorName;
    private String message;
}

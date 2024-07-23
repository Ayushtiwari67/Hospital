package com.example.service;

import com.example.payload.HospitalDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HospitalService {
    public HospitalDto add(HospitalDto hospitalDto);
    public void delete(long patientId);
    public HospitalDto update(long patientId, HospitalDto hospitalDto);
    List<HospitalDto> all();
    public HospitalDto find(long patientId);
    List<HospitalDto> findAll(int pageNo, int pageSize, String sortBy, String sortDir);
}

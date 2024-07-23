package com.example.service;

import com.example.entity.Hospital;
import com.example.exception.ResourceNotFound;
import com.example.payload.HospitalDto;
import com.example.repository.HospitalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HospitalServiceImpl implements HospitalService{
    private HospitalRepository hospitalRepository;

    public HospitalServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }


    @Override
    public HospitalDto add(HospitalDto hospitalDto) {
        Hospital entity = mapToEntity(hospitalDto);
        Hospital save = hospitalRepository.save(entity);
        HospitalDto dto = mapToDto(save);
        dto.setMessage("Patient Information Saved SuccessFully");
        return dto;
    }

    Hospital mapToEntity(HospitalDto dto){
        Hospital entity = new Hospital();
        entity.setPatientName(dto.getPatientName());
        entity.setPatientNumber(dto.getPatientNumber());
        entity.setPatientAddress(dto.getPatientAddress());
        entity.setPatientCity(dto.getPatientCity());
        entity.setPatientDoctorName(dto.getPatientDoctorName());
        return entity;
    }

    HospitalDto mapToDto(Hospital hospital){
        HospitalDto dto = new HospitalDto();
        dto.setPatientId(hospital.getPatientId());
        dto.setPatientName(hospital.getPatientName());
        dto.setPatientNumber(hospital.getPatientNumber());
        dto.setPatientAddress(hospital.getPatientAddress());
        dto.setPatientCity(hospital.getPatientCity());
        dto.setPatientDoctorName(hospital.getPatientDoctorName());
        return dto;
    }

    @Override
    public void delete(long patientId) {
        hospitalRepository.deleteById(patientId);
    }

    @Override
    public HospitalDto update(long patientId, HospitalDto hospitalDto) {
        Optional<Hospital> byId = hospitalRepository.findById(patientId);
        Hospital hospital = byId.get();
        hospital.setPatientName(hospitalDto.getPatientName());
        hospital.setPatientNumber(hospitalDto.getPatientNumber());
        hospital.setPatientAddress(hospitalDto.getPatientAddress());
        hospital.setPatientCity(hospitalDto.getPatientCity());
        hospital.setPatientDoctorName(hospitalDto.getPatientDoctorName());
        Hospital update = hospitalRepository.save(hospital);
        HospitalDto dto = mapToDto(update);
        dto.setMessage("Update SuccessFully");
        return dto;

    }

    @Override
    public List<HospitalDto> all() {

        List<Hospital> all = hospitalRepository.findAll();
        List<HospitalDto> collect = all.stream().map(r->mapToDto(r)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public HospitalDto find(long patientId) {
        Hospital hospital = hospitalRepository.findById(patientId).orElseThrow(() -> new ResourceNotFound("Patient Not found With id: " + patientId));
        HospitalDto dto = mapToDto(hospital);
        return dto;
    }

    @Override
    public List<HospitalDto> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(Sort.Direction.ASC, sortBy) : Sort.by(Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Hospital> all = hospitalRepository.findAll(pageable);
        List<Hospital> hospitals = all.getContent();
        List<HospitalDto> dtos = hospitals.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return dtos;

    }
}


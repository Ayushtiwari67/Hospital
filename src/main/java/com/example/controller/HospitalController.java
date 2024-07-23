package com.example.controller;

import com.example.payload.HospitalDto;
import com.example.service.HospitalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {
    private HospitalService hospitalService;

    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @PostMapping
    public ResponseEntity<?> addPatient(
            @Valid @RequestBody HospitalDto hospitalDto,
            BindingResult result){

        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.OK);
        }
        HospitalDto add = hospitalService.add(hospitalDto);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deletePatient(@RequestParam long patientId){
        hospitalService.delete(patientId);
        return new ResponseEntity<>("Delete SuccessFully", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<HospitalDto> updatePatient(@RequestParam long patientId, @RequestBody HospitalDto hospitalDto){
        HospitalDto update = hospitalService.update(patientId, hospitalDto);
        return new ResponseEntity<>(update,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<HospitalDto>> allPatient(){
        List<HospitalDto> all = hospitalService.all();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<HospitalDto> findPatient(@RequestParam long patientId){
        HospitalDto dto = hospitalService.find(patientId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<HospitalDto>> getAll(
            @RequestParam(name = "pageNo", defaultValue = "0",required = false)int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "5", required = false)int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "patientName", required = false)String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "patientName", required = false)String sortDir
    ){
        List<HospitalDto> all = hospitalService.findAll(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
}

package br.cliniconect.medical.rest.controller;

import br.cliniconect.medical.domain.model.patient.Patient;
import br.cliniconect.medical.rest.dto.patient.CreatePatientDTO;
import br.cliniconect.medical.rest.dto.patient.ResponsePatientDTO;
import br.cliniconect.medical.rest.dto.patient.UpdatePatientDTO;
import br.cliniconect.medical.service.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;
    private final ModelMapper mapper;

    @GetMapping()
    public ResponseEntity<Page<ResponsePatientDTO>> getAllPatient(Pageable pageable) {
        return ResponseEntity.ok(patientService.findAllPatient(pageable)
                .map(patient -> mapper.map(patient, ResponsePatientDTO.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePatientDTO> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.map(patientService.findPatientById(id), ResponsePatientDTO.class));
    }

    @PostMapping()
    public ResponseEntity<ResponsePatientDTO> savePatient(@RequestBody @Valid CreatePatientDTO patientRequest) {
        return ResponseEntity.ok(mapper.map(patientService.savePatient(mapper.map(patientRequest, Patient.class)), ResponsePatientDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePatientDTO> updatePatient(@PathVariable Long id, @RequestBody @Valid UpdatePatientDTO patientRequest) {
        return ResponseEntity.ok(mapper.map(patientService.updatePatient(id, mapper.map(patientRequest, Patient.class)), ResponsePatientDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponsePatientDTO> deletePatient(@PathVariable Long id) {
        patientService.deletePatientById(id);
        return ResponseEntity.noContent().build();
    }
}
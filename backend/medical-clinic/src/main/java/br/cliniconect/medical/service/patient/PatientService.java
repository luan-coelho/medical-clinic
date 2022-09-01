package br.cliniconect.medical.service.patient;

import br.cliniconect.medical.domain.model.patient.Patient;
import br.cliniconect.medical.domain.repository.PatientRepository;
import br.cliniconect.medical.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper mapper;

    public Page<Patient> findAllPatient(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    public Patient findPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new BadRequestException("Patient not found by id"));
    }

    public Patient savePatient(Patient entity) {
        if (patientRepository.existsByCpf(entity.getCpf().trim()))
            throw new BadRequestException("There is already a patient registered with this CPF");
        if (patientRepository.existsByEmailIgnoreCase(entity.getEmail().trim()))
            throw new BadRequestException("There is already a patient registered with this Email");
        return patientRepository.save(entity);
    }

    public Patient updatePatient(Long id, Patient entity) {
        Optional<Patient> patient = patientRepository.findById(id);
        patient.orElseThrow(() -> new BadRequestException("Patient not found by id"));

        if (entity.getCpf() != null && !entity.getCpf().trim().equalsIgnoreCase(patient.get().getCpf().trim()))
            if (patientRepository.existsByCpf(entity.getCpf().trim()))
                throw new BadRequestException("There is already a patient registered with this CPF");
        if (entity.getEmail() != null && !entity.getEmail().trim().equalsIgnoreCase(patient.get().getEmail().trim())) {
            if (patientRepository.existsByEmailIgnoreCase(entity.getEmail().trim()))
                throw new BadRequestException("There is already a patient registered with this Email");
        }

        entity.setId(id);
        mapper.map(entity, patient.get());

        return patientRepository.save(patient.get());
    }

    public void deletePatientById(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new BadRequestException("Patient not found by Id");
        }
        patientRepository.deleteById(id);
    }
}

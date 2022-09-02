package br.cliniconect.medical.domain.repository;

import br.cliniconect.medical.domain.model.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

    boolean existsByCpf(String cpf);

    boolean existsByEmailIgnoreCase(String email);
}


package br.cliniconect.medical.domain.repository;

import br.cliniconect.medical.domain.model.patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByCpf(String cpf);

    Optional<Patient> findByEmailIgnoreCase(String email);

    boolean existsByCpf(String cpf);

    boolean existsByEmailIgnoreCase(String email);
}


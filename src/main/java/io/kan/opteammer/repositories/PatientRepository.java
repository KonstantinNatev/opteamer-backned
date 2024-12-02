package io.kan.opteammer.repositories;

import io.kan.opteammer.model.Inventory;
import io.kan.opteammer.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    Optional<Patient> findById(long id);
}

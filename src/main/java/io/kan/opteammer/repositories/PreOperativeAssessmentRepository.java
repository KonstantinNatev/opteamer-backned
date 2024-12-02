package io.kan.opteammer.repositories;

import io.kan.opteammer.model.Inventory;
import io.kan.opteammer.model.PreOperativeAssessment;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PreOperativeAssessmentRepository extends CrudRepository<PreOperativeAssessment, String> {
    Optional<PreOperativeAssessment> findByName(String name);
}

package io.kan.opteammer.repositories;

import io.kan.opteammer.model.Assessment;
import io.kan.opteammer.model.embeddedids.AssessmentId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AssessmentRepository extends CrudRepository<Assessment, AssessmentId> {
    Optional<Assessment> findById(AssessmentId assessmentId);
}


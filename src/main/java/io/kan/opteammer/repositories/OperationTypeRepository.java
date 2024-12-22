package io.kan.opteammer.repositories;

import io.kan.opteammer.model.OperationType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OperationTypeRepository extends CrudRepository<OperationType, String> {
    Optional<OperationType> findByName(String name);
}

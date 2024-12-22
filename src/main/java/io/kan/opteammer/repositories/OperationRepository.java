package io.kan.opteammer.repositories;

import io.kan.opteammer.model.Operation;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface OperationRepository extends CrudRepository<Operation, Long> {
    Optional<Operation> findById(Long id);
}

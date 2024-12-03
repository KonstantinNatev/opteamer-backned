package io.kan.opteammer.repositories;

import io.kan.opteammer.model.OperationProvider;
import io.kan.opteammer.model.Patient;
import io.kan.opteammer.model.enums.OperationProviderType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OperationProviderRepository extends CrudRepository<OperationProvider, Long> {
    Optional<OperationProvider> findByType(OperationProviderType id);
}

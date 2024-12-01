package io.kan.opteammer.repositories;

import io.kan.opteammer.model.Asset;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AssetRepository extends CrudRepository<Asset, Long> {
    Optional<Asset> findById(long id);
}

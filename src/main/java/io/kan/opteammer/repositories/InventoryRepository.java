package io.kan.opteammer.repositories;

import io.kan.opteammer.model.Inventory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {
    Optional<Inventory> findById(long id);
}

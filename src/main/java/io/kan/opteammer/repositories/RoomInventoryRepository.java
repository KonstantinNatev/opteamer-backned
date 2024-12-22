package io.kan.opteammer.repositories;

import io.kan.opteammer.model.RoomInventory;
import io.kan.opteammer.model.embeddedids.RoomInventoryId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoomInventoryRepository extends CrudRepository<RoomInventory, RoomInventoryId> {
    Optional<RoomInventory> findById(RoomInventoryId id);
}

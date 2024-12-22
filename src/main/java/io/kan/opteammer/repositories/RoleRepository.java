package io.kan.opteammer.repositories;

import io.kan.opteammer.model.Role;
import io.kan.opteammer.model.enums.ERole;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}

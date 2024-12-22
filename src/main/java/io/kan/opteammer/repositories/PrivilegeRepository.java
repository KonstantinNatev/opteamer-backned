package io.kan.opteammer.repositories;

import io.kan.opteammer.model.Privilege;
import io.kan.opteammer.model.enums.EPrivilege;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PrivilegeRepository  extends CrudRepository<Privilege, Long> {
    Optional<Privilege> findByName(EPrivilege name);
}

package io.kan.opteammer.repositories;

import io.kan.opteammer.model.TeamMember;
import io.kan.opteammer.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}


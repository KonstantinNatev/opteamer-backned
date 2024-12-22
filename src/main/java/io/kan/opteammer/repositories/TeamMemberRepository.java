package io.kan.opteammer.repositories;

import io.kan.opteammer.model.TeamMember;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TeamMemberRepository extends CrudRepository<TeamMember, Long> {
    Optional<TeamMember> findById(long id);
}

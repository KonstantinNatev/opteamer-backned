package io.kan.opteammer.mapper;

import io.kan.opteammer.dto.TeamMemberDTO;
import io.kan.opteammer.model.TeamMember;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

@Mapper(uses = {OperationProviderMapper.class})
public interface TeamMemberMapper {

    TeamMemberMapper INSTANCE = Mappers.getMapper(TeamMemberMapper.class);

    @Mapping(source = "operationProvider", target = "operationProvider")
    TeamMemberDTO toTeamMemberDTO(TeamMember teamMember);

    TeamMember toTeamMember(TeamMemberDTO teamMemberDTO);

}


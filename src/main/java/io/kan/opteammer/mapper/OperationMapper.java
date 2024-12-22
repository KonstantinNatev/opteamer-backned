package io.kan.opteammer.mapper;

import io.kan.opteammer.dto.OperationDTO;
import io.kan.opteammer.dto.OperationTypeDTO;
import io.kan.opteammer.model.Operation;
import io.kan.opteammer.model.OperationType;
import io.kan.opteammer.model.TeamMember;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

@Mapper(uses = {io.kan.opteammer.mapper.OperationTypeMapper.class, OperationRoomMapper.class, PatientMapper.class, TeamMemberMapper.class})
public interface OperationMapper {

    OperationMapper INSTANCE = Mappers.getMapper(OperationMapper.class);

    @Mapping(source = "operationType", target = "operationType")
    @Mapping(source = "operationRoom", target = "operationRoom")
    @Mapping(source = "patient", target = "patient")
    @Mapping(source = "teamMembers", target = "teamMembers")
    OperationDTO toOperationDTO(Operation operation);

    Operation toOperation(OperationDTO operationDTO);

}


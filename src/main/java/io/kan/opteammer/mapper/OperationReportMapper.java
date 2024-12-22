package io.kan.opteammer.mapper;

import io.kan.opteammer.dto.OperationDTO;
import io.kan.opteammer.dto.OperationReportDTO;
import io.kan.opteammer.model.Operation;
import io.kan.opteammer.model.OperationReport;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

@Mapper(uses = {Operation.class, TeamMemberMapper.class})
public interface OperationReportMapper {

    OperationReportMapper INSTANCE = Mappers.getMapper(OperationReportMapper.class);

    @Mapping(source = "operation", target = "operation")
    @Mapping(source = "teamMember", target = "teamMember")
    OperationReportDTO toOperationReportDTO(OperationReport operationReport);

    Operation toOperation(OperationDTO operationDTO);

}


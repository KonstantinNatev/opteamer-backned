package io.kan.opteammer.mapper;

import io.kan.opteammer.dto.OperationTypeDTO;
import io.kan.opteammer.model.OperationType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

@Mapper(uses = {AssetMapper.class, OperationTypeMapper.class, PreOperativeAssessmentMapper.class})
public interface OperationTypeMapper {

    OperationTypeMapper INSTANCE = Mappers.getMapper(OperationTypeMapper.class);

    @Mapping(source = "assets", target = "assets")
    @Mapping(source = "operationProviders", target = "operationProviders")
    @Mapping(source = "preOperativeAssessments", target = "preOperativeAssessments")
    OperationTypeDTO toOperationTypeDTO(OperationType operationType);

    OperationType toOperationType(OperationTypeDTO operationTypeDTO);

}


package io.kan.opteammer.mapper;

import io.kan.opteammer.dto.OperationProviderDTO;
import io.kan.opteammer.model.OperationProvider;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperationProviderMapper {

    OperationProviderMapper INSTANCE = Mappers.getMapper(OperationProviderMapper.class);

    OperationProviderDTO toOperationProviderDTO(OperationProvider operationProvider);

    OperationProvider toOperationProvider(OperationProviderDTO operationProviderDTO);

}


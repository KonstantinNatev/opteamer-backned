package io.kan.opteammer.mapper;

import io.kan.opteammer.dto.OperationRoomDTO;
import io.kan.opteammer.model.OperationRoom;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OperationRoomMapper {

    OperationRoomMapper INSTANCE = Mappers.getMapper(OperationRoomMapper.class);

    OperationRoomDTO toOperationRoomDTO(OperationRoom operationRoom);

    OperationRoom toOperationRoom(OperationRoomDTO operationRoomDTO);

}


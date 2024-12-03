package io.kan.opteammer.services;

import io.kan.opteammer.dto.OperationProviderDTO;
import io.kan.opteammer.dto.OperationRoomDTO;
import io.kan.opteammer.model.OperationProvider;
import io.kan.opteammer.model.OperationRoom;
import io.kan.opteammer.model.enums.OperationProviderType;
import io.kan.opteammer.repositories.OperationProviderRepository;
import io.kan.opteammer.repositories.OperationRoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OperationRoomService {

    OperationRoomRepository operationRoomRepository;

    private OperationRoom mapDTOToEntity(OperationRoomDTO operationRoomDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(operationRoomDTO, OperationRoom.class);
    }

    private OperationRoomDTO mapEntityToDTO(OperationRoom operationRoom) {
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(operationRoom, OperationRoomDTO.class);
    }

    @Autowired
    public OperationRoomService(OperationRoomRepository operationRoomRepository) {
        this.operationRoomRepository = operationRoomRepository;
    }

    public Optional<OperationRoomDTO> getOperationRoomById(Long id) {
        try {
            OperationRoom operationRoom = operationRoomRepository.findById(id).orElseThrow();
            return Optional.of(mapEntityToDTO(operationRoom));
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public List<OperationRoomDTO>  getAllOperationRooms() {
        List<OperationRoomDTO> list = new ArrayList<>();
        Iterable<OperationRoom> allOperationRooms = operationRoomRepository.findAll();
        allOperationRooms.forEach(operationRoom -> list.add(mapEntityToDTO(operationRoom)));
        return list;
    }

    public OperationRoomDTO createOperationRoom(OperationRoomDTO operationRoomDTO) {
        OperationRoom operationRoom = mapDTOToEntity(operationRoomDTO);
        operationRoomRepository.save(operationRoom);

        return mapEntityToDTO(operationRoom);
    }

    public Optional<OperationRoomDTO> updateOperationRoom(Long id, OperationRoomDTO operationRoomDTO) {
        return operationRoomRepository.findById(id).map(operationRoom -> {

            operationRoom.setRoomNr(operationRoomDTO.getRoomNr());
            operationRoom.setBuildingBlock(operationRoomDTO.getBuildingBlock());
            operationRoom.setFloor(operationRoomDTO.getFloor());
            operationRoom.setType(operationRoomDTO.getType());
            operationRoom.setState(operationRoomDTO.getState());

            operationRoomRepository.save(operationRoom);
            return mapEntityToDTO(operationRoom);
        });
    }

    public boolean deleteOperationRoom(Long id) {
        return operationRoomRepository.findById(id).map(operationRoom -> {
            operationRoomRepository.delete(operationRoom);
            return true;
        }).orElse(false);
    }
}

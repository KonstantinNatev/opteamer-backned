package io.kan.opteammer.services;

import io.kan.opteammer.dto.AssetDTO;
import io.kan.opteammer.dto.RoomInventoryDTO;
import io.kan.opteammer.dto.OperationRoomDTO;
import io.kan.opteammer.dto.RoomInventoryDTO;
import io.kan.opteammer.model.Asset;
import io.kan.opteammer.model.OperationRoom;
import io.kan.opteammer.model.RoomInventory;
import io.kan.opteammer.model.embeddedids.RoomInventoryId;
import io.kan.opteammer.repositories.AssetRepository;
import io.kan.opteammer.repositories.OperationRoomRepository;
import io.kan.opteammer.repositories.RoomInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RoomInventoryService {
    RoomInventoryRepository roomInventoryRepository;
    AssetRepository assetRepository;
    OperationRoomRepository operationRoomRepository;

    private static RoomInventoryDTO getRoomInventoryDTO(RoomInventory roomInventory, Asset asset, OperationRoom operationRoom) {
        return new RoomInventoryDTO(
            roomInventory.getAsset().getId(),
            roomInventory.getOperationRoom().getId(),
            new AssetDTO(asset.getId(), asset.getType(), asset.getName()),
            new OperationRoomDTO(   operationRoom.getId(),
                    operationRoom.getRoomNr(),
                    operationRoom.getBuildingBlock(),
                    operationRoom.getFloor(),
                    operationRoom.getType(),
                    operationRoom.getState()),
            roomInventory.getCount());
    }

    @Autowired
    public RoomInventoryService(RoomInventoryRepository roomInventoryRepository, AssetRepository assetRepository, OperationRoomRepository operationRoomRepository) {
        this.roomInventoryRepository = roomInventoryRepository;
        this.assetRepository = assetRepository;
        this.operationRoomRepository = operationRoomRepository;
    }

    public List<RoomInventoryDTO> getAllRoomInventories() {
        List<RoomInventoryDTO> list = new ArrayList<>();

        Iterable<RoomInventory> allRoomInventories = roomInventoryRepository.findAll();
        allRoomInventories.forEach(roomInventory -> list.add(
                getRoomInventoryDTO(roomInventory, roomInventory.getAsset(), roomInventory.getOperationRoom()))
        );

        return list;
    }

    public Optional<RoomInventoryDTO> getRoomInventoryById(Long assetId, Long roomId) {
        try {
            RoomInventoryId roomInventoryById = new RoomInventoryId(assetId, roomId);
            RoomInventory roomInventory = roomInventoryRepository.findById(roomInventoryById).orElseThrow();
            return Optional.of(
                    getRoomInventoryDTO(roomInventory, roomInventory.getAsset(), roomInventory.getOperationRoom())
            );
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public RoomInventoryDTO createRoomInventory(RoomInventoryDTO roomInventoryDTO) throws NoSuchElementException {
        RoomInventory roomInventory = new RoomInventory();
        Asset asset = assetRepository.findById(roomInventoryDTO.getAssetId()).get();
        OperationRoom operationRoom = operationRoomRepository.findById(roomInventoryDTO.getOperationRoomId()).get();

        roomInventory.setRoomInventoryId(new RoomInventoryId(asset.getId(), operationRoom.getId()));
        roomInventory.setAsset(asset);
        roomInventory.setOperationRoom(operationRoom);
        roomInventory.setCount(roomInventoryDTO.getCount());
        roomInventory = roomInventoryRepository.save(roomInventory);

        return getRoomInventoryDTO( roomInventory, roomInventory.getAsset(), roomInventory.getOperationRoom());
    }

    public boolean deleteRoomInventory(Long assetId, Long roomId) {
        RoomInventoryId roomInventoryId = new RoomInventoryId(roomId, assetId);
        return roomInventoryRepository.findById(roomInventoryId).map(roomInventory -> {
            roomInventoryRepository.delete(roomInventory);
            return true;
        }).orElse(false);
    }

    public Optional<RoomInventoryDTO> updateRoomInventory(Long assetId, Long roomId, RoomInventoryDTO roomInventoryDTO) {
          RoomInventoryId roomInventoryId = new RoomInventoryId(roomId, assetId);
        return roomInventoryRepository.findById(roomInventoryId).map(roomInventory -> {
            roomInventory.setCount(roomInventoryDTO.getCount());
            roomInventoryRepository.save(roomInventory);
            return getRoomInventoryDTO( roomInventory, roomInventory.getAsset(), roomInventory.getOperationRoom());
        });
    }
}

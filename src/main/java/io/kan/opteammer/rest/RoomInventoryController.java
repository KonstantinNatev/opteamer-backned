package io.kan.opteammer.rest;

import io.kan.opteammer.dto.RoomInventoryDTO;
import io.kan.opteammer.services.RoomInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/room-inventories")
public class RoomInventoryController {

    RoomInventoryService roomInventoryService;

   @Autowired
    public RoomInventoryController(RoomInventoryService roomInventoryService) {
        this.roomInventoryService = roomInventoryService;
    }

    @GetMapping("/{assetId}/{roomId}")
    public ResponseEntity<RoomInventoryDTO> getRoomInventoryById(
            @PathVariable(name = "assetId") Long assetId, @PathVariable(name = "roomId") Long roomId) {
        Optional<RoomInventoryDTO> roomInventoryDTOOptional = roomInventoryService.getRoomInventoryById(assetId, roomId);
        return roomInventoryDTOOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<RoomInventoryDTO>> getAllRoomInventories() {
        List<RoomInventoryDTO> inventoryList = roomInventoryService.getAllRoomInventories();
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryList);
    }

    @PostMapping
    public ResponseEntity<RoomInventoryDTO> createRoomInventory(@RequestBody RoomInventoryDTO roomInventoryDTO) {
        RoomInventoryDTO createdRoomInventoryDTO = roomInventoryService.createRoomInventory(roomInventoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRoomInventoryDTO);
    }

    @PutMapping("/{assetId}/{roomId}")
    public ResponseEntity<RoomInventoryDTO> updateRoomInventory(
            @PathVariable(name = "assetId") Long assetId, @PathVariable(name = "roomId") Long roomId,
            @RequestBody RoomInventoryDTO roomInventoryDTO) {
        Optional<RoomInventoryDTO> roomInventoryDTOOptional = roomInventoryService.updateRoomInventory(assetId,roomId,roomInventoryDTO);
        return roomInventoryDTOOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{assetId}/{roomId}")
    public ResponseEntity<Void> deleteInventory(@PathVariable(name = "assetId") Long assetId, @PathVariable(name = "roomId") Long roomId) {
        boolean deleted = roomInventoryService.deleteRoomInventory(assetId, roomId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

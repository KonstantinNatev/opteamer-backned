package io.kan.opteammer.rest;

import io.kan.opteammer.dto.InventoryDTO;
import io.kan.opteammer.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getInventoryById(@PathVariable( name = "id") Long id) {
        Optional<InventoryDTO> inventoryDTOOptional = inventoryService.getInventoryById(id);
        return inventoryDTOOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAllInventories() {
        List<InventoryDTO> InventoryList = inventoryService.getAllInventories();
        return ResponseEntity.status(HttpStatus.OK).body(InventoryList);
    }

    @PostMapping
    public ResponseEntity<InventoryDTO> createInventory(@RequestBody InventoryDTO InventoryDTO) {
        InventoryDTO createInventoryDTO = inventoryService.createInventory(InventoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createInventoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable( name = "id") Long id) {
        boolean deleted = inventoryService.deleteInventory(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> updateInventory(@PathVariable( name = "id") Long id, @RequestBody InventoryDTO InventoryDTO) {
        Optional<InventoryDTO> inventoryDTOOptional = inventoryService.updateInventory(id,InventoryDTO);
        return inventoryDTOOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}

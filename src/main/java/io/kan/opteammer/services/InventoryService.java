package io.kan.opteammer.services;

import io.kan.opteammer.dto.AssetDTO;
import io.kan.opteammer.dto.InventoryDTO;
import io.kan.opteammer.model.Asset;
import io.kan.opteammer.model.Inventory;
import io.kan.opteammer.repositories.AssetRepository;
import io.kan.opteammer.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class InventoryService {
    InventoryRepository inventoryRepository;
    AssetRepository assetRepository;

    private static InventoryDTO getInventoryDTO(Inventory inventory, Asset asset) {
        return new InventoryDTO(inventory.getAssetId(), new AssetDTO(asset.getId(), asset.getType(), asset.getName()), inventory.getCount());
    }

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, AssetRepository assetRepository) {
        this.inventoryRepository = inventoryRepository;
        this.assetRepository = assetRepository;
    }

    public List<InventoryDTO> getAllInventories() {
        List<InventoryDTO> list = new ArrayList<>();

        Iterable<Inventory> inventories = inventoryRepository.findAll();
        inventories.forEach(inventory -> list.add(getInventoryDTO(inventory, inventory.getAsset())));

        return list;
    }

    public Optional<InventoryDTO> getInventoryById(Long id) {
        try {
            Inventory inventoryById = inventoryRepository.findById(id).orElseThrow();
            return Optional.of(getInventoryDTO(inventoryById, inventoryById.getAsset()));
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public InventoryDTO createInventory(InventoryDTO inventoryDTO) throws NoSuchElementException {
        Inventory inventory = new Inventory();
        Asset asset = assetRepository.findById(inventoryDTO.getAssetId()).get();

        inventory.setAsset(asset);
        inventory.setCount(inventoryDTO.getCount());
        inventory = inventoryRepository.save(inventory);

        return getInventoryDTO(inventory, asset);
    }

    public boolean deleteInventory(Long id) {
        return inventoryRepository.findById(id).map(inventory -> {
            inventoryRepository.delete(inventory);
            return true;
        }).orElse(false);
    }

    public Optional<InventoryDTO> updateInventory(Long id, InventoryDTO InventoryDTO) {
        return inventoryRepository.findById(id).map(inventory -> {
            inventory.setCount(InventoryDTO.getCount());

            inventoryRepository.save(inventory);

            return getInventoryDTO(inventory, inventory.getAsset());
        });
    }
}

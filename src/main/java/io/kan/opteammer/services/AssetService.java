package io.kan.opteammer.services;

import io.kan.opteammer.dto.AssetDTO;
import io.kan.opteammer.model.Asset;
import io.kan.opteammer.repositories.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AssetService {
    AssetRepository assetRepository;

    private Asset mapDTOToEntity(AssetDTO assetDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(assetDTO, Asset.class);
    }

    private AssetDTO mapEntityToDTO(Asset asset) {
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(asset, AssetDTO.class);
    }

    @Autowired
    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<AssetDTO> getAllAssets() {
        List<AssetDTO> list = new ArrayList<>();

        Iterable<Asset> assets = assetRepository.findAll();
        assets.forEach(asset -> list.add(mapEntityToDTO(asset)));

        return list;
    }

    public Optional<AssetDTO> getAssetById(Long id) {
        try {
            Asset assetById = assetRepository.findById(id).orElseThrow();
            return Optional.of(mapEntityToDTO(assetById));
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public AssetDTO createAsset(AssetDTO assetDTO) {
        Asset asset = mapDTOToEntity(assetDTO);
        assetRepository.save(asset);

        return mapEntityToDTO(asset);
    }

    public boolean deleteAsset(Long id) {
        return assetRepository.findById(id).map(asset -> {
            assetRepository.delete(asset);
            return true;
        }).orElse(false);
    }

    public Optional<AssetDTO> updateAsset(Long id, AssetDTO assetDTO) {
        return assetRepository.findById(id).map(asset -> {
            asset.setName(assetDTO.getName());
            asset.setType(assetDTO.getType());

            assetRepository.save(asset);

            return mapEntityToDTO(asset);
        });
    }
}

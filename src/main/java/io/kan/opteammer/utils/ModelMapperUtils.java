package io.kan.opteammer.utils;

import io.kan.opteammer.dto.AssetDTO;
import io.kan.opteammer.dto.OperationProviderDTO;
import io.kan.opteammer.dto.OperationRoomDTO;
import io.kan.opteammer.model.Asset;
import io.kan.opteammer.model.OperationProvider;
import io.kan.opteammer.model.OperationRoom;
import org.modelmapper.ModelMapper;

public class ModelMapperUtils {
     public static OperationProvider mapOperationProviderDTOToEntity(OperationProviderDTO operationProviderDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(operationProviderDTO, OperationProvider.class);
    }

    public static OperationProviderDTO mapOperationProviderEntityToDTO(OperationProvider operationProvider) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(operationProvider, OperationProviderDTO.class);
    }

    public static AssetDTO mapAssetEntityToDTO(Asset asset) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(asset, AssetDTO.class);
    }

    public static Asset mapAssetDTOToEntity(AssetDTO assetDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(assetDTO, Asset.class);
    }

    public static OperationRoomDTO mapOperationRoomEntityToDTO(OperationRoom operationRoom) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(operationRoom, OperationRoomDTO.class);
    }
}

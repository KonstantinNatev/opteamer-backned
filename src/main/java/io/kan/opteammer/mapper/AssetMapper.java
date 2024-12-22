package io.kan.opteammer.mapper;

import io.kan.opteammer.dto.AssetDTO;
import io.kan.opteammer.model.Asset;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AssetMapper {
    AssetMapper INSTANCE = Mappers.getMapper(AssetMapper.class);
    AssetDTO assetToAssetDTO(Asset asset);
    Asset assetDTOToAsset(AssetDTO assetDTO);
}

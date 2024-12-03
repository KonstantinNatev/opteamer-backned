package io.kan.opteammer.dto;

import io.kan.opteammer.model.enums.AssetType;
import io.kan.opteammer.model.enums.OperationProviderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OperationProviderDTO {
    private OperationProviderType type;
}

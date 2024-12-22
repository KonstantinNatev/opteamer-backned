package io.kan.opteammer.services;

import io.kan.opteammer.dto.OperationTypeDTO;
import io.kan.opteammer.mapper.OperationTypeMapper;
import io.kan.opteammer.model.Asset;
import io.kan.opteammer.model.OperationProvider;
import io.kan.opteammer.model.OperationType;
import io.kan.opteammer.model.PreOperativeAssessment;
import io.kan.opteammer.repositories.AssetRepository;
import io.kan.opteammer.repositories.OperationProviderRepository;
import io.kan.opteammer.repositories.OperationTypeRepository;
import io.kan.opteammer.repositories.PreOperativeAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OperationTypeService {

    OperationTypeRepository operationTypeRepository;
    AssetRepository assetRepository;
    PreOperativeAssessmentRepository preOperativeAssessmentRepository;
    OperationProviderRepository operationProviderRepository;

    @Autowired
    public OperationTypeService(OperationTypeRepository operationTypeRepository, AssetRepository assetRepository,
                                PreOperativeAssessmentRepository preOperativeAssessmentRepository,
                                OperationProviderRepository operationProviderRepository) {
        this.operationTypeRepository = operationTypeRepository;
        this.assetRepository = assetRepository;
        this.preOperativeAssessmentRepository = preOperativeAssessmentRepository;
        this.operationProviderRepository = operationProviderRepository;
    }

    public Optional<OperationTypeDTO> getOperationTypeById(String name) {
        try {
            OperationType operationType = operationTypeRepository.findByName(name).orElseThrow();
            return Optional.of(OperationTypeMapper.INSTANCE.toOperationTypeDTO(operationType));
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public List<OperationTypeDTO>  getAllOperationTypes() {
        List<OperationTypeDTO> list = new ArrayList<>();
        Iterable<OperationType> allOperationTypes = operationTypeRepository.findAll();
        allOperationTypes.forEach(operationType -> list.add(OperationTypeMapper.INSTANCE.toOperationTypeDTO(operationType)));
        return list;
    }

    public OperationTypeDTO createOperationType(OperationTypeDTO operationTypeDTO) {
        OperationType operationType = OperationTypeMapper.INSTANCE.toOperationType(operationTypeDTO);
        setChildEntites(operationTypeDTO, operationType);
        operationType = operationTypeRepository.save(operationType);
        return OperationTypeMapper.INSTANCE.toOperationTypeDTO(operationType);
    }

    public Optional<OperationTypeDTO> updateOperationType(String name, OperationTypeDTO operationTypeDTO) {
        return operationTypeRepository.findByName(name).map(operationType -> {
            operationType.setRoomType(operationTypeDTO.getRoomType());
            operationType.setName(operationTypeDTO.getName());
            operationType.setDurationHours(operationTypeDTO.getDurationHours());
            setChildEntites(operationTypeDTO, operationType);
            operationTypeRepository.save(operationType);
            return OperationTypeMapper.INSTANCE.toOperationTypeDTO(operationType);
        });
    }

    public boolean deleteOperationType(String name) {
        return operationTypeRepository.findByName(name).map(operationType -> {
            operationTypeRepository.delete(operationType);
            return true;
        }).orElse(false);
    }

    private void setChildEntites(OperationTypeDTO operationTypeDTO, OperationType operationType) {
        Set<Asset> assets = new HashSet<>();
        operationTypeDTO.getAssets().stream().forEach(assetDTO -> {
            Asset asset = assetRepository.findById(assetDTO.getId()).get();
            assets.add(asset);
        });

        Set<PreOperativeAssessment> preOperativeAssessments = operationTypeDTO.getPreOperativeAssessments().stream()
                .map(preOperativeAssessmentDTO -> preOperativeAssessmentRepository.findByName(preOperativeAssessmentDTO.getName())
                        .orElseThrow(() -> new NoSuchElementException()))
                .collect(Collectors.toSet());

        Set<OperationProvider> operationProviders = operationTypeDTO.getOperationProviders().stream()
                .map(operationProviderDTO -> operationProviderRepository.findByType(operationProviderDTO.getType())
                        .orElseThrow(() -> new NoSuchElementException()))
                .collect(Collectors.toSet());

        operationType.setAssets(assets);
        operationType.setPreOperativeAssessments(preOperativeAssessments);
        operationType.setOperationProviders(operationProviders);
    }
}

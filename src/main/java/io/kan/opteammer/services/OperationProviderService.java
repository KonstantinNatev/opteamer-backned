package io.kan.opteammer.services;

import io.kan.opteammer.dto.OperationProviderDTO;
import io.kan.opteammer.model.OperationProvider;
import io.kan.opteammer.model.enums.OperationProviderType;
import io.kan.opteammer.repositories.OperationProviderRepository;
import io.kan.opteammer.repositories.PatientRepository;
import io.kan.opteammer.utils.ModelMapperUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OperationProviderService {
    OperationProviderRepository operationProviderRepository;

    @Autowired
    public OperationProviderService(OperationProviderRepository operationProviderRepository) {
        this.operationProviderRepository = operationProviderRepository;
    }

    public List<OperationProviderDTO> getAllOperationProvider() {
        List<OperationProviderDTO> list = new ArrayList<>();
        Iterable<OperationProvider> allOperationProviders = operationProviderRepository.findAll();
        allOperationProviders.forEach(operationProvider -> list.add(ModelMapperUtils.mapOperationProviderEntityToDTO(operationProvider)));
        return list;
    }

    public Optional<OperationProviderDTO> getOperationProviderById(String id) {
        try {
            OperationProvider operationProvider = operationProviderRepository.findByType(OperationProviderType.valueOf(id)).orElseThrow();
            return Optional.of(ModelMapperUtils.mapOperationProviderEntityToDTO(operationProvider));
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public OperationProviderDTO createOperationProvider(OperationProviderDTO operationProviderDTO) {
        ModelMapper modelMapper = new ModelMapper();
        OperationProvider operationProvider = modelMapper.map(operationProviderDTO, OperationProvider.class);
        operationProvider = operationProviderRepository.save(operationProvider);
        return modelMapper.map(operationProvider, OperationProviderDTO.class);
    }

    public boolean deleteOperationProvider(String id) {
        return operationProviderRepository.findByType(OperationProviderType.valueOf(id)).map(operationProvider -> {
            operationProviderRepository.delete(operationProvider);
            return true;
        }).orElse(false);
    }

    public Optional<OperationProviderDTO> updateOperationProvider(String id, OperationProviderDTO operationProviderDTO) {
         return operationProviderRepository.findByType(OperationProviderType.valueOf(id)).map(operationProvider -> {
            operationProvider.setType(operationProviderDTO.getType());
            operationProviderRepository.save(operationProvider);
            return ModelMapperUtils.mapOperationProviderEntityToDTO(operationProvider);
        });
    }
}

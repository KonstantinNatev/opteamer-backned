package io.kan.opteammer.services;

import io.kan.opteammer.dto.PreOperativeAssessmentDTO;
import io.kan.opteammer.model.PreOperativeAssessment;
import io.kan.opteammer.repositories.PreOperativeAssessmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PreOperativeAssessmentService {
    PreOperativeAssessmentRepository preOperativeAssessmentRepository;

    private PreOperativeAssessment mapDTOToEntity(PreOperativeAssessmentDTO preOperativeAssessmentDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(preOperativeAssessmentDTO, PreOperativeAssessment.class);
    }

    private PreOperativeAssessmentDTO mapEntityToDTO(PreOperativeAssessment preOperativeAssessment) {
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(preOperativeAssessment, PreOperativeAssessmentDTO.class);
    }

    @Autowired
    public PreOperativeAssessmentService(PreOperativeAssessmentRepository preOperativeAssessmentRepository) {
        this.preOperativeAssessmentRepository = preOperativeAssessmentRepository;
    }

    public List<PreOperativeAssessmentDTO> getAllPreOperativeAssessment() {
        List<PreOperativeAssessmentDTO> list = new ArrayList<>();

        Iterable<PreOperativeAssessment> preOperativeAssessments = preOperativeAssessmentRepository.findAll();
        preOperativeAssessments.forEach(preOperativeAssessment -> {
            list.add(mapEntityToDTO(preOperativeAssessment));
        });

        return list;
    }

    public Optional<PreOperativeAssessmentDTO> getPreOperativeAssessmentByName(String name) {
        PreOperativeAssessment preOperativeAssessmentByName = preOperativeAssessmentRepository.findByName(name).orElseThrow();
        return Optional.of(mapEntityToDTO(preOperativeAssessmentByName));
    }

    public PreOperativeAssessmentDTO cratePreOperativeAssessment(PreOperativeAssessmentDTO preOperativeAssessmentDTO) {
        PreOperativeAssessment preOperativeAssessment = mapDTOToEntity(preOperativeAssessmentDTO);
        preOperativeAssessmentRepository.save(preOperativeAssessment);

        return mapEntityToDTO(preOperativeAssessment);
    }

    public boolean deletePreOperativeAssessment(String name) {
        return preOperativeAssessmentRepository.findByName(name).map(preOperativeAssessment -> {
            preOperativeAssessmentRepository.delete(preOperativeAssessment);
            return true;
        }).orElse(false);
    }

    public Optional<PreOperativeAssessmentDTO> updatePreOperativeAssessment(String name, PreOperativeAssessmentDTO preOperativeAssessmentDTO) {
        return preOperativeAssessmentRepository.findByName(name).map(preOperativeAssessment -> {
            preOperativeAssessment.setName(preOperativeAssessmentDTO.getName());
            preOperativeAssessmentRepository.save(preOperativeAssessment);

            return mapEntityToDTO(preOperativeAssessment);
        });
    }
}

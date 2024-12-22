package io.kan.opteammer.mapper;

import io.kan.opteammer.dto.PreOperativeAssessmentDTO;
import io.kan.opteammer.model.PreOperativeAssessment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PreOperativeAssessmentMapper {

    PreOperativeAssessmentMapper INSTANCE = Mappers.getMapper(PreOperativeAssessmentMapper.class);

    PreOperativeAssessmentDTO toPreOperativeAssessmentDTO(PreOperativeAssessment preOperativeAssessment);

    PreOperativeAssessment toPreOperativeAssessment(PreOperativeAssessmentDTO preOperativeAssessmentDTO);

}

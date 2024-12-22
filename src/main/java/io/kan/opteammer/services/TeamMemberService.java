package io.kan.opteammer.services;

import io.kan.opteammer.dto.TeamMemberDTO;
import io.kan.opteammer.model.OperationProvider;
import io.kan.opteammer.model.TeamMember;
import io.kan.opteammer.repositories.TeamMemberRepository;
import io.kan.opteammer.utils.ModelMapperUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TeamMemberService {
    TeamMemberRepository teamMemberRepository;

    private static TeamMemberDTO getMappedTeamMemberDTO(ModelMapper modelMapper, TeamMember teamMember) {
        TeamMemberDTO mappedTeamMemberDTO = modelMapper.map(teamMember, TeamMemberDTO.class);
        mappedTeamMemberDTO.setOperationProvider(ModelMapperUtils.mapOperationProviderEntityToDTO(teamMember.getOperationProvider()));
        return mappedTeamMemberDTO;
    }

    @Autowired
    public TeamMemberService(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    public Optional<TeamMemberDTO> getTeamMemberById(Long id) {
        try {
            TeamMember teamMember = teamMemberRepository.findById(id).orElseThrow();
            return Optional.of(getMappedTeamMemberDTO(new ModelMapper(), teamMember));
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public List<TeamMemberDTO> getAllTeamMembers() {
        List<TeamMemberDTO> list = new ArrayList<>();
        Iterable<TeamMember> allTeamMembers = teamMemberRepository.findAll();
        allTeamMembers.forEach(teamMember -> list.add(getMappedTeamMemberDTO(new ModelMapper(), teamMember)));
        return list;
    }

    public TeamMemberDTO createTeamMember(TeamMemberDTO teamMemberDTO) {
        ModelMapper modelMapper = new ModelMapper();
        TeamMember teamMember = modelMapper.map(teamMemberDTO, TeamMember.class);
        teamMember = teamMemberRepository.save(teamMember);
        return getMappedTeamMemberDTO(modelMapper, teamMember);
    }

    public Optional<TeamMemberDTO> updateTeamMember(Long id, TeamMemberDTO teamMemberDTO) {
        return teamMemberRepository.findById(id).map(teamMember -> {

            teamMember.setName(teamMemberDTO.getName());
            OperationProvider operationProvider = ModelMapperUtils.mapOperationProviderDTOToEntity(teamMemberDTO.getOperationProvider());
            teamMember.setOperationProvider(operationProvider);

            teamMemberRepository.save(teamMember);
            return getMappedTeamMemberDTO(new ModelMapper(), teamMember);
        });
    }

    public boolean deleteTeamMember(Long id) {
        return teamMemberRepository.findById(id).map(teamMember -> {
            teamMemberRepository.delete(teamMember);
            return true;
        }).orElse(false);
    }
}

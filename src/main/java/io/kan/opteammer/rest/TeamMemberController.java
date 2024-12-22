package io.kan.opteammer.rest;

import io.kan.opteammer.dto.TeamMemberDTO;
import io.kan.opteammer.services.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author degijanos
 * @version 1.0
 * @since 2024. 06. 15.
 */

@RestController
@RequestMapping("/api/team-members")
public class TeamMemberController {

    TeamMemberService teamMemberService;

    @Autowired
    public TeamMemberController(TeamMemberService teamMemberService) {
        this.teamMemberService = teamMemberService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamMemberDTO> getTeamMemberById(@PathVariable(name = "id") Long id) {
        Optional<TeamMemberDTO> teamMemberDTOOptional = teamMemberService.getTeamMemberById(id);
        return teamMemberDTOOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping
    public ResponseEntity<List<TeamMemberDTO>> getAllTeamMembers() {
        List<TeamMemberDTO> teamMemberList = teamMemberService.getAllTeamMembers();
        return ResponseEntity.status(HttpStatus.CREATED).body(teamMemberList);
    }

    @PostMapping
    public ResponseEntity<TeamMemberDTO> createTeamMember(@RequestBody TeamMemberDTO TeamMemberDTO) {
        TeamMemberDTO createdTeamMemberDTO = teamMemberService.createTeamMember(TeamMemberDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeamMemberDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamMemberDTO> updateTeamMember(@PathVariable(name = "id") Long id, @RequestBody TeamMemberDTO TeamMemberDTO) {
        Optional<TeamMemberDTO> teamMemberDTOOptional = teamMemberService.updateTeamMember(id, TeamMemberDTO);
        return teamMemberDTOOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeamMember(@PathVariable(name = "id") Long id) {
        boolean deleted = teamMemberService.deleteTeamMember(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

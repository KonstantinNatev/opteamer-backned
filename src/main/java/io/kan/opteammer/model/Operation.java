package io.kan.opteammer.model;

import io.kan.opteammer.model.enums.OperationState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author degijanos
 * @version 1.0
 * @since 2024. 06. 13.
 */

@Entity
@Table(name = "OPERATIONS")
@NoArgsConstructor
@Getter
@Setter
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private OperationType operationType;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private OperationRoom operationRoom;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private OperationState state;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "optype_team_member",
            joinColumns = @JoinColumn(name = "operation_id"),
            inverseJoinColumns = @JoinColumn(name = "team_member_id"))
    private Set<TeamMember> teamMembers = new HashSet<TeamMember>();
}

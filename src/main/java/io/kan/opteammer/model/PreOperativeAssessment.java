package io.kan.opteammer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author degijanos
 * @version 1.0
 * @since 2024. 06. 13.
 */

@Entity
@Table(name = "PRE_OPERATIVE_ASSESSMENTS")
@Getter
@Setter
public class PreOperativeAssessment {

    @Id
    private String name;
}

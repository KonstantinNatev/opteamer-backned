package io.kan.opteammer.rest;

import io.kan.opteammer.dto.UserRegistrationDTO;
import io.kan.opteammer.services.UserRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/userregistration")
public class UserRegistrationController {

    UserRegistrationService userRegistrationService;

    @Autowired
    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        userRegistrationService.createUser(userRegistrationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

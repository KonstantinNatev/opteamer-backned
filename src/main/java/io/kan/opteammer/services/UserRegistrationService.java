package io.kan.opteammer.services;

import io.kan.opteammer.dto.UserRegistrationDTO;
import io.kan.opteammer.model.Role;
import io.kan.opteammer.model.User;
import io.kan.opteammer.model.enums.ERole;
import io.kan.opteammer.repositories.RoleRepository;
import io.kan.opteammer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserRegistrationService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserRegistrationService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserRegistrationDTO registrationDTO) {
        Role role = roleRepository.findByName(ERole.ROLE_USER).get();

        User user = new User(registrationDTO.getUsername(), registrationDTO.getEmail(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                new HashSet<>(Arrays.asList(role)));

        userRepository.save(user);
    }

}

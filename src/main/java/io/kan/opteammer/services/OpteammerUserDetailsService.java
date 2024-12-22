package io.kan.opteammer.services;

import io.kan.opteammer.model.OpteamerUserDetails;
import io.kan.opteammer.model.User;
import io.kan.opteammer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("OpteammerUserDetailsService")
public class OpteammerUserDetailsService implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public OpteammerUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return OpteamerUserDetails.build(user);
    }
}
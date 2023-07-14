package com.banking.security.implementations;

import com.banking.entity.entityenumerations.Role;
import com.banking.security.Authorisation;
import com.banking.security.interfaces.AuthorisationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthorisationService authorisationService;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Authorisation auth = authorisationService.findByLogin(login);
        return new User(auth.getLogin(), auth.getPassword(), getRoles(Collections.singletonList(auth.getRole())));
    }

    private List<GrantedAuthority> getRoles(List<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }
}

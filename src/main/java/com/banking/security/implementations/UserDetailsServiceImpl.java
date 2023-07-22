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

/**
 * Implementation of the Spring Security {@link org.springframework.security.core.userdetails.UserDetailsService}
 * interface to load user details from the database based on their login (username).
 *
 * <p>This class retrieves the user authorization information from the database using the {@link
 * com.banking.security.interfaces.AuthorisationService} and constructs a {@link org.springframework.security.core.userdetails.UserDetails}
 * object with the necessary user details and authorities for Spring Security authentication and authorization.
 *
 * @since 2023-07-19
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthorisationService authorisationService;

    /**
     * Loads user details from the database based on the provided login (username).
     *
     * @param login The login (username) of the user to retrieve details for.
     * @return A {@link org.springframework.security.core.userdetails.UserDetails} object containing the user's details
     * and authorities for authentication and authorization.
     * @throws UsernameNotFoundException If the user with the provided login is not found in the database.
     * @since 2023-07-19
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Authorisation auth = authorisationService.findByLogin(login);
        return new User(auth.getLogin(), auth.getPassword(), getRoles(Collections.singletonList(auth.getRole())));
    }

    /**
     * Converts a list of {@link com.banking.entity.entityenumerations.Role} entities to a list of {@link
     * org.springframework.security.core.GrantedAuthority} objects.
     *
     * @param roles The list of {@link com.banking.entity.entityenumerations.Role} entities representing the roles
     *              associated with the user.
     * @return A list of {@link org.springframework.security.core.GrantedAuthority} objects representing the roles (authorities)
     * associated with the user.
     * @since 2023-07-19
     */
    private List<GrantedAuthority> getRoles(List<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toList());
    }
}

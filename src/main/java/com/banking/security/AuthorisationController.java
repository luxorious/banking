package com.banking.security;

import com.banking.security.interfaces.AuthorisationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Spring MVC controller for handling HTTP requests related to {@link com.banking.security.Authorisation} entities.
 *
 * @since 2023-07-19
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthorisationController {

    private final AuthorisationService authorisationService;
    /**
     * Endpoint for saving an {@link com.banking.security.Authorisation} entity.
     *
     * @param authorisation The {@link com.banking.security.Authorisation} object to be saved.
     * @since 2023-07-19
     */
    @GetMapping("/create")
    public void save(@RequestBody Authorisation authorisation) {
        authorisationService.save(authorisation);
    }
    /**
     * Endpoint for retrieving all {@link com.banking.security.Authorisation} entities.
     *
     * @return A list of all {@link com.banking.security.Authorisation} entities.
     * @since 2023-07-19
     */
    @GetMapping("/get-all")
    public List<Authorisation> getAll(){
        return authorisationService.findAll();
    }
    /**
     * Endpoint for finding an {@link com.banking.security.Authorisation} entity by login (username) and password.
     *
     * @param login    The login (username) of the user.
     * @param password The password of the user.
     * @return A string indicating whether the user is found or not.
     * @since 2023-07-19
     */
    @GetMapping("/get-by")
    public String findByLoginAndPassword(@RequestParam String login, @RequestParam String password) {
        return authorisationService.findByLoginAndPassword(login, password);
    }

    /**
     * Endpoint for changing the password of an {@link com.banking.security.Authorisation} entity.
     *
     * @param login      The login (username) of the user.
     * @param newPassword The new password to be set for the user.
     * @since 2023-07-19
     */
    @PutMapping("/change-password")
    public void changePassword(@RequestParam String login, @RequestParam String newPassword) {
        authorisationService.changePassword(login, newPassword);
    }
}

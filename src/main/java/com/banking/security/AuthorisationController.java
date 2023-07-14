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

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthorisationController {

    private final AuthorisationService authorisationService;

    @GetMapping("/create")
    public void save(@RequestBody Authorisation authorisation) {
        authorisationService.save(authorisation);
    }

    @GetMapping("/get-all")
    public List<Authorisation> getAll(){
        return authorisationService.findAll();
    }

    @GetMapping("/get-by")
    public String findByLoginAndPassword(@RequestParam String login, @RequestParam String password) {
        return authorisationService.findByLoginAndPassword(login, password);
    }

    @PutMapping("/change-password")
    public void changePassword(@RequestParam String login, @RequestParam String newPassword) {
        authorisationService.changePassword(login, newPassword);
    }
}

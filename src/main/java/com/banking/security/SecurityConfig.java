package com.banking.security;


import com.banking.entity.entityenumerations.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    @Value("${security.userLinks}")
    private String userLinks;
    @Value("${security.managerLinks}")
    private String managerLinks;
    @Value("${security.adminLinks}")
    private String adminLinks;
    @Value("${security.domainName}")
    private String domainName;


    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(convertStringToList(adminLinks).toArray(
                                new String[0])).hasRole(String.valueOf(Role.ADMINISTRATOR))
                        .requestMatchers(convertStringToList(userLinks).toArray(
                                new String[0])).hasRole(String.valueOf(Role.USER))
                        .requestMatchers(convertStringToList(managerLinks).toArray(
                                new String[0])).hasRole(String.valueOf(Role.MANAGER))
                        .anyRequest().permitAll()
                )
                .logout(out -> out
                        .logoutUrl(domainName + "/logout")
                        .permitAll()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    private List<String> convertStringToList(String input) {
        String[] values = input.split(",\\s*");
        for (int i = 0; i < values.length; i++) {
            values[i] = domainName + values[i];
        }
        return Arrays.asList(values);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

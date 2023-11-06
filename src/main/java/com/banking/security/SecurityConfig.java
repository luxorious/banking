package com.banking.security;

import com.banking.entity.entityenumerations.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.List;

/**
 * Spring Security configuration class that defines security settings for the application.
 *
 * @since 2023-07-19
 */
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

    /**
     * Configures the security filter chain to handle different types of requests based on the user's role.
     *
     * @param http The {@link HttpSecurity} object used for configuring security.
     * @return The configured security filter chain.
     * @throws Exception If there is an error while configuring the security.
     * @since 2023-07-19
     */
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(convertStringToList(userLinks).toArray(
                                new String[0])).hasAnyRole(
                                String.valueOf(Role.USER),
                                String.valueOf(Role.MANAGER),
                                String.valueOf(Role.ADMINISTRATOR))
                        .requestMatchers(convertStringToList(managerLinks).toArray(
                                new String[0])).hasAnyRole(
                                String.valueOf(Role.MANAGER),
                                String.valueOf(Role.ADMINISTRATOR))
                        .requestMatchers(convertStringToList(adminLinks).toArray(
                                new String[0])).hasRole(String.valueOf(Role.ADMINISTRATOR))
                        .anyRequest().permitAll()
                )
                .logout(out -> out
                        .logoutUrl(domainName + "/logout")
                        .permitAll()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    /**
     * Converts a comma-separated string of links to a list of fully qualified links by appending the domainName.
     *
     * @param input The comma-separated string of links.
     * @return A list of fully qualified links.
     * @since 2023-07-19
     */
    private List<String> convertStringToList(String input) {
        String[] values = input.split(",\\s*");
        for (int i = 0; i < values.length; i++) {
            values[i] = domainName + values[i];
        }
        return Arrays.asList(values);
    }

    /**
     * Creates a BCryptPasswordEncoder bean for password encoding.
     *
     * @return A BCryptPasswordEncoder bean.
     * @since 2023-07-19
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package com.banking.security;


import com.banking.dbfiller.FileUtil;
import com.banking.entity.entityenumerations.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfiguration {

    private final UserDetailsService userDetailsService;

    @Value("${security.userLinks}")
    private static String userLinks;
    @Value("${security.managerLinks}")
    private static String managerLinks;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    protected static SecurityFilterChain configure(HttpSecurity http,
                                                   @Value("${security.userLinks}") String userLinks,
                                                   @Value("${security.managerLinks}") String managerLinks) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                //почему когда ени реквест поставить в начало прога падает?
                        .requestMatchers(convertStringToList(userLinks).toArray(
                                new String[0])).hasRole(String.valueOf(Role.USER))
                        .requestMatchers(convertStringToList(managerLinks).toArray(
                                new String[0])).hasRole(String.valueOf(Role.MANAGER))
                        .requestMatchers("/swagger-ui/index.html").permitAll()
                        .anyRequest().hasRole(String.valueOf(Role.ADMINISTRATOR))
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    public static List<String> convertStringToList(String input) {
        String[] values = input.split(",\\s*");
        return Arrays.asList(values);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

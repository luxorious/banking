package com.banking.security;


import com.banking.dbfiller.FileUtil;
import com.banking.entity.entityenumerations.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Value("${security.userLinks}")
    private String userLinks;
    @Value("${security.managerLinks}")
    private String managerLinks;
    @Value("${security.adminLinks}")
    private String adminLinks;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    protected static SecurityFilterChain configure(HttpSecurity http,
                                                   @Value("${security.userLinks}") String userLinks,
                                                   @Value("${security.managerLinks}") String managerLinks,
                                                   @Value("${security.adminLinks}") String adminLinks) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        //почему когда ени реквест поставить в начало прога падает?
                        .requestMatchers(convertStringToList(adminLinks).toArray(
                                new String[0])).hasRole(String.valueOf(Role.ADMINISTRATOR))
                        .requestMatchers(convertStringToList(userLinks).toArray(
                                new String[0])).hasRole(String.valueOf(Role.USER))
                        .requestMatchers(convertStringToList(managerLinks).toArray(
                                new String[0])).hasRole(String.valueOf(Role.MANAGER))
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .usernameParameter("login")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/swagger-ui/index.html")
                        .permitAll()
                )
                .logout(out -> out
                        .logoutUrl("/logout")
                        .permitAll()
                );
//                .httpBasic(Customizer.withDefaults());
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

//package com.banking.security;
//
//import com.banking.dbfiller.FileUtil;
//import com.banking.entity.entityenumerations.Role;
//import com.banking.repository.BankRepository;
//import com.banking.service.interfaces.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
////@EnableMethodSecurity()/////////////////////////////////////////////////////////////////////
//@RequiredArgsConstructor
//public class BankingSecurity{// extends WebSecurityConfiguration {
//
//    private final AccountService accountService;
//    private final AgreementService agreementService;
//    private final BankRepository bankRepository;
//    private final ClientService clientService;
//    private final CreditService creditService;
//    private final DocumentService documentService;
//    private final ManagerService managerService;
//    private final ProductService productService;
//    private final TransactionService transactionService;
//
//    private final
//
//    private final FileUtil fileUtil;
//
//    @Value("${security.userPath}")
//    private String userPath;
//    @Value("${security.managerPath}")
//    private String managerPath;
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        return http.authorizeHttpRequests(auth -> auth
////                        .anyRequest().permitAll()
////                )
////                .build();
////    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests(auth -> auth
//                        .requestMatchers(fileUtil.writeFromFileToList(userPath).toArray(new String[0])).hasRole("MANAGER")
//                        .requestMatchers(fileUtil.writeFromFileToList(managerPath).toArray(new String[0])).hasRole("USER")
//                        .anyRequest().hasRole(String.valueOf(Role.ADMINISTRATOR))
//                )
//                .httpBasic(Customizer.withDefaults())
//                .build();
//    }
//}

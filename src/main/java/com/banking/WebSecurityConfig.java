//package com.banking;
//
//import com.banking.repository.AccountRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
////import org.springframework.security.crypto.password.NoOpPasswordEncoder;
////import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
////@EnableWebSecurity
//public class WebSecurityConfig {
//
//    @Autowired
//    private AccountRepository userRepository;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests()
//                //.requestMatchers("/new-match").hasRole("SUPER_USER")
//                //.requestMatchers(HttpMethod.GET,"/new-match").hasRole("USER")
//                //.requestMatchers("/new-match").hasRole("USER")
//                //.requestMatchers("/hello").hasRole("ADMIN")
//                //.requestMatchers("/**").hasRole("ADMIN")
//                //.requestMatchers("/user/get").hasRole("ADMIN")
//                //.requestMatchers("/delete/all").hasRole("OWNER")
//                //.requestMatchers("/new-match").hasRole("USER")
//                .anyRequest().permitAll()
//                .and()
//                .formLogin();
//
//        return http.build();
//    }
//
////    @Bean
////    public UserDetailsService userDetailsService() {
////        List<Account> accounts = userRepository.findAll();
////        List<UserDetails> users = new ArrayList<>();
////
////        for (Account account : accounts) {
////            UserDetails userDetails = User
////                    .withUsername(account.getName() + "_" + account.getId())
////                    .password("1")
////                    .roles("SUPER_USER")
////                    .build();
////            users.add(userDetails);
////        }
////
////        return new InMemoryUserDetailsManager(users);
////    }
//
//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
//
//}

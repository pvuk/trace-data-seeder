package com.nl.trace.dataseeder.config.security	;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	/**
     * Migration Tip:
	To migrate from WebSecurityConfigurerAdapter to the new approach:
	
	Replace extends WebSecurityConfigurerAdapter with a @Bean method returning SecurityFilterChain.
	Use lambda expressions for configuring HttpSecurity.

     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests(auth -> auth
////                .requestMatchers("/h2-console/**", "/convert/testAPI", "/convert/upload-multiple")
//                .requestMatchers(HttpMethod.POST, "/convert/upload-multiple", "/data-seeder/convert/upload-multiple")
//                .permitAll()
//                .anyRequest().authenticated()
//            )
//            .csrf(csrf -> csrf
//                .ignoringRequestMatchers("/h2-console/**", "/data-seeder/convert/upload-multiple")
//            )
//            .headers(headers -> headers
//                .frameOptions(frame -> frame.disable())
//            );
//
//        return http.build();
    	

    	http.securityMatcher("/**"); // optional
    http
      .csrf(csrf -> csrf.disable())            // temporarily if needed
      .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
      .headers(headers -> headers.frameOptions(frame -> frame.disable())); // if H2 etc.
//    http.debug(true); // <— enable debug logging
    return http.build();

    }
}


package com.employeemanagement.employeemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .cors(Customizer.withDefaults())
//            .csrf().disable()
//            .authorizeHttpRequests(auth -> auth
//            		.requestMatchers("/h2-console/**").permitAll()
//            		.anyRequest().authenticated())
//            .formLogin(formLogin ->
//            formLogin.loginPage("/login") 
//                     .permitAll() 
//            );
//        return http.build();
//    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        //configuration.setAllowedMethods(Arrays.asList("*")); //Permitindo métodos do protocolo HTTP
        //configuration.setAllowedHeaders(Arrays.asList("*")); //Permitindo todos os headers
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}

package com.shoppy.Shoppy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private JwtAuthFilter _authFilter;

    @Bean
    public UserDetailsService getUserDetailsService(){
        return new UsersUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setUserDetailsService(getUserDetailsService());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManagerProvider(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain getFilterChain(HttpSecurity security) throws Exception {
        security.csrf(customizer->customizer.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(request->
                    request.requestMatchers("/auth/login").permitAll()
                            .requestMatchers("/api/**").hasAnyAuthority("USER")
                            .requestMatchers("/papi/**").hasAnyAuthority("ADMIN")
                            .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(getAuthenticationProvider())
                .sessionManagement(session->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(_authFilter, UsernamePasswordAuthenticationFilter.class);;
        return security.build();
    }

}

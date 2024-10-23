package com.ads.dacapicar.config;

import com.ads.dacapicar.filter.JwtRequestFilter;
import com.ads.dacapicar.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_MATCHERS = new String[]{ "/postgresql" };

    @Autowired
    private Environment environment;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        if (Arrays.asList(environment.getActiveProfiles()).contains("homolog")) {
            http.headers().frameOptions().disable();
        }
                http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers(HttpMethod.GET, "/api/cars/**").authenticated()
                .antMatchers(HttpMethod.GET, "/api/users/**").authenticated()
                .antMatchers(HttpMethod.POST, "/api/cars/").hasAuthority("PRIVILEGE")
                .antMatchers(HttpMethod.POST, "/api/cars/bulk/").hasAuthority("PRIVILEGE")
                .antMatchers(HttpMethod.PUT, "/api/cars/").hasAuthority("PRIVILEGE")
                .antMatchers(HttpMethod.DELETE, "/api/cars/").hasAuthority("PRIVILEGE")
                .antMatchers(HttpMethod.POST, "/api/users/").hasAuthority("PRIVILEGE")
                .antMatchers(HttpMethod.POST, "/api/users/bulk/").hasAuthority("PRIVILEGE")
                .antMatchers(HttpMethod.PUT, "/api/users/").hasAuthority("PRIVILEGE")
                .antMatchers(HttpMethod.DELETE, "/api/users/").hasAuthority("PRIVILEGE")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

package ru.hits.hitsback.timetable.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.hits.hitsback.timetable.service.JwtService;

import static ru.hits.hitsback.timetable.configuration.UrlConstant.*;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    public WebSecurityConfig(JwtService jwtService) {
        this.jwtService = jwtService;
        jwtFilter = new JwtFilter(jwtService);
    }
    @Autowired
    private final JwtService jwtService;
    private final JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .securityContext()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/webjars/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + AUTHORISATION_URL + "/sign-out")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + AUTHORISATION_URL + "/sign-in")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + AUTHORISATION_URL + "/teacher/sign-up")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + AUTHORISATION_URL + "/student/sign-up")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + REQUEST_URL + "/**")).hasAuthority("Admin")
                .requestMatchers(BASE_URL + AUTHORISATION_URL + "/sign-out").authenticated()
                .requestMatchers("/api/v1/teacher").authenticated()
                .and()
                .addFilterAfter(jwtFilter, SecurityContextHolderFilter.class)
                .build();

    }


    @Bean
    public CorsConfigurationSource corsConfiguration() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.applyPermitDefaultValues();
        corsConfig.addAllowedHeader("*");
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedOrigin("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder(
                BCryptPasswordEncoder.BCryptVersion.$2A,
                10
        );
    }}

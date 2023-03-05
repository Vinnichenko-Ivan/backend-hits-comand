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
                .cors().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .securityContext()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/webjars/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()

                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + AUTHORISATION_URL + "/sign-out")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + AUTHORISATION_URL + "/sign-out-all")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + AUTHORISATION_URL + "/sign-in")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + AUTHORISATION_URL + "/teacher/sign-up")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + AUTHORISATION_URL + "/student/sign-up")).permitAll()

                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + PROFILE_URL)).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + PROFILE_URL + "/me")).hasAuthority("Admin")
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + PROFILE_URL + "/group")).hasAuthority("Student")
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + PROFILE_URL + "/security/password")).authenticated()

                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + GROUP_URL)).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + GROUP_URL + "/**")).hasAuthority("Admin")

                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + STUDY_ROOM_URL)).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + STUDY_ROOM_URL + "/**")).hasAuthority("Admin")

                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + TEACHER_URL)).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + TEACHER_URL + "/**")).hasAuthority("Admin")

                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + SUBJECT_URL)).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + SUBJECT_URL + "/**")).hasAuthority("Admin")

                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + SCHEDULE_URL)).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + SCHEDULE_URL + "/group/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + SCHEDULE_URL + "/teacher/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + SCHEDULE_URL + "/staff")).hasAuthority("ScheduleWriter")
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + SCHEDULE_URL + "/lesson-time")).hasAuthority("ScheduleWriter")

                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + LESSON_URL + "/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + LESSON_URL + "/type")).hasAuthority("ScheduleWriter")
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + LESSON_URL + "/staff")).hasAuthority("ScheduleWriter")
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + LESSON_URL + "/lesson-group")).hasAuthority("ScheduleWriter")
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + LESSON_URL + "/staff")).hasAuthority("ScheduleWriter")

                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + REQUEST_URL + "/registration")).hasAuthority("Admin")
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + REQUEST_URL + "/group")).hasAuthority("Admin")
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + REQUEST_URL + "/registration/**")).hasAuthority("Admin")
                .requestMatchers(new AntPathRequestMatcher("/" + BASE_URL + REQUEST_URL + "/group/**")).hasAuthority("Admin")


//                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll() //TODO для дебага пока так

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

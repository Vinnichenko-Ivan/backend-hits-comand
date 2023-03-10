package ru.hits.hitsback.timetable.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                .cors().configurationSource(corsConfiguration())
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .securityContext()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(new AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/webjars/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v3/api-docs/**")).permitAll()

                .requestMatchers(HttpMethod.GET, "/" + BASE_URL + TEACHER_URL).permitAll()
                .requestMatchers(HttpMethod.PUT, "/" + BASE_URL + TEACHER_URL).hasAuthority("Admin")
                .requestMatchers(HttpMethod.POST, "/" + BASE_URL + TEACHER_URL).hasAuthority("Admin")
                .requestMatchers(HttpMethod.DELETE, "/" + BASE_URL + TEACHER_URL + "/{id}").hasAuthority("Admin")

                .requestMatchers(HttpMethod.GET, "/" + BASE_URL + SUBJECT_URL).permitAll()
                .requestMatchers(HttpMethod.PUT, "/" + BASE_URL + SUBJECT_URL).hasAuthority("Admin")
                .requestMatchers(HttpMethod.POST, "/" + BASE_URL + SUBJECT_URL).hasAuthority("Admin")
                .requestMatchers(HttpMethod.DELETE, "/" + BASE_URL + SUBJECT_URL + "/{id}").hasAuthority("Admin")

                .requestMatchers(HttpMethod.GET, "/" + BASE_URL + STUDY_ROOM_URL).permitAll()
                .requestMatchers(HttpMethod.PUT, "/" + BASE_URL + STUDY_ROOM_URL).hasAuthority("Admin")
                .requestMatchers(HttpMethod.POST, "/" + BASE_URL + STUDY_ROOM_URL).hasAuthority("Admin")
                .requestMatchers(HttpMethod.DELETE, "/" + BASE_URL + STUDY_ROOM_URL + "/{id}").hasAuthority("Admin")

                .requestMatchers(HttpMethod.PUT, "/" + BASE_URL + LESSON_URL).hasAuthority("ScheduleWriter")
                .requestMatchers(HttpMethod.POST, "/" + BASE_URL + LESSON_URL).hasAuthority("ScheduleWriter")
                .requestMatchers(HttpMethod.PUT, "/" + BASE_URL + LESSON_URL + "/lesson-group").hasAuthority("ScheduleWriter")
                .requestMatchers(HttpMethod.GET, "/" + BASE_URL + LESSON_URL + "/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/" + BASE_URL + LESSON_URL + "/{id}").hasAuthority("ScheduleWriter")
                .requestMatchers(HttpMethod.GET, "/" + BASE_URL + LESSON_URL + "/type").permitAll()

                .requestMatchers(HttpMethod.PUT, "/" + BASE_URL + REQUEST_URL + "/registration/{id}").hasAuthority("ScheduleWriter")
                .requestMatchers(HttpMethod.PUT, "/" + BASE_URL + REQUEST_URL + "/group/{id}").hasAuthority("ScheduleWriter")
                .requestMatchers(HttpMethod.GET, "/" + BASE_URL + REQUEST_URL + "/registration").hasAuthority("ScheduleWriter")
                .requestMatchers(HttpMethod.GET, "/" + BASE_URL + REQUEST_URL + "/group").hasAuthority("ScheduleWriter")

                .requestMatchers(HttpMethod.PUT, "/" + BASE_URL + PROFILE_URL + "/security/password").authenticated()
                .requestMatchers(HttpMethod.PUT, "/" + BASE_URL + PROFILE_URL + "/group").hasAuthority("Student")
                .requestMatchers(HttpMethod.GET, "/" + BASE_URL + PROFILE_URL + "").hasAuthority("Admin")
                .requestMatchers(HttpMethod.GET, "/" + BASE_URL + PROFILE_URL + "/me").hasAnyAuthority("Student", "Teacher")
                .requestMatchers(HttpMethod.PUT, "/" + BASE_URL + PROFILE_URL + "/modify").hasAuthority("Admin")
                .requestMatchers(HttpMethod.POST, "/" + BASE_URL + PROFILE_URL + "/create").hasAuthority("Admin")
                .requestMatchers(HttpMethod.DELETE, "/" + BASE_URL + PROFILE_URL + "/delete").hasAuthority("Admin")


                .requestMatchers(HttpMethod.GET, "/" + BASE_URL + GROUP_URL).permitAll()
                .requestMatchers(HttpMethod.PUT, "/" + BASE_URL + GROUP_URL).hasAuthority("Admin")
                .requestMatchers(HttpMethod.POST, "/" + BASE_URL + GROUP_URL).hasAuthority("Admin")
                .requestMatchers(HttpMethod.DELETE, "/" + BASE_URL + GROUP_URL + "/{id}").hasAuthority("Admin")

                .requestMatchers(HttpMethod.POST, "/" + BASE_URL + AUTHORISATION_URL + "/teacher/sign-up").permitAll()
                .requestMatchers(HttpMethod.POST, "/" + BASE_URL + AUTHORISATION_URL + "/student/sign-up").permitAll()
                .requestMatchers(HttpMethod.POST, "/" + BASE_URL + AUTHORISATION_URL + "/sign-out").authenticated()
                .requestMatchers(HttpMethod.POST, "/" + BASE_URL + AUTHORISATION_URL + "/sign-out-all").authenticated()
                .requestMatchers(HttpMethod.POST, "/" + BASE_URL + AUTHORISATION_URL + "/sign-in").permitAll()

                .requestMatchers(HttpMethod.GET, "/" + BASE_URL + SCHEDULE_URL).hasAnyAuthority("Student", "Teacher")
                .requestMatchers(HttpMethod.GET, "/" + BASE_URL + SCHEDULE_URL + "/teacher/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/" + BASE_URL + SCHEDULE_URL + "/staff").hasAuthority("ScheduleWriter")
                .requestMatchers(HttpMethod.GET, "/" + BASE_URL + SCHEDULE_URL + "/lesson-time").permitAll()
                .requestMatchers(HttpMethod.GET, "/" + BASE_URL + SCHEDULE_URL + "/group/{id}").permitAll()
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

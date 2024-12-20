package edu.miu.cs544.moe.emr.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs544.moe.emr.helper.LocaleMessageProvider;
import edu.miu.cs544.moe.emr.shared.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfig {
    private static final String[] whiteLists = {"/swagger-ui/**", "/v3/api-docs/**", "/error", "/actuator/**"};
    private static final String[] publicPostEndpoints = {
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "/api/v1/auth/refresh",
    };
    private static final String[] publicGetEndpoints = {};
    @Value("${app.security.cors.origins}")
    private List<String> origins;

    private final JwtAuthenticationFilter jwtFilter;
    private final ObjectMapper objectMapper;
    private final LocaleMessageProvider messageProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(whiteLists).permitAll()
                        .requestMatchers(HttpMethod.POST, publicPostEndpoints).permitAll()
                        .requestMatchers(HttpMethod.GET, publicGetEndpoints).permitAll()
                        .anyRequest().permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                            var configuration = new CorsConfiguration();
                            configuration.setAllowedOrigins(this.origins);
                            configuration.setAllowedMethods(List.of("*"));
                            configuration.setAllowedHeaders(List.of("*"));
                            return configuration;
                        })
                ).exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json");
                            response.getWriter().write(this.objectMapper.writeValueAsString(new ErrorResponse(403, messageProvider.getMessage("auth.exceptions.forbidden", null))));
                        })
                        .authenticationEntryPoint(authenticationEntryPoint())
                ); // handle forbidden error and serialize the response
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * This bean is required to handle the unauthorized requests and return proper serialized response.
     * @return AuthenticationEntryPoint
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write(this.objectMapper.writeValueAsString(new ErrorResponse(401, messageProvider.getMessage("auth.exceptions.unauthorized", null))));
        };
    }
}

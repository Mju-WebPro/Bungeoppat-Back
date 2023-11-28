package server.api.webpro.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import server.api.webpro.common.security.CustomAccessDeniedHandler;
import server.api.webpro.common.security.CustomEntryPoint;
import server.api.webpro.common.security.JwtSecurityConfig;
import server.api.webpro.common.security.TokenProvider;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final CustomEntryPoint entryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(c -> c.disable())
                .cors(c -> c.disable())
                .formLogin(c -> c.disable())
                .httpBasic(c -> c.disable())
                .sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(c -> c.frameOptions(f -> f.disable()).disable())
                .authorizeHttpRequests(auth -> {
                    try{auth
                                .requestMatchers("/", "/auth/**","login/oauth2/code/google").permitAll()
                                .requestMatchers( "/swagger-ui/**","/v3/**").permitAll()
                                .anyRequest().authenticated()
                        ;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }).exceptionHandling(c ->
                        c.authenticationEntryPoint(entryPoint).accessDeniedHandler(accessDeniedHandler)
                ).sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .apply(new JwtSecurityConfig(tokenProvider));
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

package com.github.lexilift.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lexilift.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> map = new HashMap<>();
        map.put("bcrypt", new BCryptPasswordEncoder());
        return new DelegatingPasswordEncoder("bcrypt", map);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(customer -> {
            //white list
            customer.requestMatchers(HttpMethod.POST, "/install").permitAll();
            //any request should be authenticated
            //customer.anyRequest().authenticated();
        });

        http.exceptionHandling(customer -> customer.authenticationEntryPoint(authenticationEntryPoint()));

        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    /**
     * this method give a common response for the UNAUTHORIZED request
     * @return return a AuthenticationEntryPoint this method will return a Response for the UNAUTHORIZED request
     */
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, httpServletResponse, exception) -> {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

            // set the response content type
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);

            // set the response character encoding
            httpServletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());

            try(PrintWriter writer = httpServletResponse.getWriter()) {
                Response response = Response.error(HttpStatus.UNAUTHORIZED.value(), "未登录");
                writer.write(objectMapper.writeValueAsString(response));
            }
        };
    }
}

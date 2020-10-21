package com.georgeciachir.primesfinder.config;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.auth0.apiAudience}")
    private String apiAudience;
    @Value("${security.auth0.issuer}")
    private String issuer;
    @Value("${security.cors.allowedOrigins}")
    private List<String> allowedOrigins;
    @Value("${security.exposedEndpoints}")
    private String[] exposedEndpoints;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(allowedOrigins);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(exposedEndpoints)
                .permitAll()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated();

        JwtWebSecurityConfigurer
                .forRS256(apiAudience, issuer)
                .configure(http);
    }
}
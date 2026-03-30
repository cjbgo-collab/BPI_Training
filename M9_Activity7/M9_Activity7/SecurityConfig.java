package M9_Activity7;

import M9_Activity7.security.CustomAccessDeniedHandler;
import M9_Activity7.security.CustomAuthenticationEntryPoint;
import M9_Activity7.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public DaoAuthenticationProvider authProvider(CustomUserDetailsService s) {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(s);
        p.setPasswordEncoder(passwordEncoder());
        return p;
    }

    @Bean
    SecurityFilterChain chain(HttpSecurity http,
                              CustomAuthenticationEntryPoint entry,
                              CustomAccessDeniedHandler denied,
                              CustomUserDetailsService s) throws Exception {

        http.csrf(c -> c.disable());

        http.exceptionHandling(e -> e
                .authenticationEntryPoint(entry)
                .accessDeniedHandler(denied)
        );

        http.authorizeHttpRequests(auth -> auth   
                .requestMatchers("/home").hasAnyRole("USER", "MANAGER")
                .requestMatchers("/dashboard").hasRole("USER")
                .requestMatchers("/reports").hasRole("MANAGER")
                .anyRequest().authenticated()
        );
        

        http.authenticationProvider(authProvider(s));

        return http.build();
    }
    @Bean
    @SuppressWarnings("deprecation")
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
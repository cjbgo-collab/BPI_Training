package M9_Activity6;

import M9_Activity6.service.CustomUserDetailsService;
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
     DaoAuthenticationProvider authProvider(CustomUserDetailsService service) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(service);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,
                                   CustomUserDetailsService service) throws Exception {

        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/login").permitAll()     
                .requestMatchers("/home").hasAnyRole("USER", "MANAGER")
                .requestMatchers("/dashboard").hasRole("USER")
                .requestMatchers("/reports").hasRole("MANAGER")
                .anyRequest().authenticated()
        );

         
        http.formLogin(form -> form
                .defaultSuccessUrl("/home", true)
                .permitAll()
        );

        http.logout(logout -> logout
                .logoutSuccessUrl("/login?logout=true")
                .permitAll()
        );

        http.authenticationProvider(authProvider(service));

        return http.build();
    }

    @Bean
    @SuppressWarnings("deprecation")
     static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
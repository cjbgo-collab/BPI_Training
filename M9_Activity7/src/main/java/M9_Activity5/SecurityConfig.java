package M9_Activity5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Set;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    
    public static final Set<String> VALID_USERNAMES = Set.of(
            "dev_1",
            "dev_2",
            "mgr_1"
    );

    @Bean
    UserDetailsService userDetailsService() {

        var user1 = User.withUsername("dev_1")
                .password("password")
                .roles("USER")
                .build();

        var user2 = User.withUsername("dev_2")
                .password("password")
                .roles("USER")
                .build();

        var manager = User.withUsername("mgr_1")
                .password("password")
                .roles("MANAGER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, manager);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/login").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .successHandler((req, res, auth) -> {
                        var roles = auth.getAuthorities().toString();
                        if (roles.contains("MANAGER")) {
                            res.sendRedirect("/reports");
                        } else {
                            res.sendRedirect("/dashboard");
                        }
                    })
                    .permitAll()
            )
            .logout(logout -> logout.permitAll())
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    @SuppressWarnings("deprecation")
    static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
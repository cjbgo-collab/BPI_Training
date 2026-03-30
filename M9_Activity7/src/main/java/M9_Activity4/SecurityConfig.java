package M9_Activity4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
public class SecurityConfig {

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

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/home").hasAnyRole("USER", "MANAGER")
                .requestMatchers("/dashboard").hasRole("USER")
                .requestMatchers("/reports").hasRole("MANAGER")
                .anyRequest().authenticated()
        )

     
        .formLogin(form -> form
                .successHandler((request, response, authentication) -> {
                    var roles = authentication.getAuthorities().toString();

                    if (roles.contains("MANAGER")) {
                        response.sendRedirect("/reports");
                    } else {
                        response.sendRedirect("/dashboard");
                    }
                })
                .permitAll()
        )

        
        .logout(logout -> logout.permitAll());

        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @SuppressWarnings("deprecation")
    @Bean
      static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}

package M9_Activity7.service;

import M9_Activity7.entity.UserEntity;
import M9_Activity7.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String u)
            throws UsernameNotFoundException {

        UserEntity x = repo.findByUsername(u)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.withUsername(x.getUsername())
                .password(x.getPassword())
                .authorities(x.getRoles().stream().map(r -> r.getName()).toArray(String[]::new))
                .build();
    }
}
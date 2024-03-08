package com.sparta.finalpractice.security;

import com.sparta.finalpractice.security.UserDetailsImpl;
import com.sparta.finalpractice.user.User;
import com.sparta.finalpractice.user.UserRepository;
import com.sparta.finalpractice.user.UserRole;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserDetails(Long userId, UserRole role) throws UsernameNotFoundException {
        return new UserDetailsImpl(userId, role);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Not Found " + email));

        return new UserDetailsImpl(user);
    }
}

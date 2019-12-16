package com.epam.task4.config;

import com.epam.task4.entity.User;
import com.epam.task4.repository.UserRepository;
import com.epam.task4.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserService service;

    public UserDetailService(UserService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = service.findByLogin(login).orElseThrow(() -> new RuntimeException("User not found: " + login));

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (user.getRole() != null) {
            GrantedAuthority authority =
                    new SimpleGrantedAuthority("ROLE_" + user.getRole().getValue());
            grantList.add(authority);
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantList);
    }
}

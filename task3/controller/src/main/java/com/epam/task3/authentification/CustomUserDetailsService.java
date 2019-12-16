package com.epam.task3.authentification;

import com.epam.task3.dao.UserDao;
import com.epam.task3.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao dao;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = dao.read(login);

        if (user == null) {
            throw new UsernameNotFoundException("User " + login + " was not found in the database");
        }

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (user.getRole() != null) {
            GrantedAuthority authority =
                    new SimpleGrantedAuthority("ROLE_" + user.getRole().getValue());
            grantList.add(authority);
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                grantList);
    }
}


package com.max.max.service;

import com.max.max.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminService adminService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // create a user for "randomuser123"/"password".
        Admin admin = adminService.getAdmin(username);
        if (admin.getName().equals(username) && admin.getPassword().equals(admin.getPassword())) {
            return new User(admin.getName(), // username
                    "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", // encoded password
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public UserDetails adminDetalis(String name) {
        Admin admin = adminService.getAdmin(name);
        if (admin.getName().equals(name)) {
            return new User(admin.getName(),
                    "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6", // encoded password
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + name);
        }
    }

}

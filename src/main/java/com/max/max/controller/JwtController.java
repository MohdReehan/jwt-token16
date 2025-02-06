package com.max.max.controller;

import com.max.max.config.TokenManager;
import com.max.max.model.Admin;
import com.max.max.model.JwtRequestModel;
import com.max.max.model.JwtResponseModel;
import com.max.max.service.AdminService;
import com.max.max.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtController {
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private AdminService adminService;

    // Get a JWT Token once user is authenticated, otherwise throw BadCredentialsException
    @PostMapping("/login")
    public ResponseEntity<JwtResponseModel> createToken(@RequestBody JwtRequestModel
                                                                request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        Admin admin = adminService.getAdmin(request.getUsername());
        if (admin.getName().equals(request.getUsername()) && admin.getPassword().equals(request.getPassword())) {
            UserDetails userDetails = userDetailsService.adminDetalis(request.getUsername());
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            final String jwtToken = tokenManager.generateJwtToken(userDetails);
            return ResponseEntity.ok(new JwtResponseModel(jwtToken));
        }
      return ResponseEntity.badRequest().build();
    }
}

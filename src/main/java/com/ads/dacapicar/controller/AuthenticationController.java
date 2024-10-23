package com.ads.dacapicar.controller;

import com.ads.dacapicar.entities.dto.request.AuthenticationRequestDTO;
import com.ads.dacapicar.entities.dto.response.AuthenticationResponseDTO;
import com.ads.dacapicar.service.impl.UserDetailsServiceImpl;
import com.ads.dacapicar.utils.JwtUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@SecurityRequirement(name = "bearerAuth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDTO authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getAuthorities().toString());

        return ResponseEntity.ok(new AuthenticationResponseDTO(jwt));
    }


}

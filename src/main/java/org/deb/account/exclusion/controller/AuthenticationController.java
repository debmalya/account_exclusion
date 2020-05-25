package org.deb.account.exclusion.controller;

import lombok.extern.slf4j.Slf4j;
import org.deb.account.exclusion.component.JwtTokenUtil;
import org.deb.account.exclusion.model.ErrorResponse;
import org.deb.account.exclusion.model.JwtResponse;
import org.deb.account.exclusion.model.UserAuthenticationRequest;
import org.deb.account.exclusion.component.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {


  private final AuthenticationManager authenticationManager;

  private final JwtTokenUtil jwtTokenUtil;

  private final AppUserDetailsService appUserDetailsService;

  @Autowired
  public AuthenticationController(AppUserDetailsService appUserDetailsService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
    this.appUserDetailsService = appUserDetailsService;
    this.authenticationManager = authenticationManager;

    this.jwtTokenUtil = jwtTokenUtil;
  }


  @PostMapping(value = "/authenticate")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody UserAuthenticationRequest userAuthenticationRequest) {
    ResponseEntity<?> responseEntity = null;
    if (StringUtils.isEmpty(userAuthenticationRequest.getUserName()) || StringUtils.isEmpty(userAuthenticationRequest.getPassword())) {
      ErrorResponse errorResponse = new ErrorResponse("Please provide username and password");
      responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    } else {
      try {
        UserDetails userDetails = appUserDetailsService.loadUserByUsername(userAuthenticationRequest.getUserName());
        if (userDetails.getPassword().equals(userAuthenticationRequest.getPassword())) {
          final String token = jwtTokenUtil.generateToken(userDetails);
          responseEntity = ResponseEntity.ok(new JwtResponse(token));
        } else {
          ErrorResponse errorResponse = new ErrorResponse("Either username or password is not valid");
          responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.OK);
        }
      } catch (UsernameNotFoundException unfe) {
        log.error(String.format("User name '%s' not found", userAuthenticationRequest.getUserName()));
        ErrorResponse errorResponse = new ErrorResponse("Either username or password is not valid");
        responseEntity = new ResponseEntity<>(errorResponse, HttpStatus.OK);
      }

    }
    return responseEntity;
  }

}

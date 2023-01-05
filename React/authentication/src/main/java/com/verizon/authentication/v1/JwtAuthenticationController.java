package com.verizon.authentication.v1;

import com.verizon.authentication.config.JwtTokenUtil;
import com.verizon.authentication.controllerService.service.JwtUserDetailsService;
import com.verizon.authentication.v1.Response.AuthResponse;
import com.verizon.authentication.model.UserData;
import com.verizon.authentication.v1.Request.JwtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<AuthResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        AuthResponse authResponse = new AuthResponse();
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        if(userDetails != null){
            final String token = jwtTokenUtil.generateToken(userDetails);

            authResponse.setToken(token);
           authResponse.setUsername(userDetails.getUsername());
            String[] res = UserData.roleName.split("[,]", 0);
            for(String myStr: res) {
                authResponse.setRole(myStr.substring(3));
            }
            authResponse.setStatus("Authenticated");
         authResponse.setResult("Success");
            return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);

        }else{
            authResponse.setStatus("Not Authenticated");
            authResponse.setResult("Failure");
            authResponse.setToken(null);
            authResponse.setRole(null);
            authResponse.setUsername(authenticationRequest.getUsername());
            return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
        }



    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
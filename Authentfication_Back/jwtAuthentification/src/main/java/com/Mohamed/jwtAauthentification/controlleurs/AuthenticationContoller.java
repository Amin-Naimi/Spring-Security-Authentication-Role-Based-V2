package com.Mohamed.jwtAauthentification.controlleurs;

import com.Mohamed.jwtAauthentification.jwt.AuthentificationUserDetailsService;
import com.Mohamed.jwtAauthentification.jwt.JwtRequest;
import com.Mohamed.jwtAauthentification.jwt.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthenticationContoller {

    @Autowired
    AuthentificationUserDetailsService service;

    @PostMapping("/login")
    public JwtResponse createJwt(@RequestBody JwtRequest request)throws Exception{
        return service.createToken(request);
    }

    @GetMapping("/Test")
    public String tes(){
        return "Bonjour mr Amin";
    }
}

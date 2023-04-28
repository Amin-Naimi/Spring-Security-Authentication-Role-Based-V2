package com.Mohamed.jwtAauthentification.jwt;

import com.Mohamed.jwtAauthentification.modals.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse {

    private Users users;
    private String accesToken;

}

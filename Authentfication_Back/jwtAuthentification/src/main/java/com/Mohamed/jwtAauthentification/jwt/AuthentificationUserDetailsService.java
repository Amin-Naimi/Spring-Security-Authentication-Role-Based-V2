package com.Mohamed.jwtAauthentification.jwt;

import com.Mohamed.jwtAauthentification.modals.Users;
import com.Mohamed.jwtAauthentification.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthentificationUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);
        final UserDetails userDetails = loadUserByUsername(userName);
        String jwtToken = jwtUtil.generateToken(userDetails);
        Users utilisateur = userRepository.findByUsername(userName);
        return new JwtResponse(utilisateur, jwtToken);
    }

    private void authenticate(String userName, String userPassword)throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userName, userPassword));
        }catch (DisabledException e){
            throw new Exception("User is disabled");
        }catch (BadCredentialsException e){
            throw new Exception("Bad credentials from user");
        }
    }

    private Set getAuthorities(Users user){
        Set authorities = new HashSet<>();
        user.getRoleSet().forEach(role ->{
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName())
                );
        }
        );
        return authorities;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users utilisateur = userRepository.findByUsername(username);
        if(utilisateur == null) throw new UsernameNotFoundException("Could not able to find the user.");
        return new User(utilisateur.getUsername(), utilisateur.getPassword(), getAuthorities(utilisateur));
    }
}

package com.Mohamed.jwtAauthentification.controlleurs;

import com.Mohamed.jwtAauthentification.modals.Users;
import com.Mohamed.jwtAauthentification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public Users createNewUser(@RequestBody Users users){
        return userService.createNewUser(users);
    }

    /*@PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }*/

    @PreAuthorize("hasRole('Administrateur')")
    @GetMapping("/admin")
    public String forAdmin(){
        return "Welcome Mr Administrateur";
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping("/normal")
    public ResponseEntity<String> forUSer(){
        return new ResponseEntity<>("user spring boot work i love you you are greate ", HttpStatus.OK);
    }
}

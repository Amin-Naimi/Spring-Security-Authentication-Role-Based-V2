package com.Mohamed.jwtAauthentification.controlleurs;

import com.Mohamed.jwtAauthentification.modals.Users;
import com.Mohamed.jwtAauthentification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/admin")
    public String forAdmin(){
        return "Welcome Mr admin";
    }

    @GetMapping("/normal")
    public String forUSer(){
        return "Welcome Mr user";
    }
}

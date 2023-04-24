package com.Mohamed.jwtAauthentification.controlleurs;

import com.Mohamed.jwtAauthentification.modals.Users;
import com.Mohamed.jwtAauthentification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }
}

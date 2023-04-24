package com.Mohamed.jwtAauthentification.controlleurs;

import com.Mohamed.jwtAauthentification.modals.Roles;
import com.Mohamed.jwtAauthentification.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @PostMapping("/create")
    public Roles createNewRole(@RequestBody Roles role){
        return roleService.createNewRole(role);
    }
}

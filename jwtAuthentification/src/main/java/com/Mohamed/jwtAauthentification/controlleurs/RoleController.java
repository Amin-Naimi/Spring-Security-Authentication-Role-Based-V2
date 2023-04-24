package com.Mohamed.jwtAauthentification.controlleurs;

import com.Mohamed.jwtAauthentification.modals.Roles;
import com.Mohamed.jwtAauthentification.services.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.Mohamed.jwtAauthentification.utiles.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT+"/app")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role/create")
    public Roles createNewRole(@RequestBody Roles roles){
        return roleService.createNewRole(roles);
    }
}

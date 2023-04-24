package com.Mohamed.jwtAauthentification.service;

import com.Mohamed.jwtAauthentification.modals.Roles;
import com.Mohamed.jwtAauthentification.repositorys.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RolesRepository rolesRepository;
    public Roles createNewRole(Roles roles){
        return rolesRepository.save(roles);
    }
}

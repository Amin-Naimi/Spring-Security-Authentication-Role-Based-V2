package com.Mohamed.jwtAauthentification.service;

import com.Mohamed.jwtAauthentification.modals.Roles;
import com.Mohamed.jwtAauthentification.modals.Users;
import com.Mohamed.jwtAauthentification.repositorys.RolesRepository;
import com.Mohamed.jwtAauthentification.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users createNewUser(Users users){
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        return userRepository.save(users);
    }

   /* public void initRoleAndUser() {

        Roles adminRole = new Roles();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        rolesRepository.save(adminRole);

        Roles userRole = new Roles();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        rolesRepository.save(userRole);

        Users adminUser = new Users();
        adminUser.setUsername("Goku78");
        adminUser.setPassword(passwordEncoder.encode("admin@SS"));
        adminUser.setUserFirstName("Kakaroto");
        adminUser.setUserLastName("Songoku");
        Set<Roles> adminRoles = new HashSet<>();

        adminRoles.add(adminRole);
        adminUser.setRoleSet(adminRoles);
        userRepository.save(adminUser);

        Users user = new Users();
        user.setUsername("MohamdNaimi");
        user.setPassword(passwordEncoder.encode("NAio7825"));
        user.setUserFirstName("Amin");
        user.setUserLastName("Naimi");
        Set<Roles> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRoleSet(userRoles);
        userRepository.save(user);
    }*/

}

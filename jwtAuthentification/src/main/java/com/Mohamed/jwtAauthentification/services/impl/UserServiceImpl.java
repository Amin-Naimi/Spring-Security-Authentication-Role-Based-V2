package com.Mohamed.jwtAauthentification.services.impl;

import com.Mohamed.jwtAauthentification.exception.ResourceNotFoundException;
import com.Mohamed.jwtAauthentification.modals.Roles;
import com.Mohamed.jwtAauthentification.modals.Users;
import com.Mohamed.jwtAauthentification.repositorys.RolesRepository;
import com.Mohamed.jwtAauthentification.repositorys.UserRepository;
import com.Mohamed.jwtAauthentification.services.UserService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<Users> findByUserId(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User with Id" + userId.toString()+ "not found in DB"));
        return ResponseEntity.ok().body(user);
    }

    @Override
    public ResponseEntity<Users> createUser(Users user) {
        return ResponseEntity.ok(userRepository.save(user));
    }

    @Override
    public ResponseEntity<Users> updateUser(Users userData, Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User with Id" + userId.toString()+ "not found in DB"));
        user.setUsername(userData.getUsername());
        user.setPassword(user.getPassword());
        return ResponseEntity.ok(userRepository.save(user));
    }

    @Override
    public ResponseEntity<String> deleteUserById(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User with Id" + userId.toString()+ "not found in DB"));
        userRepository.deleteById(user.getId());
        return ResponseEntity.ok().body("User id: "+userId+" is deleted.");
    }

    public void initRolesAndUser()
    {
        Roles adminRole = new Roles();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin Role");
        rolesRepository.save(adminRole);

        Roles userRole = new Roles();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        rolesRepository.save(userRole);

        Users adminUser = new Users();
        adminUser.setUserLastName("Naimi");
        adminUser.setUserFirstName("Mohamed");
        adminUser.setUsername("adminNaimi");
        adminUser.setPassword("admin@784");
        Set<Roles> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoleSet(adminRoles);
        userRepository.save(adminUser);

        Users normalUser = new Users();
        adminUser.setUserLastName("Malika");
        adminUser.setUserFirstName("Jali");
        adminUser.setUsername("userMalika");
        adminUser.setPassword("7484dsff@784");
        Set<Roles> userRoles = new HashSet<>();
        userRoles.add(userRole);
        normalUser.setRoleSet(userRoles);
        userRepository.save(adminUser);

    }
    @GetMapping("/forAdmin")
    public String forAdmin(){
        return "Welcome mr Admin";
    }

    @GetMapping("/forUser")
    public String forUser(){
        return "Welcome mr User";
    }
}

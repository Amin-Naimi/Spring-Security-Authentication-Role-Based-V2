package com.Mohamed.jwtAauthentification.modals;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="user_name")
    private String username;

    @Column(name="user_First_Name")
    private String userFirstName;

    @Column(name="user_Last_Name")
    private String userLastName;

    private String password;

    //@ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @ManyToMany(cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Roles> roleSet;
}

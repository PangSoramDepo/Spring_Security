package com.pangsoramdepo.rbac.model;

import java.util.Set;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class AccessControl {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String api;

    private String accessType;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "role_access_control",
            joinColumns = {
            @JoinColumn(name = "access_control_id")
            },
            inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private Set<Role> roles;
    
}

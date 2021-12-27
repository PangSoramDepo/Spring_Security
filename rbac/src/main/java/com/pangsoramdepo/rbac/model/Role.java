package com.pangsoramdepo.rbac.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String description;

}
/*
 * File: src\main\java\com\taylor\springsecurityjpa\orm\entities\User.java
 * Project: spring-security-jpa
 * Created Date: Tuesday, December 26th 2023, 8:12:23 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Thursday, 11th January 2024 6:40:26 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Tuesday, December 26th 2023	Rui Yu		Initial version
 */

package com.taylor.springsecurityjpa.orm.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users", schema = "public")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Setter
    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Setter
    @Column(name = "password", nullable = false)
    private String password;

    @Setter
    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    // The default constructor exists only for the sake of JPA.
    protected User() {
    }

    public User(String name, String password, Boolean isActive, List<Role> roles) {
        this.name = name;
        this.password = password;
        this.isActive = isActive;

        roles.stream()
                .forEach(r -> this.roles.add(r));
    }
}
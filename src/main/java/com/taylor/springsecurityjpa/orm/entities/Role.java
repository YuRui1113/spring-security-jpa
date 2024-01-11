/*
 * File: src\main\java\com\taylor\springsecurityjpa\orm\entities\Role.java
 * Project: spring-security-jpa
 * Created Date: Tuesday, December 26th 2023, 9:42:31 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Thursday, 11th January 2024 6:40:20 pm
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
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "roles", schema = "public")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    @Setter
    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    // The default constructor exists only for the sake of JPA.
    protected Role() {
    }

    public Role(String name) {
        this.setName(name);
    }
}
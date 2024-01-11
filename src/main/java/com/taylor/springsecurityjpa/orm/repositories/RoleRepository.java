/*
 * File: src\main\java\com\taylor\springsecurityjpa\orm\repositories\RoleRepository.java
 * Project: spring-security-jpa
 * Created Date: Tuesday, January 2nd 2024, 10:13:30 am
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Thursday, 11th January 2024 6:40:53 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2024 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Tuesday, January 2nd 2024	Rui Yu		Initial version
 */

package com.taylor.springsecurityjpa.orm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taylor.springsecurityjpa.orm.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {    

    Optional<Role> findByName(String name);
} 
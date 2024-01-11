/*
 * File: src\main\java\com\taylor\springsecurityjpa\orm\repositories\UserRepository.java
 * Project: spring-security-jpa
 * Created Date: Tuesday, December 26th 2023, 8:19:52 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Thursday, 11th January 2024 6:40:56 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Tuesday, December 26th 2023	Rui Yu		Initial version
 */

package com.taylor.springsecurityjpa.orm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taylor.springsecurityjpa.orm.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);
}
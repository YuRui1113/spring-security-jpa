/*
 * File: src\main\java\com\taylor\springsecurityjpa\services\UserDetailsServiceImpl.java
 * Project: spring-security-jpa
 * Created Date: Tuesday, December 26th 2023, 8:46:01 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Thursday, 11th January 2024 6:41:14 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Tuesday, December 26th 2023	Rui Yu		Initial version
 */

package com.taylor.springsecurityjpa.services;

import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taylor.springsecurityjpa.orm.entities.User;
import com.taylor.springsecurityjpa.orm.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> existedUser = this.userRepository.findByName(userName);
        existedUser.orElseThrow(() -> new UsernameNotFoundException(userName + " 没找到。"));

        Hibernate.initialize(existedUser.get().getRoles());
        return existedUser.map(UserDetailsImpl::new).get();
    }
}
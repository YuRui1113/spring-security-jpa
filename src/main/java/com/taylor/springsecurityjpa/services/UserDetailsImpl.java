/*
 * File: src\main\java\com\taylor\springsecurityjpa\services\UserDetailsImpl.java
 * Project: spring-security-jpa
 * Created Date: Tuesday, December 26th 2023, 8:34:21 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Thursday, 11th January 2024 6:41:06 pm
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.taylor.springsecurityjpa.orm.entities.Role;
import com.taylor.springsecurityjpa.orm.entities.User;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> userRoles = user.getRoles();

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (Role userRole : userRoles) {
            authorities.add(new SimpleGrantedAuthority(userRole.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.user.getIsActive();
    }
}
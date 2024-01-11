/*
 * File: src\main\java\com\taylor\springsecurityjpa\controllers\LoginController.java
 * Project: spring-security-jpa
 * Created Date: Monday, December 25th 2023, 4:50:57 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Thursday, 11th January 2024 6:40:06 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Monday, December 25th 2023	Rui Yu		Initial version
 */

package com.taylor.springsecurityjpa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
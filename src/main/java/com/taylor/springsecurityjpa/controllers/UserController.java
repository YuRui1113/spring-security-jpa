/*
 * File: src\main\java\com\taylor\springsecurityjpa\controllers\UserController.java
 * Project: spring-security-jpa
 * Created Date: Monday, December 25th 2023, 4:52:44 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Thursday, 11th January 2024 6:40:13 pm
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
public class UserController {

    @GetMapping("/user/index")
    public String userIndex() {
        return "user/index";
    }
}
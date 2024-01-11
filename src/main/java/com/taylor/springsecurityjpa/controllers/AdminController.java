/*
 * File: src\main\java\com\taylor\springsecurityjpa\controllers\AdminController.java
 * Project: spring-security-jpa
 * Created Date: Tuesday, December 26th 2023, 2:59:06 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Thursday, 11th January 2024 6:39:52 pm
 * Modified By: Rui Yu (yurui_113@hotmail.com>)
 * -----
 * Copyright (c) 2023 Rui Yu
 * -----
 * HISTORY:
 * Date                     	By       	Comments
 * -------------------------	---------	----------------------------------------------------------
 * Tuesday, December 26th 2023	Rui Yu		Initial version
 */

package com.taylor.springsecurityjpa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/index")
    public String adminIndex() {
        return "admin/index";
    }
}
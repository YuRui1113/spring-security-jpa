/*
 * File: src\main\java\com\taylor\springsecurityjpa\controllers\HomeController.java
 * Project: spring-security-jpa
 * Created Date: Monday, December 25th 2023, 4:48:55 pm
 * Author: Rui Yu (yurui_113@hotmail.com)
 * -----
 * Last Modified: Thursday, 11th January 2024 6:39:59 pm
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return "redirect:index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/anonymous")
    public String anonymous(Model model) {
        model.addAttribute("helloText", "欢迎来到我的主页！");
        return "anonymous";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
}
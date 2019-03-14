/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bh08.movieproject.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Zoli
 */
@Controller
public class LogoutController {
    
    @RequestMapping(value = "mainpage/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "successful_logout.html";
    }
}

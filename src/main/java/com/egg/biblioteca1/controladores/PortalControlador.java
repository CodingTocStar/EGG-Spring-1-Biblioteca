/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Shimbo
 */
@Controller
@RequestMapping("/")

public class PortalControlador {

    @GetMapping("/") //esto va a hacer que se ejecute el metodo de todo nuestro cuerpo
    public String index() {

        return "index.html";

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca1.controladores;

import com.egg.biblioteca1.excepciones.MiException;
import com.egg.biblioteca1.servicios.EditorialServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Shimbo
 */
@Controller
@RequestMapping("/editorial") //localhost:8080/editorial
public class EditorialControlador {

    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar") //localhost:8080/editorial/registrar -- aca es donde entra
    public String registrar() {
        return "editorial_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) { // requestparam indica que el parametro recibido viaja por la url | llega cuando se ejecute el formulario
        System.out.println("Nombre editorial: " + nombre);

        try {
            editorialServicio.crearEditorial(nombre);
            
            modelo.put("exito", "La editorial se cargo con exito!");
            
        } catch (MiException ex) {
            //Logger.getLogger(EditorialControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());
            
            return "editorial_form.html";
        }
        return "index.html";
    }
}

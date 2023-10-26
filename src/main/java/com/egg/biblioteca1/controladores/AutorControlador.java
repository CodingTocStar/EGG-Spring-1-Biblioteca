/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca1.controladores;

import com.egg.biblioteca1.entidades.Autor;
import com.egg.biblioteca1.excepciones.MiException;
import com.egg.biblioteca1.servicios.AutorServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Shimbo
 */
@Controller
@RequestMapping("/autor") //localhost:8080/autor

public class AutorControlador {

    @Autowired
    private AutorServicio autorServicio;

    @GetMapping("/registrar") //localhost:8080/autor/registrar
    public String registrar() {
        return "autor_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required = false) String nombre, ModelMap modelo) {

        try {
            //antes         //System.out.println(nombre);
            autorServicio.crearAutor(nombre);

            modelo.put("exito", "El autor se cargo con exito!");

        } catch (MiException ex) {
            //Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);  //le muestra al usuario por consola el error
            //System.out.println("ERRORRRR CAGASTEEE");

            modelo.put("error", ex.getMessage());
            return "autor_form.html";

        } catch (Exception excepcion) {

            modelo.put("error", excepcion.getMessage());
            return "autor_form.html";
//System.out.println(excepcion.getMessage());
        }
        modelo.put("exito", "El autor se cargo con exito!");
        //return "autor_form.html";
        return "index.html";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {

        List<Autor> autores = autorServicio.listarAutores();

        modelo.addAttribute("autores", autores);

        return "autor_list.html";
    }

    @GetMapping("/modificar/{id}") //usamos la notacion path(variable) || le mandamos el id por la url
    public String modificar(@PathVariable String id, ModelMap modelo) { //con el pathvariable le digo a spring que el id viaja en la url

        modelo.put("autor", autorServicio.getOne(id));
        
        return "autor_modificar.html";
    }
    
    @PostMapping("/modificar/{id}")
    public String modificar(@PathVariable String id, String nombre, ModelMap modelo){
    
        try {
            autorServicio.modificarAutor(nombre, id);
            
            return "redirect:../lista"; // si sale todo bien que se redireccione aca
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "autor_modificar.html";
        }
    }
}

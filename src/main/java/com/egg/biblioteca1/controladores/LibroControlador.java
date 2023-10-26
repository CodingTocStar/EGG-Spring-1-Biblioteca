/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca1.controladores;

import com.egg.biblioteca1.entidades.Autor;
import com.egg.biblioteca1.entidades.Editorial;
import com.egg.biblioteca1.entidades.Libro;
import com.egg.biblioteca1.excepciones.MiException;
import com.egg.biblioteca1.servicios.AutorServicio;
import com.egg.biblioteca1.servicios.EditorialServicio;
import com.egg.biblioteca1.servicios.LibroServicio;
import java.util.List;
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
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;

    //--------------------------------------------------
    //getmapping se encarga de renderizarla pagina
    @GetMapping("/registrar") //localhost:8080/libro/registrar
    public String registrar(ModelMap modelo) {
        List<Autor> autores = autorServicio.listarAutores();
        List<Editorial> editoriales = editorialServicio.listarEditorial();

        modelo.addAttribute("autores", autores);
        modelo.addAttribute("editoriales", editoriales);

        return "libro_form.html";

    }

    @PostMapping("/registro")                   //le pongo requerido falso para que entre al metodo y manejo la excepcion que es nulo el isbn 
    public String registro(@RequestParam(required = false) Long isbn, @RequestParam String titulo,
            @RequestParam(required = false) Integer ejemplares, @RequestParam(required = false) String idAutor,
            @RequestParam(required = false) String idEditorial, ModelMap modelo) {

        try {
            libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);

            modelo.put("exito", "El libro se cargo con exito!");

        } catch (MiException ex) {
            //ya no lo mandamos por consola  //Logger.getLogger(LibroControlador.class.getName()).log(Level.SEVERE, null, ex);
            modelo.put("error", ex.getMessage());//el mensaje de todo se fue a la MIERDA!

            //INYECTO DE NUEVO LOS DATOS
            List<Autor> autores = autorServicio.listarAutores();
            List<Editorial> editoriales = editorialServicio.listarEditorial();

            modelo.addAttribute("autores", autores);
            modelo.addAttribute("editoriales", editoriales);
            //=================================================
            return "libro_form.html";
        }

        return "index.html";
    }
    
    
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
    
        List<Libro> libros = libroServicio.listarLibros();
        modelo.addAttribute("libros", libros);
        
        return "libro_list.html";
    }
}

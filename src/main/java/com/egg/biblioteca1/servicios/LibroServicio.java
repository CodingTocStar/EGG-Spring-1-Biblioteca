/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca1.servicios;

import com.egg.biblioteca1.entidades.Autor;
import com.egg.biblioteca1.entidades.Editorial;
import com.egg.biblioteca1.entidades.Libro;
import com.egg.biblioteca1.excepciones.MiException;
import com.egg.biblioteca1.repositorio.AutorRepositorio;
import com.egg.biblioteca1.repositorio.EditorialRepositorio;
import com.egg.biblioteca1.repositorio.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Shimbo
 */
@Service
public class LibroServicio {

    //AUTOWIRED
    @Autowired
    private LibroRepositorio libroRepositorio;

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Autowired
    private EditorialRepositorio editorialRepositorio;

    //METODOS
    @Transactional //si generas un cambio permanente tiene que ser transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException {

        //VALIDAMOS ESTE TODO BIEN
        validar(isbn, titulo, idAutor, idEditorial, ejemplares);

        //SI ALGO FALLA NO SE EJECUTA LO DEMAS
        //=================================================
        
        Autor autor = autorRepositorio.findById(idAutor).get();

        Editorial editorial = editorialRepositorio.findById(idEditorial).get();

        Libro libro = new Libro();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);

        libro.setAlta(new Date());

        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);
    }

    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList();
        libros = libroRepositorio.findAll();

        return libros;
    }

    public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException{

        validar(isbn, titulo, idAutor, idEditorial, ejemplares);
        
        //Libro libro = libroRepositorio.findById(isbn).get();
        //con el Optional nos aseguramos que este lo que buscamos, y si esta todo bien nos devuelve true el Optional
        // google bard --> Optional es un tipo de dato contenedor que puede representar la presencia o ausencia de un valor.
        //Se utiliza para manejar valores nulos de forma segura. En Java, los valores nulos pueden causar errores inesperados, ya que no se puede 
        //distinguir entre un valor nulo y un valor que simplemente no existe. 
        //Optional permite tratar los valores nulos como una condición especial, lo que ayuda a evitar errores.
        Optional<Libro> respuestaLibro = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

        //instanciamos los objetos vacios
        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        //============================================================
        //chequeamos si sale todo bien

        if (respuestaAutor.isPresent()) {

            autor = respuestaAutor.get(); //con esto sabemos que existe el autor y no nos mandamos cagadas

        }

        if (respuestaEditorial.isPresent()) {

        }

        if (respuestaLibro.isPresent()) { //me dice que si la respuesta esta entonces acciona

            Libro libro = respuestaLibro.get(); //como todo salio bien entonces me trae los datos del repo

            libro.setTitulo(titulo);

            libro.setAutor(autor);

            libro.setEditorial(editorial);

            libro.setEjemplares(ejemplares);

            libroRepositorio.save(libro);
        }
    }
    
    private void validar(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws MiException{
    
         if (isbn == null) {
            throw new MiException("El isbn no puede ser nulo.");
        }
         
         if(titulo.isEmpty() || titulo == null){
         
             throw new MiException("El titulo no puede ser nulo o estar vacio.");
         }

        if (ejemplares == null) {

            throw new MiException("Los ejemplares no puede ser nulo o estar vacio.");
        }

        if (idAutor.isEmpty() || idAutor == null) {
            throw new MiException("El autor no puede ser nulo.");
        }

        if (idEditorial.isEmpty() || idEditorial == null) {

            throw new MiException("La editorial no puede ser nulo o estar vacio.");
        }
        
    }
}

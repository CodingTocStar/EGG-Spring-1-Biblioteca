/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca1.servicios;

import com.egg.biblioteca1.entidades.Autor;
import com.egg.biblioteca1.excepciones.MiException;
import com.egg.biblioteca1.repositorio.AutorRepositorio;
import java.util.ArrayList;
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
public class AutorServicio {

    @Autowired
    AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MiException {


        //valido
        validar(nombre);
        //lo que sigue
        Autor autor = new Autor();

        autor.setNombre(nombre);

        autorRepositorio.save(autor);

    }

    public List<Autor> listarAutores() {

        List<Autor> autores = new ArrayList();      //hago una lista con el objeto Autor, lo llamo autores e instancio un nuevo arraylist
        autores = autorRepositorio.findAll();       //uso los metodos heredados de Jpa en el repo

        return autores;     //devuelvo los autores
    }

    public void modificarAutor(String nombre, String id) throws MiException {

        //valido
        validar(nombre);
        //======================
        Optional<Autor> respuestaAutor = autorRepositorio.findById(id);

        if (respuestaAutor.isPresent()) {

            Autor autor = respuestaAutor.get();

            autor.setNombre(nombre);

            autorRepositorio.save(autor);
        }
    }
    
    public Autor getOne(String id){
        return autorRepositorio.getOne(id); // le pedimos el id y nos retorna el autor que tiene asignado
    }

    public void validar(String nombre) throws MiException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El nombre del autor no puede ser nulo.");
        }

    }
}

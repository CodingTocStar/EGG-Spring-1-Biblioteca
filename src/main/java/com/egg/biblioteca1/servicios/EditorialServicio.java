/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca1.servicios;

import com.egg.biblioteca1.entidades.Editorial;
import com.egg.biblioteca1.excepciones.MiException;
import com.egg.biblioteca1.repositorio.EditorialRepositorio;
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
public class EditorialServicio {

    @Autowired
    EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre) throws MiException {

        //valido
        validar(nombre);
        //======================
        
        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);

    }

    public List<Editorial> listarEditorial() {

        List<Editorial> editoriales = new ArrayList();
        editoriales = editorialRepositorio.findAll();

        return editoriales;
    }

    public void modificarEditorial(String id, String nombre) throws MiException {
        
        //validar
        validar(nombre);

        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(id);

        if (respuestaEditorial.isPresent()) {

            Editorial editorial = respuestaEditorial.get();

            editorial.setNombre(nombre);

            editorialRepositorio.save(editorial);
        }
    }

    public void validar(String nombre) throws MiException {

        if (nombre.isEmpty() || nombre == null) {
            throw new MiException("El nombre de la editorial no puede ser nulo.");
        }
    }
}

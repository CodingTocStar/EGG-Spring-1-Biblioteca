/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca1.repositorio;

import com.egg.biblioteca1.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Shimbo
 */
@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")// String titulo hace referencia al ultimo titulo, al que estamos comparando
    public Libro BuscarPorTitulo(@Param("titulo") String titulo);//Parametro "titulo" hace referencia al libro encontrado.

    @Query("SELECT l FROM Libro l WHERE l.autor.nombre = :nombre")
    public List<Libro> buscarPorAutor(@Param("nombre") String nombre);

}

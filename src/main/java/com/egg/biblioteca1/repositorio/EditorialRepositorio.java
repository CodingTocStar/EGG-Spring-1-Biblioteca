/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca1.repositorio;

import com.egg.biblioteca1.entidades.Editorial;
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
public interface EditorialRepositorio extends JpaRepository<Editorial, String> {

    @Query("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
    public List<Editorial> buscarPorNombre(@Param("nombre") String nombre);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.biblioteca1.excepciones;

/**
 *
 * @author Shimbo
 */
public class MiException extends Exception {
    
    public MiException(String mensaje){
    
        super(mensaje); //generamos esta clase para diferencias los errores de la logica de negocio de los del sistema
        
    }
}


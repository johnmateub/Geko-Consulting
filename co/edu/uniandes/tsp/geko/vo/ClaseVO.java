/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.tsp.geko.vo;

/**
 *
 * 
 */
public class ClaseVO {
    String nombre;
    String ruta;
    Integer numeroLineas;
    Integer numeroMetodos;
    Integer numeroComentarios;
    Integer numeroEspacios;
    Integer numeroComentsJdoc;
    Integer corchAbiertos;
 
    public ClaseVO(){
     numeroLineas = 0;
     numeroMetodos = 0;
     numeroComentarios = 0;
     numeroEspacios = 0;
     numeroComentsJdoc = 0;
     corchAbiertos = 0;
    }
    
    public void sumarLinea(){
        numeroLineas++;
    }
    
    public void sumarNumeroMetodos(){
        numeroMetodos++;
    }
    
    public void sumarNumeroComentarios(){
        numeroComentarios++;
    }
    public void sumarNumeroEspacios(){
        numeroEspacios++;
    }
    public void sumarNumeroComentsJdoc(){
        numeroComentsJdoc++;
    }
    public void restarNumeroComentsJdoc(){
        numeroComentsJdoc--;
    }
    public void sumarCorchAbiertos(){
        corchAbiertos++;
    }
    public void restarCorchAbiertos(){
        corchAbiertos--;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Integer getNumeroLineas() {
        return numeroLineas;
    }

    public void setNumeroLineas(Integer numeroLineas) {
        this.numeroLineas = numeroLineas;
    }

    public Integer getNumeroMetodos() {
        return numeroMetodos;
    }

    public void setNumeroMetodos(Integer numeroMetodos) {
        this.numeroMetodos = numeroMetodos;
    }

    public Integer getNumeroComentarios() {
        return numeroComentarios;
    }

    public void setNumeroComentarios(Integer numeroComentarios) {
        this.numeroComentarios = numeroComentarios;
    }

    public Integer getNumeroEspacios() {
        return numeroEspacios;
    }

    public void setNumeroEspacios(Integer numeroEspacios) {
        this.numeroEspacios = numeroEspacios;
    }

    public Integer getNumeroComentsJdoc() {
        return numeroComentsJdoc;
    }

    public void setNumeroComentsJdoc(Integer numeroComentsJdoc) {
        this.numeroComentsJdoc = numeroComentsJdoc;
    }

    public Integer getCorchAbiertos() {
        return corchAbiertos;
    }

    public void setCorchAbiertos(Integer corchAbiertos) {
        this.corchAbiertos = corchAbiertos;
    }
    
}

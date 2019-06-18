/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entorno;

import java.util.LinkedList;

/**
 *
 * @author p_ab1
 */
public class Simbolo {
    private String identificador;
    private Object valor;
    private Tipo tipo; 
    private Rol rol;
    
    public Simbolo(String identificador) {
        this.identificador = identificador;
    }
    
    public Simbolo(String identificador,Rol rol,Tipo tipo) {
        this.identificador = identificador;
        this.tipo = tipo;
        this.rol = rol;
    }
    
    public Simbolo(String identificador, Tipo tipo,int dimensionArreglo){
        this.identificador = identificador;
        this.tipo = tipo;        
    
    }
    
    public Simbolo(String identificador, Tipo tipo){
        this.identificador = identificador;
        this.tipo = tipo;
    }
    
     public Simbolo(String identificador, Rol rol){
        this.identificador = identificador;
        this.rol = rol;
    }

    public Simbolo(String identificador, Object valor, Tipo tipo ) {
        this.identificador = identificador;
        this.valor = valor;
        this.tipo = tipo;
        
    }
    
    public Simbolo(String identificador, Object valor, Tipo tipo,Rol rol) {
        this.identificador = identificador;
        this.valor = valor;
        this.tipo = tipo;
        this.rol = rol;
        
    }
    
     public Simbolo(String identificador, Tipo tipo,Rol rol) {
        this.identificador = identificador;
        this.tipo = tipo;
        this.rol = rol;
        
    }
     
    public Simbolo(String identificador, Object valor, Tipo tipo, LinkedList<Simbolo> listaParametros) {
        this.identificador = identificador;
        this.valor = valor;
        this.tipo = tipo;
        
    }

    public enum Rol{
        ARREGLO,
        VARIABLE,
        FUNCION,
        MAIN,
        CLASE,
        CONSTRUCTOR,
        OBJETO
    }
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }    
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}

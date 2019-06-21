/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.Nativo.Modificador;
import ast.general.Instruccion;
import entorno.Entorno;
import entorno.Simbolo;
import java.util.LinkedList;

/**
 *
 * @author p_ab1
 */
public class Clase extends Simbolo implements Instruccion{
    //lista de modificadores
    private LinkedList<Modificador> modificadores;    
    //lista de Instrucciones globales
    private LinkedList<Instruccion> Instrucciones;
    //lista de funciones funciones
    private LinkedList<Funcion> funciones;
    //lista de constructures cons
    private LinkedList<Constructor> constructores;
    private LinkedList<Clase> jerarqua;
    private String Lextend;
    int linea;
    int col;
    public Clase(Rol rol,LinkedList<Modificador> modificadores, LinkedList<Instruccion> Instrucciones, LinkedList<Funcion> funciones, LinkedList<Constructor> constructores, String identificador,LinkedList<Clase>jerarquia ) {
        super(identificador,rol);
        this.modificadores = modificadores;
        this.Instrucciones = Instrucciones;
        this.funciones = funciones;
        this.constructores = constructores;
        this.jerarqua = jerarquia;
        this.Lextend = null;
//        this.linea = fila;
//this.col = col;
    }
    
    public Clase(LinkedList<Modificador> modificadores, LinkedList<Instruccion> Instrucciones, LinkedList<Funcion> funciones, LinkedList<Constructor> constructores, String identificador,LinkedList<Clase>jerarquia,String Lextend) {
        super(identificador);
        this.modificadores = modificadores;
        this.Instrucciones = Instrucciones;
        this.funciones = funciones;
        this.constructores = constructores;
        this.jerarqua = jerarquia;
        this.Lextend = Lextend;
//        this.linea = fila;
//this.col = col;
    }

    @Override
    public Object ejecutar(Entorno ent) {
        for (Funcion funcion : funciones) {
            if (funcion.getRol() == Rol.MAIN) 
            {
                Object result = funcion.ejecutar(ent);
                if (result != null) {
                    return result;
                }
            }
        }
        //if main != null
        //main ejecutar
        //return null;
        return null;
    }

    @Override
    public int linea() {
        return linea;
    }

    public LinkedList<Modificador> getModificadores() {
        return modificadores;
    }

    public void setModificadores(LinkedList<Modificador> modificadores) {
        this.modificadores = modificadores;
    }

    public LinkedList<Instruccion> getInstrucciones() {
        return Instrucciones;
    }

    public void setInstrucciones(LinkedList<Instruccion> Instrucciones) {
        this.Instrucciones = Instrucciones;
    }

    public LinkedList<Funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(LinkedList<Funcion> funciones) {
        this.funciones = funciones;
    }

    public LinkedList<Constructor> getConstructores() {
        return constructores;
    }

    public void setConstructores(LinkedList<Constructor> constructores) {
        this.constructores = constructores;
    }

    public LinkedList<Clase> getJerarqua() {
        return jerarqua;
    }

    public void setJerarqua(LinkedList<Clase> jerarqua) {
        this.jerarqua = jerarqua;
    }

    public String getLextend() {
        return Lextend;
    }

    public void setLextend(String Lextend) {
        this.Lextend = Lextend;
    }
        @Override
    public int columana() {
        return col;
    }
    
}

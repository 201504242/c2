/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.general.Instruccion;
import entorno.Entorno;

/**
 *
 * @author p_ab1
 */
public class Continue implements Instruccion{
    int linea;
    int col;

    public Continue() {
//        this.linea = linea;
//        this.col = col;
    }
    
    @Override
    public int columana() {
        return col;
    }
    @Override
    public Object ejecutar(Entorno ent) {
        return null;
    }

    @Override
    public int linea() {
        return linea;
    }
    
}

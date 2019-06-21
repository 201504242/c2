/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.general.Expresion;
import entorno.Entorno;
import entorno.Tipo;

/**
 *
 * @author p_ab1
 */
public class Literal implements Expresion{
    Object valor;
    Tipo tipo;
    int linea;
    int col;

    public Literal(Object valor, Tipo tipo) {
        this.valor = valor;
        this.tipo = tipo;
//        this.linea = fila;
//        this.col = col;
    }
    
    @Override
    public Tipo getTipo(Entorno ent) {
        //Object val = this.getValorImplicito(ent);
        return this.tipo;        
    }

    @Override
    public Object getValorImplicito(Entorno ent) {
        return valor;
    }

    @Override
    public int linea() {
        return linea;
    }
        @Override
    public int columana() {
        return col;
    }
}

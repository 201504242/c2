/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.general.Expresion;
import ast.general.Instruccion;
import entorno.Entorno;
import inteprete.Ventana;

/**
 *
 * @author p_ab1
 */
public class Println implements Instruccion{
    private Expresion valor;
    int linea;
    int col;

    @Override
    public int columana() {
        return col;
    }
    public Println(Expresion valor) {
        this.valor = valor;
//        this.linea = fila;
//this.col = col;
    }
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        Object o = valor.getValorImplicito(ent);
        if (o != null) 
        {
            Ventana.getVentana().agregarConsolaln(" "+o.toString());
            System.out.println(" "+o.toString());
        }
        else
        {
            Ventana.getVentana().agregarConsolaln("Valor null");  
            System.out.println("Valor null");
        }
        return null;
    }

    @Override
    public int linea() {
        return linea;
    }
    
}


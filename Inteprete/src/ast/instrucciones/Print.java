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
public class Print implements Instruccion{
    private Expresion valor;
    int linea;
    int col;

    @Override
    public int columana() {
        return col;
    }
    public Print(Expresion valor) {
        this.valor = valor;
    }
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        Object o = valor.getValorImplicito(ent);
        if (o != null) 
        {
            Ventana.getVentana().agregarConsola(""+o.toString());
            System.out.println("-----PRINT: "+o.toString());
        }
        else
        {
            Ventana.getVentana().agregarConsola("Valor null");  
            System.out.println("-----PRINT: null");
        }
        return null;
    }

    @Override
    public int linea() {
        return linea;
    }
    
}

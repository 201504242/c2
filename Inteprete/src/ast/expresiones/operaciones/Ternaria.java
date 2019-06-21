/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.operaciones;

import ast.general.Expresion;
import entorno.Entorno;
import entorno.Tipo;

/**
 *
 * @author p_ab1
 */
public class Ternaria implements Expresion {
    int linea;
    int col;
    
    public Ternaria(Expresion ex1, Expresion ex2, Expresion ex3) {
        this.ex1 = ex1;
        this.ex2 = ex2;
        this.ex3 = ex3;
//        this.linea = fila;
//        this.col = col;
    }
    private Expresion ex1;
    private Expresion ex2;
    private Expresion ex3;

    @Override
    public Tipo getTipo(Entorno ent) {
        if (ex1.getTipo(ent).isBoolean()) {
            Object r1 = ex1.getValorImplicito(ent);
            if ((boolean)r1)
            {
                return ex2.getTipo(ent);
            }
            return ex3.getTipo(ent);
        }
        return null;
    }

    @Override
    public Object getValorImplicito(Entorno ent) {
        Object r1 = ex1.getValorImplicito(ent);
        Tipo t1 = ex1.getTipo(ent);
        if (r1 != null && t1.isBoolean()) 
        {               
            if ((boolean)r1) 
            {
                return ex2.getValorImplicito(ent);
            }
            else
            {
                return ex3.getValorImplicito(ent);
            }
        }
        else{
            System.out.println("No puede ejecutar valores nulos en operacion ternaria 1");
        }
        return null;
    }

    @Override
    public int linea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int columana() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

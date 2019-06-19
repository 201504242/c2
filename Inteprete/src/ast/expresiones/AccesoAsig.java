/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.Nativo.Objeto;
import ast.general.Expresion;
import ast.general.NodoAST;
import entorno.Entorno;
import entorno.Simbolo;
import entorno.Tipo;

/**
 *
 * @author p_ab1
 */
public class AccesoAsig implements Expresion{
    private Expresion o1;
    private Expresion o2;
    int linea;
    int columna;

    public AccesoAsig(NodoAST a, NodoAST b) {
        this.o1 = (Expresion)a;
        this.o2 = (Expresion)b;
    }
    
    
    @Override
    public Tipo getTipo(Entorno ent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValorImplicito(Entorno ent) {
        Object ob1 = o1.getValorImplicito(ent);
        if (ob1 instanceof Simbolo) 
        {
            Simbolo simboloHeredado = (Simbolo)ob1;
            if(simboloHeredado.getValor() instanceof Objeto)
            if(((Objeto)simboloHeredado.getValor()).getAtributos() instanceof Entorno)
            {                  
                ent.setHeredado(((Objeto)simboloHeredado.getValor()).getAtributos());
                Object ob2 = o2.getValorImplicito(ent);
                ent.setHeredado(null);
                if (ob2 != null) {
                    return ob2;
                }
            }
        }else if (((Identificador)o1).getVal().equals("this")) {
            ent.setHeredado(ent);
            Object ob2 = o2.getValorImplicito(ent);
                ent.setHeredado(null);
                if (ob2 != null) {
                    return ob2;
                }
        }
        
        return null;
    }

    @Override
    public int linea() {
        return linea;
    }

    @Override
    public int columana() {
        return columna;
    }
    
}

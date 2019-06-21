/*
 * To change thinstanceof license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.Nativo.Objeto;
import ast.Nativo.arregloVar;
import ast.general.Expresion;
import entorno.Entorno;
import entorno.Simbolo;
import entorno.Simbolo.Rol;
import entorno.Tipo;
import entorno.Tipo.Tipos;
import java.util.LinkedList;

/**
 *
 * @author p_ab1
 */
public class Acceso implements Expresion{
    Expresion o1;
    Expresion o2;
    int linea;
    int col;

    public Acceso(Expresion o1, Expresion o2,int fila,int col) {
        this.o1 = o1;
        this.o2 = o2;
        this.linea = fila;
        this.col = col;
        
    }     
    
    @Override
    public Tipo getTipo(Entorno ent) {
        Object valor = getValorImplicito(ent);
        if (valor != null) 
        {
            if (valor instanceof Boolean)
            {
                return new Tipo(Tipos.BOOL);
            }
            else if (valor instanceof String)
            {
                return new Tipo(Tipos.STRING);
            }
            else if (valor instanceof Character)
            {
                return new Tipo(Tipos.CHAR);
            }
            else if (valor instanceof Integer)
            {
                return new Tipo(Tipos.INT);
            }
            else if (valor instanceof Double)
            {
                return new Tipo(Tipos.DOUBLE);
            }
        }
        return new Tipo((String) valor);
    }

    @Override
    public Object getValorImplicito(Entorno ent) {
        Object ob1 = o1.getValorImplicito(ent);
        if (ob1 instanceof Entorno) 
        {
            ent.setHeredado((Entorno) ob1);
            Object ob2 = o2.getValorImplicito(ent);
            ent.setHeredado(null);
            if (ob2 != null) {
                return ob2;
            }
        }
        else if (ob1 instanceof  arregloVar) 
        {           
            if (o2 instanceof Identificador) 
            {
                Identificador s = (Identificador)o2;
                if (s.getVal().equals("length")) {
                    return ((arregloVar) ob1).size();
                }
            }
        }
//        else if (ob1 instanceof Identificador) 
//        {
//            if (((Identificador) ob1).getVal().equals("this")){
//                return o2
//            }
//        }
        return null;
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

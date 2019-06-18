/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.general.Expresion;
import ast.general.Instruccion;
import ast.general.NodoAST;
import entorno.Entorno;
import java.util.LinkedList;
/**
 *
 * @author p_ab1
 */
public class If extends Condicion implements  Instruccion{
    private LinkedList<NodoAST> insElse;
    private Instruccion elseIf;
        int linea;
    int col;

    @Override
    public int columana() {
        return col;
    }
    public If(LinkedList<NodoAST> ins, Expresion cond) {
        super(ins, cond);
    }
    
    public If(LinkedList<NodoAST> ins, Expresion cond,LinkedList<NodoAST>insElse) {
        super(ins, cond);
        this.insElse = insElse;
    }
    
    public If(LinkedList<NodoAST> ins, Expresion cond,Instruccion elseIf) {
        super(ins, cond);
        this.elseIf = elseIf;
    }
    
    public LinkedList<NodoAST> getInsElse() {
        return insElse;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        //ejecuto las instrucciones if
        if ((boolean)getCond().getValorImplicito(ent)) 
        {
            Entorno local = new Entorno(ent);
            for (NodoAST nodo : getIns()) {
                if (nodo instanceof Instruccion) 
                {
                    Instruccion ins = (Instruccion)nodo;
                    Object result = ins.ejecutar(local);
                    if (ins instanceof  Break || result instanceof Break) {
                        return new Break();
                    }

                    if (result != null) {
                        return result;
                    }                    
                }
                else if (nodo instanceof Expresion) 
                {
                    Expresion exp = (Expresion)nodo;
                    return exp.getValorImplicito(local);
                }
            }
        }
        else
        {
            Entorno local = new Entorno(ent);
            //ejecuto el else if
            if (elseIf != null) 
            {
                Object result = elseIf.ejecutar(local);
                if (result != null)
                {
                    return result;
                }
            }
            //ejecuto el else
            else
            {
                if (getInsElse() != null) {
                    for(NodoAST nodo : getInsElse())
                    {
                        if (nodo instanceof Break) 
                        {
                            return new Break();
                        }
                        if (nodo instanceof Instruccion)
                        {
                            Instruccion ins = (Instruccion)nodo;
                            Object result = ins.ejecutar(local);
                            if (result != null)
                            {
                                return result;
                            }
                        }
                        else if (nodo instanceof Expresion)
                        {
                            Expresion expr = (Expresion)nodo;
                            return expr.getValorImplicito(local);
                        }
                    }
                }                
            }          
            
        }
        return null;
    }

    @Override
    public int linea() {
        return linea;
    }
}

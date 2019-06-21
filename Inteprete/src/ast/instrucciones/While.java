
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
import entorno.Tipo;
import java.util.LinkedList;

/**
 *
 * @author p_ab1
 */
public class While extends Condicion implements Instruccion{
    int linea;
    int col;


    public While(LinkedList<NodoAST> ins, Expresion cond) {
        super(ins, cond);
//        this.linea = fila;
//this.col = col;
    }
 
    
    @Override
    public Object ejecutar(Entorno ent) {
        eti: 
        while ((boolean)getCond().getValorImplicito(ent))
            {
                Entorno local = new Entorno(ent);               
                for (NodoAST ins : getIns())
                {
                    if (ins instanceof Instruccion) 
                    {        
                        Instruccion aux = (Instruccion)ins;
                        Object result = aux.ejecutar(local);
                        if (ins instanceof Break || result instanceof Break)
                        {
                            return null;
                        }
                        if (ins instanceof Continue || result instanceof Continue) 
                        {
                            continue eti;
                        }

                        if (result != null)
                        {
                            return result;
                        }                          
                    }
                    else if (ins instanceof Expresion) 
                    {
                        Expresion aux = (Expresion) ins;
                        aux.getValorImplicito(local);
                    }                   
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
        return col;
    }
}

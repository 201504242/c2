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
public class DoWhile extends Condicion implements Instruccion{

    public DoWhile(LinkedList<NodoAST> ins, Expresion cond) {
        super(ins, cond);
    }

    @Override
    public Object ejecutar(Entorno ent) {
        Entorno doW = new Entorno(ent);  
        for (NodoAST ins : getIns())
        {
            if (ins instanceof Instruccion) 
            {        
                Instruccion aux = (Instruccion)ins;
                Object result = aux.ejecutar(doW);
                if (ins instanceof Break || result instanceof Break)
                {
                    return null;
                }
                if (ins instanceof Continue || result instanceof Continue) 
                {
                    continue;
                }

                if (result != null)
                {
                    return result;
                }                          
            }
            else if (ins instanceof Expresion) 
            {
                Expresion aux = (Expresion) ins;
                aux.getValorImplicito(doW);
            }                   
        }
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int columana() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

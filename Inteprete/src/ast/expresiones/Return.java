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
public class Return implements Expresion {
    private boolean retornoVoid;
    private Expresion valorDeRetorno;
        int linea;
    int col;
    public Return(Expresion valorDeRetorno)
        {
            this.valorDeRetorno = valorDeRetorno;
            retornoVoid = false;
        }
     
    public Return()
        {
            retornoVoid = true;
        }
     public boolean isRetornoVoid()
        {
            return retornoVoid;
        }
    @Override
    public Tipo getTipo(Entorno ent) {
        return valorDeRetorno.getTipo(ent);
    }

    @Override
    public Object getValorImplicito(Entorno ent) {
        return valorDeRetorno.getValorImplicito(ent);
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

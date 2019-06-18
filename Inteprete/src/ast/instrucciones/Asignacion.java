/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.Nativo.Objeto;
import ast.expresiones.Identificador;
import ast.expresiones.Llama;
import ast.general.Expresion;
import ast.general.Instruccion;
import entorno.Entorno;
import entorno.Simbolo;
import entorno.Tipo.Tipos;
/**
 *
 * @author p_ab1
 */
public class Asignacion implements Instruccion{
    private Expresion assig;
    private Expresion val;
    private boolean isObjecto;
    int linea;
    int col;    
    public Asignacion(Expresion id, Expresion val) {
        this.assig = id;
        this.val = val;
        this.isObjecto = false;
    }

    public Asignacion( Expresion val) {
        this.val = val;
        this.isObjecto = true;
    }
    
    
    @Override
    public Object ejecutar(Entorno ent) {        
        try
        {
           Simbolo asignar = (Simbolo)assig.getValorImplicito(ent);
           Object valor = this.val.getValorImplicito(ent);
           asignar.setValor(valor);
        }
        catch(Exception e)
        {
            System.err.println("Error en asignacion cateo " + e.getMessage());
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
 

    public Expresion getVal() {
        return val;
    }

    public void setVal(Expresion val) {
        this.val = val;
    }
    
    
}

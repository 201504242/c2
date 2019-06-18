/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.Nativo.arregloVar;
import ast.general.Instruccion;
import entorno.Entorno;
import entorno.Simbolo;

/**
 *
 * @author p_ab1
 */
public class fantasma  implements Instruccion{
    int linea;
    int col;

    @Override
    public int columana() {
        return col;
    }
    @Override
    public Object ejecutar(Entorno ent) {
        
        Simbolo n = ent.get("arr");
        arregloVar arr = (arregloVar) n.getValor();
        System.out.println(arr.get(1));
        return null;
    }

    @Override
    public int linea() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

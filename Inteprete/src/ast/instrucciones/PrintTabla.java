/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.general.Instruccion;
import entorno.Entorno;
import entorno.Simbolo;
import java.util.Enumeration;

/**
 *
 * @author p_ab1
 */
public class PrintTabla implements Instruccion{
        int linea;
    int col;

    @Override
    public int columana() {
        return col;
    }
    @Override
    public Object ejecutar(Entorno ent) {
        
        int c= 0;
        
        for (Entorno e = ent; e != null; e = e.getAnterior())
        {
            Enumeration<Simbolo> enumeration = e.getTabla().elements();
            System.out.println("");
            System.out.println("Entorno "+c);
            
            while (enumeration.hasMoreElements())
            {
                Simbolo sim = (Simbolo)enumeration.nextElement();
                System.out.println("TIPO: "+sim.getTipo().getTipoPrimitivo()+" VARIABLE: " + sim.getIdentificador()+" valor: "+sim.getValor());
            }
            
            c++;
        } 
        return null;
    }

    @Override
    public int linea() {
        return linea;
    }
    
}

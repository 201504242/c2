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
public class Bloque implements Instruccion  {
    LinkedList<NodoAST> nodos;
    int linea;
    int col;

    public Bloque(LinkedList<NodoAST> nodos) {
        this.nodos = nodos;
//        this.linea = fila;
//this.col = col;
    }
    
    public Bloque(NodoAST nodo) {
        nodos.add(nodo);
    }
        
    @Override
    public Object ejecutar(Entorno ent) {
        
        Entorno local = new Entorno(ent);
        for (NodoAST nodo : nodos) {
            if (nodo instanceof Instruccion) {
                try {
                    Object result = ((Instruccion)nodo).ejecutar(local);
                    if (result != null) {
                        return result;
                    }
                } catch (Exception e) {
                    System.err.println("REVISA EL DOC DE ERRORES, instruccion incorrecta " + e.getMessage()  );
                }
            }
            else if(nodo instanceof Expresion){
                try{
                    return ((Expresion)nodo).getValorImplicito(local);
                } catch (Exception e) {
                    System.out.println("REVISA EL DOC DE ERRORES, expresion incorrecta");
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

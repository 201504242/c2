/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.Nativo.arregloVar;
import ast.general.Expresion;
import ast.general.NodoAST;
import entorno.Entorno;
import entorno.Tipo;
import java.util.LinkedList;

/**
 *
 * @author p_ab1
 */
public class IniciarArreglo implements Expresion{
  
    int linea;
    int col;
    LinkedList<NodoAST> lista;


    public IniciarArreglo(LinkedList<NodoAST> lista,int fila,int col) {
        this.lista = lista;
        this.linea = fila;
        this.col = col;
    }
    
    @Override
    public Tipo getTipo(Entorno ent) {
        return null;
    }

    @Override
    public Object getValorImplicito(Entorno ent) {
        arregloVar arregloNuevo = new arregloVar();
        for(NodoAST item : this.lista)
        {
            if(item instanceof Expresion )
              arregloNuevo.add(((Expresion) item).getValorImplicito(ent));
        }
        return arregloNuevo;
    }

    @Override
    public int linea() {
        return linea;
    }
        @Override
    public int columana() {
        return col;
    }
    
    public LinkedList<NodoAST> getLista() {
        return lista;
    }

    public void setLista(LinkedList<NodoAST> lista) {
        this.lista = lista;
    }

}

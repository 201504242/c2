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
public class generarArrreglo implements Expresion{
    private LinkedList<Expresion> listaDimensiones;
    private Tipo tipoDer;
    int linea;
    int col;
    public generarArrreglo(LinkedList<Expresion> listaDimensiones, Tipo tipoDer,int fila,int col) {
        this.listaDimensiones = listaDimensiones;
        this.tipoDer = tipoDer;
        this.linea = fila;
        this.col = col;
    }    
    
    @Override
    public Tipo getTipo(Entorno ent) {
        return this.tipoDer;
    }

    @Override
    public Object getValorImplicito(Entorno ent) {
        Object val = this.tipoDer.isNumeric() ? 0 : "" ; 
        if (listaDimensiones.size() > 1) 
        {
            arregloVar nuevo = new arregloVar();
            int i = listaDimensiones.size() - 1;
            int c = (int) listaDimensiones.get(i).getValorImplicito(ent);
            for (int j = 0; j < c; j++) 
            {
                nuevo.add(val);
            }
            i--;
            arregloVar retorna = gen(i,ent,listaDimensiones,nuevo);
            return retorna;
        }
        else if(listaDimensiones.size() == 1)
        {
            arregloVar nuevo = new arregloVar();
            int i = listaDimensiones.size() - 1;
            int c = (int) listaDimensiones.get(i).getValorImplicito(ent);
            for (int j = 0; j < c; j++) 
            {
                nuevo.add(val);
            }
            return nuevo;
        }
        System.out.println("ERROR no es un ARREGLO");
        return null;
    }

    private arregloVar gen(int i, Entorno ent, LinkedList<Expresion> listaDimensiones, arregloVar nuevo) {
        int c = (int) listaDimensiones.get(i).getValorImplicito(ent);
        if (i == 0) 
        {
            arregloVar aux = new arregloVar();
            for (int j = 0; j < c; j++) 
            {
                aux.add(nuevo);
            }
            return aux;
        }
        else
        {
            arregloVar aux = new arregloVar();
            for (int j = 0; j < c; j++) 
            {
                aux.add(nuevo);
            }
            i--;
            return gen(i,ent,listaDimensiones,aux);
        }
    }
    
    @Override
    public int linea() {
        return linea;
    }

    public LinkedList<Expresion> getListaDimensiones() {
        return listaDimensiones;
    }

    public void setListaDimensiones(LinkedList<Expresion> listaDimensiones) {
        this.listaDimensiones = listaDimensiones;
    }

    public Tipo getTipoDer() {
        return tipoDer;
    }

    public void setTipoDer(Tipo tipoDer) {
        this.tipoDer = tipoDer;
    }
        @Override
    public int columana() {
        return col;
    }

    
}

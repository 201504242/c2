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
    public generarArrreglo(LinkedList<Expresion> listaDimensiones, Tipo tipoDer) {
        this.listaDimensiones = listaDimensiones;
        this.tipoDer = tipoDer;
    }    
    
    @Override
    public Tipo getTipo(Entorno ent) {
        return this.tipoDer;
    }

    @Override
    public Object getValorImplicito(Entorno ent) {
        arregloVar arregloNuevo = new arregloVar();
        boolean primero = true;
        arregloVar hijo = new arregloVar();
        for (int i = this.listaDimensiones.size(); i > 0; i--) 
        {
            int casillas = (int)listaDimensiones.get(i-1).getValorImplicito(ent);
            if (primero) 
            {
                primero = false;
                switch (tipoDer.getTipoPrimitivo()) {
                case INT:
                    hijo = new arregloVar();                   
                    for (int j = 0; j < casillas; j++) {
                        hijo.add(0);
                    }
                    break;
                default:
                    System.out.println("Error este tipo "+tipoDer+ " no se puede declarar en un arreglo");;
                }
            }
            else
            {
                for (int j = 0; j < casillas; j++) {
                    arregloNuevo.add(hijo);
                }
            }
            
        }
        return arregloNuevo;
    }
    
    private Object gen(int indice){
        if (indice == this.listaDimensiones.size() - 1) 
        {
            
        }
        return null;
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

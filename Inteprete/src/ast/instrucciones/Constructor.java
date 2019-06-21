/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.Nativo.Modificador;
import ast.general.Instruccion;
import ast.general.NodoAST;
import entorno.Entorno;
import entorno.Simbolo;
import entorno.Tipo.Tipos;
import java.util.LinkedList;

/**
 *
 * @author p_ab1
 */
public class Constructor extends Simbolo implements Instruccion {
    LinkedList<Modificador> modificadores;
    LinkedList<Simbolo> parametros;
    LinkedList<NodoAST> instrucciones;
    int linea;
    int col;
    public Constructor(Rol rol,LinkedList<Modificador> modificadores, LinkedList<Simbolo> parametros, LinkedList<NodoAST> instrucciones, String identificador) {
        super(identificador,rol);
        this.modificadores = modificadores;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
//        this.linea = fila;
//this.col = col;
    }
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        //System.out.println("empieza");        
        for (Simbolo parametro : parametros) {
            Simbolo aux = ent.get(parametro.getIdentificador());
            if (aux != null) 
            {
                Tipos original = aux.getTipo().getTipoPrimitivo();
                Tipos ingresado = parametro.getTipo().getTipoPrimitivo();
                if (original == ingresado) 
                {
                    ent.get(parametro.getIdentificador()).setValor(parametro.getValor());
                }
                else
                {
                    System.out.println("ERROR la variable "+parametro.getIdentificador()+" tiene tipo: "+original
                +" y se desea ingresar un tipo: "+ingresado);
                }
                
            }
            else
            {
                System.out.println("La variable no existe "+parametro.getIdentificador());
            }
        }
        for (NodoAST ins : instrucciones) {
            if (ins instanceof Asignacion) {
                Asignacion aux = (Asignacion)ins;
                aux.ejecutar(ent);
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
    public LinkedList<Modificador> getModificadores() {
        return modificadores;
    }

    public void setModificadores(LinkedList<Modificador> modificadores) {
        this.modificadores = modificadores;
    }

    public LinkedList<Simbolo> getParametros() {
        return parametros;
    }

    public void setParametros(LinkedList<Simbolo> parametros) {
        this.parametros = parametros;
    }

    public LinkedList<NodoAST> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(LinkedList<NodoAST> instrucciones) {
        this.instrucciones = instrucciones;
    }
    
    
    
    
}

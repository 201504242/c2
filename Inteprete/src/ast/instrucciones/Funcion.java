/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.Nativo.Modificador;
import ast.Nativo.Objeto;
import ast.general.Expresion;
import ast.general.Instruccion;
import ast.general.NodoAST;
import entorno.Entorno;
import entorno.Simbolo;
import entorno.Tipo;
import entorno.Tipo.Tipos;
import java.util.LinkedList;

/**
 *
 * @author p_ab1
 */
public class Funcion extends Simbolo implements Instruccion{
    //tipo
    //string id
    //lista modificadoes 
    LinkedList<Modificador> modificadores;
    //lista simbolos parametrosFormales
    LinkedList<Simbolo> parametrosFormales;
    //lista nodosAST sentencias
    LinkedList<NodoAST> sentencias;
    int dim;
        int linea;
    int col;

    @Override
    public int columana() {
        return col;
    }
    public Funcion(String identificador,Rol rol,Tipo tipo,LinkedList<Modificador> modificadores,LinkedList<Simbolo> parametrosFormales, int dim) {
        super(identificador,rol,tipo);
        this.modificadores = modificadores;
        this.parametrosFormales = parametrosFormales;
        this.dim = dim;
    }
    
    public Funcion(String identificador,Rol rol,Tipo tipo,LinkedList<Modificador> modificadores, int dim) {
        super(identificador,rol,tipo);
        this.modificadores = modificadores;
        this.parametrosFormales = new LinkedList();
        this.dim = dim;
    }
   
    
    @Override
    public Object ejecutar(Entorno ent) {
//
//        if (getRol() == Rol.MAIN) 
//        {
            for (NodoAST s : sentencias) 
            {
                if (s instanceof Instruccion) 
                {
                    Object result = ((Instruccion)s).ejecutar(ent);
                    if (result != null)
                    {
                        if (verificarTipo(result,getTipo().getTipoPrimitivo())) {
                            return result;
                        }
                        else
                        {
                            System.out.println("EL tipo INSTRUCCION del retorno no es el declarado en la funcion");
                            return null;
                        }
                    }
                }
                else if (s instanceof Expresion) {
                    Object result = ((Expresion)s).getValorImplicito(ent);
                    if (result != null) {
                        if (((Expresion) s).getTipo(ent).getTipoPrimitivo() == getTipo().getTipoPrimitivo() ) {
                            return result;
                        }
                        else{
                            System.out.println("EL tipo EXPRESION del retorno no es el declarado en la funcion");                             
                            return null;
                        }
                    }
                }
            }            
//        }
//        else
//        {
//            
//        }
        return null;
    }

    @Override
    public int linea() {
        return linea;
    }

    public LinkedList<Modificador> getModificadores() {
        return modificadores;
    }

    public void setModificadores(LinkedList<Modificador> modificadores) {
        this.modificadores = modificadores;
    }

    public LinkedList<Simbolo> getParametrosFormales() {
        return parametrosFormales;
    }

    public void setParametrosFormales(LinkedList<Simbolo> parametrosFormales) {
        this.parametrosFormales = parametrosFormales;
    }

    public LinkedList<NodoAST> getSentencias() {
        return sentencias;
    }

    public void setSentencias(LinkedList<NodoAST> sentencias) {
        this.sentencias = sentencias;
    }

    private boolean verificarTipo(Object result, Tipos tipo) {
        if (tipo == Tipos.INT && result instanceof Integer)
            {
                return true;
            }
            if (tipo == Tipos.STRING && result instanceof String)
            {
                return true;
            }
            else if (tipo == Tipos.CHAR && result instanceof Character)
            {
                return true;
            }
            else if (tipo == Tipos.DOUBLE && result instanceof Double)
            {
                return true;
            }
            else if (tipo == Tipos.BOOL && result instanceof Boolean)
            {
                return true;
            }
            else if (tipo == Tipos.OBJETO && result instanceof Objeto)
            {
                return true;
            }
        return false;
    }
    
    
}

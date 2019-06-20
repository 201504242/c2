/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.expresiones.operaciones.OperadorPost;
import ast.general.Expresion;
import ast.general.Instruccion;
import ast.general.NodoAST;
import entorno.Entorno;
import entorno.Simbolo;
import java.util.LinkedList;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author p_ab1
 */
public class For extends Condicion implements Instruccion{
    private Instruccion inicializacion;
    private Expresion incremento;
        int linea;
    int col;

    @Override
    public int columana() {
        return col;
    }
//nodoAST inicializacion
    public For(LinkedList<NodoAST> instrucciones, Expresion cond,Instruccion inicializacion,Expresion incremento) {
        super(instrucciones, cond);
        this.inicializacion = inicializacion;
        this.incremento = incremento;
    }

    public Instruccion getInicializacion() {
        return inicializacion;
    }

    public void setInicializacion(Instruccion inicializacion) {
        this.inicializacion = inicializacion;
    }
    
    
    @Override
    public Object ejecutar(Entorno ent) {
        Entorno local = new Entorno(ent);
        try {
            inicializacion.ejecutar(local);
            //if cond bool
            while ((boolean)getCond().getValorImplicito(local)) 
            {     
                Entorno entFor = new Entorno(local);                
                //ejecuto cada iteracion
                for (NodoAST in : getIns()) 
                {
                    Object result = ((Instruccion)in).ejecutar(entFor);
                    if (in instanceof Break) {
                        return new Break();
                    }
                    if (in instanceof Continue || result instanceof Continue) 
                    {
                        break;
                    }
                    if (result != null)
                    {
                        return result;
                    }
                }
                if (incremento instanceof OperadorPost) {
                    String id = ((OperadorPost)incremento).getId().getVal();
                }
                    Object dato = incremento.getValorImplicito(local);
               // Simbolo nuevo = new Simbolo(id, dato, incremento.getTipo(local), Simbolo.Rol.VARIABLE);
                //local.reemplazar(id, nuevo);
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public int linea() {
        return linea;
    }
    
}

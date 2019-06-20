/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast;

import ast.general.Instruccion;
import ast.general.NodoAST;
import ast.instrucciones.Clase;
import ast.instrucciones.Constructor;
import ast.instrucciones.Funcion;
import entorno.Entorno;
import inteprete.Graficador;
import inteprete.JError;
import java.util.LinkedList;

/**
 *
 * @author p_ab1
 */
public class AST {
    public LinkedList<JError> lista = new LinkedList();

    private LinkedList<NodoAST> instrucciones;

    public LinkedList<NodoAST> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(LinkedList<NodoAST> instrucciones) {
        this.instrucciones = instrucciones;
    }
    
           
    public AST(LinkedList<NodoAST> instrucciones) {
        this.instrucciones = instrucciones;
    }
    
    public Object ejecutar(){
        Clase Main = null ;
        int contadorMain = 0;
        Entorno superGlobal = new Entorno(null);
       // Graficador.addEntorno("SuperGlobal", superGlobal);
        for (NodoAST nodo : instrucciones) {
            //primera pasada meter en superglobal todas las clases
            if (nodo instanceof Clase) 
            {
                superGlobal.agregar(((Clase)nodo).getIdentificador(), (Clase)nodo);
            }
            else
            {
                System.out.println("revisar clase AST");
            }
        }
        //SEGUNDA PASADA
        Entorno aux = null;
        for (NodoAST nodo : instrucciones) 
        {
            Entorno global = new Entorno(superGlobal);
           // Graficador.addEntorno("global", global);
            //llenar el entorno            
            if (nodo instanceof Clase) 
            {
                Clase c = (Clase)nodo;
                for (Funcion funcion : c.getFunciones()) 
                {
                    global.agregar(funcion.getIdentificador(), funcion);
                }
                for (NodoAST nodoAST : c.getInstrucciones()) {
                    if (nodoAST instanceof Instruccion) 
                    {
                        Object restul = ((Instruccion)nodoAST).ejecutar(global);
                    }
                }
                //constructor
                for (Constructor constructor : c.getConstructores())
                {
                    if (constructor.getIdentificador().equalsIgnoreCase(((Clase) nodo).getIdentificador())) 
                    {
                        System.out.println("es un buen constructor");
                        global.agregar(constructor.getIdentificador(), constructor);
                    }
                    else
                    {
                        System.out.println("es un MAL constructor");                        
                    }
                }
                if (c.getIdentificador().equalsIgnoreCase("main")) 
                {
                    aux = global;
                    contadorMain++;
                    Main = c;
                }
            }
        }                
//        System.out.println("-----------SUPER GLOBAL");
//        superGlobal.Mostrar();
//        System.out.println("-----------GLOBAL");
        //global.Mostrar();
        if (contadorMain == 1 && aux != null) {
            Object ejecutar = Main.ejecutar(aux);
            if (ejecutar != null) {
                return ejecutar;
            }
            System.out.println("////////////TERMINO LA EJECUCION DEL MAIN");
            Graficador.addEntorno("main", aux);
        }
        else{
            Error e = new Error();
            System.out.println("Encontro mas de 1 main o no tiene Main a ejecutar");
        }
        return null;
    }
    
    
}

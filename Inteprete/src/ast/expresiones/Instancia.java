/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.Nativo.Objeto;
import ast.general.Expresion;
import ast.general.Instruccion;
import ast.general.NodoAST;
import ast.instrucciones.Clase;
import ast.instrucciones.Constructor;
import ast.instrucciones.Funcion;
import entorno.Entorno;
import entorno.Simbolo;
import entorno.Simbolo.Rol;
import entorno.Tipo;
import java.util.LinkedList;

/**
 *
 * @author p_ab1
 */
public class Instancia implements Expresion{
    /*
    string id
    lista de expresiones
    */
    private String id;
    private LinkedList<NodoAST> exp;
    int linea;
    int col;
    public Instancia(String id, LinkedList<NodoAST> exp,int fila,int col) {
        this.id = id;
        this.exp = exp;
        this.linea = fila;
        this.col = col;
    }
    
    public Instancia(String id,int fila,int col) {
        this.id = id;
        this.exp = new LinkedList();
        this.linea = fila;
        this.col = col;
    }
    @Override
    public Tipo getTipo(Entorno ent) {
        return new Tipo(id);
    }

    @Override
    public Object getValorImplicito(Entorno ent) {
        if (ent.existe(id)) 
        {
            Clase c = (Clase) ent.get(id);
            if (c != null) 
            {
                Entorno atributos = new Entorno(null);
                //VARIABLES GLOBALES
                for (Instruccion ins : c.getInstrucciones()) {
                    ins.ejecutar(atributos);
                }
                //FUNCIONES
                for (Funcion fun : c.getFunciones()) {
                    atributos.agregar(fun.getIdentificador(), fun);
                }
                //CONSTURCTOR
                for (Constructor cons : c.getConstructores()) 
                {
                    String nombre = cons.getIdentificador();
                    if (nombre.equals(id)) 
                    {
                        LinkedList<Simbolo> parametros =  cons.getParametros();
                        if (parametros.size() == exp.size()) 
                        {                            
                            for (int i = 0; i < exp.size(); i++) 
                            {
                                if (exp.get(i) instanceof Instruccion) 
                                {
                                    ((Instruccion)exp.get(i)).ejecutar(atributos);
                                }
                                else
                                {
                                    Object valorParametro = ((Expresion)exp.get(i)).getValorImplicito(atributos);                                    
                                    parametros.get(i).setValor(valorParametro);
                                    parametros.set(i,parametros.get(i));
                                }
                            }
                            cons.ejecutar(atributos);
                        }
                        else
                        {
                            System.out.println("NO ES UN CONSTRUCTOR: parametros constructro: "+parametros.size()
                            +" trae "+exp.size()+" parametros");
                        }
                    }
                    else
                    {
                        System.out.println("NO ES CONSTRUCTOR: porque tiene nombre: "+id
                        +" y quiere accesar a: "+nombre);
                    }
                }
                return new Objeto(id, atributos,id,Rol.OBJETO,new Tipo(id));
            }
        }
        System.out.println("Existio un error, no existe la clase");
        //e.exite(clase)
        /*si existe la clase buscar las globales y lista de clases miembro.
        por cada declaracion meterlo
        entron aux = new entrono null
        for(dec : simbolo.getDecla){
            dec.ejecutar(aux)
        }
        otro for a la lista de funcioens
        buscar el constructor y ejecutarlo porque pueden modificar los valores 
        retorno un objecto[el chiste es llenar la lsita de simbolos de objecto]
        */
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

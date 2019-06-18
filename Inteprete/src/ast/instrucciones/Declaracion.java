/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.instrucciones;

import ast.expresiones.IniciarArreglo;
import ast.expresiones.Var;
import ast.expresiones.generarArrreglo;
import ast.general.Expresion;
import ast.general.Instruccion;
import ast.general.NodoAST;
import entorno.Entorno;
import entorno.Simbolo;
import entorno.Simbolo.Rol;
import entorno.Tipo;
import entorno.Tipo.Tipos;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author p_ab1
 */
public class Declaracion implements Instruccion{

    private Tipo tipo;
    private LinkedList<Var> vars;
    private Expresion valor;
    private String variable;
    int linea;
    int col;

    @Override
    public int columana() {
        return col;
    }
    //para Recursion r = E;

    public Declaracion(Tipo tipo, Expresion valor, String variable) {
        this.tipo = tipo;
        this.valor = valor;
        this.variable = variable;
        this.vars = null;
    }
    
    //para Recursion r;

    public Declaracion(Tipo tipo, String variable) {
        this.tipo = tipo;
        this.variable = variable;
        this.vars = null;
    }
    
    //Constructor para int a = 5;
    public Declaracion(Tipo tipo, LinkedList<Var> vars, Expresion valor) {
        this.tipo = tipo;
        this.vars = vars;
        this.valor = valor;
        
    }
    //Constructor para int a;
    public Declaracion(Tipo tipo, LinkedList<Var> vars) {
        this.tipo = tipo;
        this.vars = vars;
        
    }
    
    public Declaracion(LinkedList<Var> vars) {        
        this.vars = vars;
        
    }
    
    public Declaracion(LinkedList<Var> vars, Expresion valor) {        
        this.vars = vars;
        
    }
    
    
    public boolean tieneValor(){
        return this.valor != null;
    }
    
    @Override
    public Object ejecutar(Entorno ent) {
        if (vars != null) 
        {
            for (Var var : vars)             
            {                
                String nombreIzq = var.getId();
                //ES UN ARREGLO
                if (var.getDim() != 0) 
                {
                    //Arreglo inicializado con {{a,b}}
                    if (tieneValor() && valor instanceof IniciarArreglo)
                    {
                        Tipos tipoIzq = tipo.getTipoPrimitivo();
                        Tipos tipoDer = traerTipo(valor,ent);
                        //Tipos tipoDer = valor.getTipo(ent).getTipoPrimitivo();
                        if (tipoIzq == tipoDer) 
                        {
                            Simbolo sim = new Simbolo(var.getId(),valor.getValorImplicito(ent),tipo);
                            ent.agregar(var.getId(),sim);
                        }
                        else
                        {
                            System.out.println("ERROR de tipos. Desea ingresar tipo "+tipoDer+" en un tipo: "+tipoIzq);
                        }
                    }
                    //Arreglo inicializado con new TIPO [][]
                    else if(tieneValor() && valor instanceof generarArrreglo)
                    {
                        Tipos tipoIzq = tipo.getTipoPrimitivo();
                        generarArrreglo derecho = (generarArrreglo)valor;
                        Tipos tipoDer = derecho.getTipoDer().getTipoPrimitivo();
                        if (tipoIzq == tipoDer) 
                        {
                            Simbolo sim = new Simbolo(var.getId(),derecho.getValorImplicito(ent),tipo);
                            ent.agregar(var.getId(),sim);
                        }
                         else
                        {
                            System.out.println("ERROR de tipos. Desea ingresar tipo "+tipoDer+" en un tipo: "+tipoIzq);
                        }
                        
                    }
                    //no tiene = E
                    else
                    {
                        Simbolo sim = new Simbolo(var.getId(),tipo);
                        ent.agregar(var.getId(),sim);
                    }
                }
                //ES UNA VARIABLE NORMAL
                else
                {
                    if (ent.existeEnActual(nombreIzq)) 
                    {
                        JOptionPane.showMessageDialog(null, "ERROR la variable ya existe");
                    }
                    else
                    {
                        //valor si trae una expresion del lado derecho
                        if (tieneValor()) 
                        {
                            //compruebo tipos
                            Tipos ingresado = valor.getTipo(ent).getTipoPrimitivo();
                            Tipos original = tipo.getTipoPrimitivo();
                            if (original == ingresado) 
                            {
                                Simbolo sim = new Simbolo(var.getId(),valor.getValorImplicito(ent),tipo);
                                ent.agregar(var.getId(),sim);
                               // System.out.println("DECLARACION ASIGNANDO "+id);
                            }
                            else
                            {
                                System.out.println("ERROR de tipos. Desea ingresar tipo "+ingresado+" en un tipo: "+original);
                            }
                        }   
                        else
                        {
                            Simbolo sim = new Simbolo(var.getId(),tipo);
                            ent.agregar(var.getId(),sim);
                           // System.out.println("Se creo "+id);
                        }
                    }
                }
            }
        }
        //es un objecto
        else
        {
            if (ent.existeEnActual(variable)) 
            {
                JOptionPane.showMessageDialog(null, "ERROR la variable "+ tipo.getTipoObjeto()+" ya existe");
            }
            else
            {
                if (tieneValor()) 
                {
                    String ingresado = valor.getTipo(ent).getTipoObjeto();
                    String original = tipo.getTipoObjeto();     
                    if (ingresado.equals(original)) 
                    {
                        Simbolo sim = new Simbolo(variable,valor.getValorImplicito(ent),tipo,Rol.OBJETO);
                        ent.agregar(variable,sim);
                    }
                    else
                    {
                        System.out.println("ERROR de tipos. Desea ingresar tipo "+ingresado+" en un tipo: "+original);
                    }
                }
                else
                {
                    Simbolo sim = new Simbolo(variable,tipo,Rol.OBJETO);
                    ent.agregar(variable,sim);
                }
            }
        }
        return null;
    }

    @Override
    public int linea() {
        return linea;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Expresion getValor() {
        return valor;
    }

    public void setValor(Expresion valor) {
        this.valor = valor;
    }

    public void setVars(LinkedList<Var> vars) {
        this.vars = vars;
    }

    public LinkedList<Var> getVars() {
        return vars;
    }

    private Tipos traerTipo(Expresion valor,Entorno ent) {
        try 
        {
            IniciarArreglo instancia = (IniciarArreglo)valor;
            LinkedList<Tipo> listaTipos = new LinkedList();
            for (NodoAST nodo : instancia.getLista()) 
            {
                Tipo t;
                if (nodo instanceof IniciarArreglo) 
                {                                      
                    t = new Tipo(traerTipo((Expresion) nodo,ent));
                }
                else{
                    t = ((Expresion)nodo).getTipo(ent);
                }
                
                listaTipos.add(t);
            }            
            //verificar todos los
            Tipo original = this.tipo; 
            for (int i = 0; i < listaTipos.size(); i++) 
            {
                Tipo aux = listaTipos.get(i);
                if (original.getTipoPrimitivo() != aux.getTipoPrimitivo()) 
                {
                    return aux.getTipoPrimitivo();
                }
            }
            return this.tipo.getTipoPrimitivo();
        } catch (Exception e) {
            return Tipos.NULL;
        }
    }
    
    
}

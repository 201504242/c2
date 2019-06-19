
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.Nativo.Objeto;
import ast.Nativo.arregloVar;
import ast.general.Expresion;
import entorno.Entorno;
import entorno.Simbolo;
import entorno.Simbolo.Rol;
import entorno.Tipo;
import java.util.LinkedList;

/**
 *
 * @author p_ab1
 */
public class Identificador implements Expresion{
    private String val;
    private LinkedList<Expresion> lista;
    int linea;
    int col;
    boolean isObjecto;
    
    public Identificador(LinkedList<Expresion> lista,String val) {
        this.val = val;
        this.lista = lista;
        this.isObjecto = false;
    }
    
    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
    

    public Identificador(String val,boolean isObjecto) {
        this.val = val;
        this.lista = null;
        this.isObjecto = isObjecto;
    }
    
    public Identificador(String val,LinkedList<Expresion> lista) {
        this.val = val;
        this.lista = lista;
    }
    
    @Override
    public Tipo getTipo(Entorno ent) {
        if (ent.get(val) != null) {
            return ent.get(val).getTipo();
        }
        else{
            System.out.println("ERROR variable "+val+" no existe en el entorno actual");
            return null;
        }
    }

    @Override
    public Object getValorImplicito(Entorno ent) {
        Entorno en = ent.getHeredado() != null ? ent.getHeredado() : ent ;
        Simbolo sim = en.get(val);
        if (sim != null) {
            //asginacion
           
            if (isObjecto) 
            {
                if(lista == null)
                {
                   return sim;
                }else{
                    // SEa Acceso Arreglo 
                    System.out.println("ARREGLO");
                    return null;
                }
            }
            //normal
            else
            {
                if (lista == null) 
                {
                    //return sim.getTipo().getTipoPrimitivo() == Tipo.Tipos.OBJETO? ((Objeto)sim).getAtributos() : sim.getValor();                
                    if (sim.getValor() instanceof Objeto)
                    {
                        Objeto o = (Objeto) sim.getValor();
                        return o.getAtributos();
                    }
                    else
                    {
                        return sim.getValor();
                    }
                }
                else
                {
                    arregloVar arr = (arregloVar) sim.getValor();       
                    if (arr != null) 
                    {
                        Object ob = getValorArreglo(lista,0,ent,arr);;
                        Simbolo s = new Simbolo(val, ob,getTipo(ent),Rol.ARREGLO);
                        return s.getValor();
                    }
                    else{
                        System.out.println("Error en Arreglo");
                        return null;
                    }
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
    private Object getValorArreglo(LinkedList<Expresion> l,int indiceActual,Entorno ent,arregloVar arr) {
        try {
            int index = (int) l.get(indiceActual).getValorImplicito(ent);
            if (indiceActual == l.size()-1 ) 
            {
                return arr.get(index);
            }
            else{
                arr = (arregloVar) arr.get(index);            
                return getValorArreglo(l, indiceActual+1,ent,arr);
            }
        } catch (Exception e) {
            System.err.println("ERROR EN GETVALOR "+e.toString());
            return null;
        }
    }
    
}

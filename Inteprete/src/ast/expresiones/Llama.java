/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones;

import ast.general.Expresion;
import ast.general.NodoAST;
import ast.instrucciones.Funcion;
import entorno.Entorno;
import entorno.Simbolo;
import entorno.Tipo;
import entorno.Tipo.Tipos;
import java.util.LinkedList;

/**
 *
 * @author p_ab1
 */
public class Llama implements Expresion{
    private String id;
    private LinkedList<NodoAST> valores;
    int linea;
    int col;

    public Llama(String id, LinkedList<NodoAST> valores) {
        this.id = id;
        this.valores = valores;
    }

    public Llama(String id) {
        this.id = id;
        this.valores = new LinkedList();
    }       
    //2 constructores
    
    @Override
    public Tipo getTipo(Entorno ent) {
        if (ent.get(id) != null) {
            return ent.get(id).getTipo();
        }
        else{
            System.out.println("ERROR variable "+id+" no existe en el entorno actual");
            return null;
        }
    }

    @Override
    public Object getValorImplicito(Entorno ent) {
        
        try {
            Entorno en = ent.getHeredado() != null ? ent.getHeredado() : ent ;
            if (en.existe(id)) 
            {
                Entorno local = new Entorno(en);
                Funcion funcion = (Funcion)en.get(id);           
                LinkedList<Simbolo> formales = funcion.getParametrosFormales();
                if (verificar(valores,formales,local,ent))
                {
                    Object a = funcion.ejecutar(local);
                    return a;
                }
            }
            else
            {
                System.out.println("ERROR la funcion con nombre: "+id+" no existe en ningun entorno");
            }
        } catch (Exception e) {
            System.out.println("ERROR la funcion con nombre: "+id+" no existe en ningun entorno");

        }
        
        return null;
    }

    @Override
    public int linea() {
        return linea;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LinkedList<NodoAST> getParametrosActuales() {
        return valores;
    }

    public void setParametrosActuales(LinkedList<NodoAST> valores) {
        this.valores = valores;
    }

    private boolean verificar(LinkedList<NodoAST> valores, LinkedList<Simbolo> formales, Entorno local,Entorno ent) {
        if (formales.size() == valores.size()) 
            { //2)rrecorrer los formales y comparar con los actuales
                Simbolo sim_aux;
                String id_aux;
                Tipos tipoPar_aux = null;
                Tipos tipoVal = null;
                Expresion exp_aux;                
                Object v = null;
                LinkedList<Simbolo> datos = new LinkedList();
                for (int i = 0; i < valores.size(); i++)
                {//JARLO DE LA TABLA DE SIMBOLOS
                    sim_aux = formales.get(i);
                    id_aux = sim_aux.getIdentificador();
                    tipoPar_aux = sim_aux.getTipo().getTipoPrimitivo();                    
                    exp_aux = (Expresion)valores.get(i);
                    tipoVal = exp_aux.getTipo(ent).getTipoPrimitivo();
                    v = exp_aux.getValorImplicito(ent);                                        
                    if (tipoPar_aux == tipoVal) {                        
                        Simbolo s = new Simbolo(id_aux, v, new Tipo(tipoPar_aux));
//                        local.agregar(id_aux, new Simbolo(id_aux, v, new Tipo(tipoVal)));
                        datos.addLast(s);
                    }
                    else {
                        System.out.println("ERROR tipos, forma: "+tipoPar_aux + " actual: "+tipoVal);
                        return false;
                    }
                }
                for (int i = 0; i < datos.size(); i++) {
                    //System.out.println("Guardo: "+datos.get(i).getValor());
                    Simbolo loca = datos.get(i);
                    local.agregar(loca.getIdentificador(), loca);
                }
            }else{
                System.out.println("ERROR parametros formales ");
            }
        return true;
    }
    
    
    @Override
    public int columana() {
        return col;
    }
}

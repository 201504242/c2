/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entorno;

import entorno.Tipo.Tipos;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author p_ab1
 */
public class Entorno {
    private Hashtable tabla;
    private Entorno anterior;
    private Entorno heredado;
        
    public Entorno(Entorno anterior)
    {
        this.tabla = new Hashtable();
        this.anterior = anterior;
    }
    
    public void agregar(String id, Simbolo simbolo)
    {
        tabla.put(id, simbolo);
    }
    
    public Simbolo get(String id)
    {
        for (Entorno e = this; e != null; e = e.anterior)
        {
            Simbolo encontrado = (Simbolo)(e.tabla.get(id));
            if (encontrado != null)
                {
                return encontrado;
            }
        }
        System.out.println("El simbolo \"" + id + "\" no ha sido declarado en el entorno actual ni en alguno externo");
        return null;
    }
    public void reemplazar(String id, Simbolo nuevoValor)
        {
            for (Entorno e = this; e != null; e = e.getAnterior())
            {
                Simbolo encontrado = (Simbolo)(e.tabla.get(id.toLowerCase()));
                if (encontrado != null)
                {
                    e.tabla.put(id.toLowerCase(), nuevoValor);
                }
            }
        }
    public void Mostrar(){
        int c= 0;
        for (Entorno e = this; e != null; e = e.anterior)
        {
            Enumeration<Simbolo> enumeration = e.tabla.elements();
            System.out.println("Entorno "+c);
            while (enumeration.hasMoreElements())
            {
                Simbolo sim = (Simbolo)enumeration.nextElement();
                Tipos t = sim.getTipo() !=null ? sim.getTipo().getTipoPrimitivo() : Tipos.OBJETO;
                System.out.println("Tipo: "+t+" Rol: "+sim.getRol()+" Valor: "+sim.getValor()+" Variable: " + sim.getIdentificador());
            }
            c++;
        } 
    }
     public boolean existe(String id)
        {
            for (Entorno e = this; e != null; e = e.anterior)
            {
                if (e.tabla.containsKey(id))
                {
                    return true;
                }
            }
            return false;
        }

    public boolean existeEnActual(String id)
    {
        Simbolo encontrado = (Simbolo)(tabla.get(id));
        return encontrado != null;
    }
    
    public Hashtable getTabla() {
        return tabla;
    }

    public void setTabla(Hashtable tabla) {
        this.tabla = tabla;
    }

    public Entorno getAnterior() {
        return anterior;
    }

    public void setAnterior(Entorno anterior) {
        this.anterior = anterior;
    }

    public Entorno getHeredado() {
        return heredado;
    }

    public void setHeredado(Entorno heredado) {
        this.heredado = heredado;
    }
    
    
}

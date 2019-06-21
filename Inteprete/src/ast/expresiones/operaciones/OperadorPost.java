/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.operaciones;

import ast.expresiones.Identificador;
import ast.general.Expresion;
import entorno.Entorno;
import entorno.Simbolo;
import entorno.Tipo;
import entorno.Tipo.Tipos;

/**
 *
 * @author p_ab1
 */
public class OperadorPost implements Expresion{
    private Identificador id;
    boolean suma;
    int linea;
    int col;
    public OperadorPost(Identificador id, boolean suma,int fila,int col) {
        this.id = id;
        this.suma = suma;
        this.linea = fila;
        this.col = col;
    }
    
    @Override
    public Tipo getTipo(Entorno ent) {
        return id.getTipo(ent);
    }

    @Override
    public Object getValorImplicito(Entorno ent) {
        Simbolo sim = ent.get(id.getVal());
        if (sim != null)
        {
            //VERIFICAR SI ES ++ O --
            Tipos aux = id.getTipo(ent).getTipoPrimitivo();
            if (suma) 
            {
                switch(aux)
                {
                    case INT:
                        int val = (int)sim.getValor();
                        sim.setValor(val+1);
                        return (int)val+1;
                    case DOUBLE:
                        double val2 = (double)sim.getValor();
                        sim.setValor(val2+1.0);
                        return (double)val2+1.0;
                    case CHAR:
                        char val3 = (char)sim.getValor();                        
                        sim.setValor(val3+1);
                        return (char)val3 + 1;
                    default:
                        System.out.println("ERROR la variable tiene que ser numerica y es: "+aux.toString());
                        return null;
                }
            }
            else
            {
                switch(aux)
                {
                    case INT:
                        int val = (int)sim.getValor();                        
                        sim.setValor(val-1);
                        return (int)val-1;
                    case DOUBLE:
                        double val2 = (double)sim.getValor();
                        sim.setValor(val2-1.0);
                        return (double)val2-1.0;
                    case CHAR:
                        char val3 = (char)sim.getValor();  
                        sim.setValor(val3-1);
                        return (char)val3 - 1;
                    default:
                        System.out.println("ERROR la variable tiene que ser numerica y es: "+aux.toString());
                        return null;
                }
            }
        }
        else
        {
            System.out.println("No existe la varaible: "+id.getVal());
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

    public Identificador getId() {
        return id;
    }

    public void setId(Identificador id) {
        this.id = id;
    }

    public boolean isSuma() {
        return suma;
    }

    public void setSuma(boolean suma) {
        this.suma = suma;
    }
    
    
}

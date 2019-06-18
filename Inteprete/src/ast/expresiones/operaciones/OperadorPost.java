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
    public OperadorPost(Identificador id, boolean suma) {
        this.id = id;
        this.suma = suma;
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
                        sim.setValor((int)sim.getValor()+1);
                        return (int)sim.getValor()+1;
                    case DOUBLE:
                        sim.setValor((double)sim.getValor()+1.0);
                        return (double)sim.getValor()+1.0;
                    case CHAR:
                        sim.setValor((char)sim.getValor()+1);
                        return (char)sim.getValor() + 1;
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
                        sim.setValor((int)sim.getValor()-1);
                        return (int)sim.getValor()-1;
                    case DOUBLE:
                        sim.setValor((double)sim.getValor()-1.0);
                        return (double)sim.getValor()-1.0;
                    case CHAR:
                        sim.setValor((char)sim.getValor()-1);
                        return (char)sim.getValor() - 1;
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.operaciones;

import ast.general.Expresion;
import entorno.Entorno;
import entorno.Tipo;

/**
 *
 * @author p_ab1
 */
public class Logica extends Operacion{
    int linea;
    int col;
    public Logica(Expresion op1, Expresion op2, Operador operador,int fila,int col) {
        super(op1, op2, operador);
        this.linea = fila;
        this.col = col;
    }

    @Override
    public Tipo tipoDominante(Tipo t1, Tipo t2) {
        if (t1.isBoolean() && t2.isBoolean()) {
            return new Tipo(Tipo.Tipos.BOOL);
        }
        return null;
    }

    @Override
    public Tipo getTipo(Entorno ent) {
        return tipoDominante(op1.getTipo(ent), op2.getTipo(ent));
    }

    @Override
    public Object getValorImplicito(Entorno ent) {
        Tipo t1 = op1.getTipo(ent);
        Tipo t2 = op2.getTipo(ent);
        Tipo tipoResultado = tipoDominante(t1, t2);
        if (tipoResultado != null)
        {
            Object res1 = op1.getValorImplicito(ent);
            Object res2 = op2.getValorImplicito(ent);
            try {
                switch(operador){
                    case OR:
                        return (boolean)res1 || (boolean)res2;
                    case AND:
                        return (boolean)res1 && (boolean)res2;
                    case XOR:
                        return logicalXOR((boolean)res1 , (boolean)res2);
                    default:
                        System.out.println("El operador no pertenece a la clase logica");
                        return null;
                }
            } catch (Exception e) {
                System.out.println("ERROR en clase logica");
            }
        }
        return null;
    }
    
    public static boolean logicalXOR(boolean x, boolean y) {
    return ( ( x || y ) && ! ( x && y ) );
    }


}

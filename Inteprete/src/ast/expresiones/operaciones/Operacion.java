/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.operaciones;

import ast.general.Expresion;
import entorno.Tipo;

/**
 *
 * @author p_ab1
 */
public abstract class Operacion implements Expresion{
    Expresion op1;
    Expresion op2;
    Operador operador;
    int linea;
    int col;
    
    public Operacion(Expresion op1, Expresion op2, Operador operador) {
        this.op1 = op1;
        this.op2 = op2;
        this.operador = operador;
    }
    
    public Operacion(Expresion op1, Operador operador) {
        this.op1 = op1;
        this.operador = operador;
    }
    
    public abstract Tipo tipoDominante(Tipo t1,Tipo t2);
    
    @Override
    public int columana() {
        return col;
    }
    
        @Override
    public int linea() {
        return linea;
    }
    
    public enum Operador
        {
            SUMA,
            RESTA,
            MULTIPLICACION,
            DIVISION,
            POTENCIA,
            MODULO,
            MENOS_UNARIO,
            MAYOR_QUE,
            MAYOR_IGUAL,
            MENOR_QUE,
            MENOR_IGUAL,
            IGUAL_IGUAL,
            DIFERENTE_QUE,
            OR,
            XOR,
            AND,
            NOT,
            TERNARIA,
            DESCONOCIDO
        }
}

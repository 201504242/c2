/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.operaciones;

import ast.general.Expresion;
import entorno.Entorno;
import entorno.Tipo;
import java.util.Objects;

/**
 *
 * @author p_ab1
 */
public class Relacional extends Operacion{
    int linea;
    int col;
    public Relacional(Expresion op1, Expresion op2, Operador operador) {
        super(op1, op2, operador);
//        this.linea = fila;
//        this.col = col;
    }

    @Override
    public Tipo tipoDominante(Tipo t1, Tipo t2) {
        if (t1.getTipoPrimitivo() == Tipo.Tipos.DOUBLE || t2.getTipoPrimitivo() == Tipo.Tipos.DOUBLE) 
        {
            return new Tipo(Tipo.Tipos.BOOL);
        } 
        else if (t1.getTipoPrimitivo() == Tipo.Tipos.INT || t2.getTipoPrimitivo() == Tipo.Tipos.INT){
            return new Tipo(Tipo.Tipos.BOOL);
        }
        else if (t1.getTipoPrimitivo() == Tipo.Tipos.CHAR || t2.getTipoPrimitivo() == Tipo.Tipos.CHAR) {
            return new Tipo(Tipo.Tipos.BOOL);
        }
        else if (t1.getTipoPrimitivo() == Tipo.Tipos.STRING && t2.getTipoPrimitivo() == Tipo.Tipos.STRING) {
            return new Tipo(Tipo.Tipos.BOOL);
        }
        else if (t1.getTipoPrimitivo() == Tipo.Tipos.BOOL && t2.getTipoPrimitivo() == Tipo.Tipos.BOOL) {
            return new Tipo(Tipo.Tipos.BOOL);
        }
        else
        {
            System.out.println("ALGO MALO tipo dominante relacional :"+t1.getTipoPrimitivo() + " , "+t2.getTipoPrimitivo());
            return null;
        }
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
            //solo string
            if (t1.isString() && t2.isString()){
                switch(operador){
                    case IGUAL_IGUAL:
                        return res1.toString().equals(res2.toString());
                        case DIFERENTE_QUE:
                        return res1.toString() != (res2.toString());
                    default:    
                        System.out.println("ERROR no se puede hacer otra operacion entre string que no sea == o !=");
                        return null;
                }
            }
            //solo numero
            else if (t1.isNumeric() && t2.isNumeric())
            {
                switch(operador)
                {
                    case MAYOR_QUE:
                        if (t1.isChar() && t2.isDouble() || t1.isChar() && t2.isInt()) {
                            return (boolean)((char)res1 > new Double(res2.toString()));
                        }
                        else if (t2.isChar() && t1.isDouble() || t2.isChar() && t1.isInt()) {
                            return (boolean)((char)res2 < new Double(res1.toString()));
                        }
                        else if (t1.isChar() && t2.isChar()) {
                            return (boolean)((char)res1 > (char)res2);
                        }
                        else{
                            return (boolean)(new Double(res1.toString()) > new Double(res2.toString()));
                        }
                    case MENOR_QUE:
                        if (t1.isChar() && t2.isDouble() || t1.isChar() && t2.isInt()) {
                            return (boolean)((char)res1 < new Double(res2.toString()));
                        }
                        else if (t2.isChar() && t1.isDouble() || t2.isChar() && t1.isInt()) {
                            return (boolean)((char)res2 > new Double(res1.toString()));
                        }
                        else if (t1.isChar() && t2.isChar()) {
                            return (boolean)((char)res1 < (char)res2);
                        }
                        else{
                            return (boolean)(new Double(res1.toString()) < new Double(res2.toString()));
                        }   
                    case MAYOR_IGUAL:
                        if (t1.isChar() && t2.isDouble() || t1.isChar() && t2.isInt()) {
                            return (boolean)((char)res1 >= new Double(res2.toString()));
                        }
                        else if (t2.isChar() && t1.isDouble() || t2.isChar() && t1.isInt()) {
                            return (boolean)((char)res2 <= new Double(res1.toString()));
                        }
                        else if (t1.isChar() && t2.isChar()) {
                            return (boolean)((char)res1 >= (char)res2);
                        }
                        else{
                            return (boolean)(new Double(res1.toString()) >= new Double(res2.toString()));
                        }
                    case MENOR_IGUAL:
                        if (t1.isChar() && t2.isDouble() || t1.isChar() && t2.isInt()) {
                            return (boolean)((char)res1 <= new Double(res2.toString()));
                        }
                        else if (t2.isChar() && t1.isDouble() || t2.isChar() && t1.isInt()) {
                            return (boolean)((char)res2 >= new Double(res1.toString()));
                        }
                        else if (t1.isChar() && t2.isChar()) {
                            return (boolean)((char)res1 <= (char)res2);
                        }
                        else{
                            return (boolean)(new Double(res1.toString()) <= new Double(res2.toString()));
                        }   
                    case IGUAL_IGUAL:

                        if (t1.isChar() && t2.isDouble() || t1.isChar() && t2.isInt()) {
                            return (boolean)((char)res1 == new Double(res2.toString()));
                        }
                        else if (t2.isChar() && t1.isDouble() || t2.isChar() && t1.isInt()) {
                            return (boolean)((char)res2 == new Double(res1.toString()));
                        }
                        else if (t1.isChar() && t2.isChar()) {
                            return (boolean)((char)res1 == (char)res2);
                        }
                        else{
                            return (boolean)(Objects.equals(new Double(res1.toString()), new Double(res2.toString())));
                        } 
                    case DIFERENTE_QUE:
                        if (t1.isChar() && t2.isDouble() || t1.isChar() && t2.isInt()) {
                            return (boolean)((char)res1 != new Double(res2.toString()));
                        }
                        else if (t2.isChar() && t1.isDouble() || t2.isChar() && t1.isInt()) {
                            return (boolean)((char)res2 != new Double(res1.toString()));
                        }
                        else if (t1.isChar() && t2.isChar()) {
                            return (boolean)((char)res1 != (char)res2);
                        }
                        else{
                            double r1 = Double.valueOf(res1.toString());
                            double r2 = Double.valueOf(res2.toString());
//                            System.out.println("r1: "+r1);
//                            System.out.println("r2: "+r2);
                            
                            boolean res = r1 != r2;
                            return res;
                        }   
                    default:    
                        System.out.println("ERROR operador en RELACIONAL");
                        return null;
                }
            }
            else if (t1.isBoolean() && t2.isBoolean()) 
            {
                boolean r1 = (boolean)res1;
                boolean r2 = (boolean)res2;    
//                System.out.println("r1:"+r1);
//                System.out.println("r2:"+r2);
                switch(operador)
                {
                    case IGUAL_IGUAL:
                        return (boolean)(r1 == r2);
                        
                    case DIFERENTE_QUE:
                        return r1 != r2;
                    default:    
                        System.out.println("ERROR operador en RELACIONAL en boool");
                        return null;
                }
            }
            else{
                System.out.println("no se puede operar si no es numero: RELACIONAL");
                return null;
            }
            
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.expresiones.operaciones;

import ast.general.Expresion;
import entorno.Entorno;
import entorno.Tipo;
import entorno.Tipo.Tipos;

/**
 *
 * @author p_ab1
 */
public class Aritmetica extends Operacion{
    int linea;
    int col;
    public Aritmetica(Expresion op1, Expresion op2, Operador operador,int fila,int col) {
        super(op1, op2, operador);
        this.linea = fila;
        this.col = col;
    }

    @Override
    public Tipo tipoDominante(Tipo t1, Tipo t2) {
         //DOMINA STRING
        if (t1.getTipoPrimitivo() == Tipos.STRING || t2.getTipoPrimitivo() == Tipos.STRING) 
        {
            return new Tipo(Tipos.STRING);
        }
        else if (t1.getTipoPrimitivo() == Tipos.BOOL || t2.getTipoPrimitivo() == Tipos.BOOL) {
            System.out.println("ERROR tipos bool no se puede operar");
            return null;
        }
        else if(t1.getTipoPrimitivo() == Tipos.DOUBLE || t2.getTipoPrimitivo() == Tipos.DOUBLE)
        {
            return new Tipo(Tipos.DOUBLE);
        }
        else if (t1.getTipoPrimitivo() == Tipos.INT || t2.getTipoPrimitivo() == Tipos.INT || 
                (t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.CHAR)) 
        {
            return new Tipo(Tipos.INT);
        }
        else
        {
            System.out.println("ALGO MALO :"+t1 + " , "+t2);
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
        //if (res1 != null && res2 != null) {            
            switch(operador)
            {
                case SUMA:
                    switch (tipoResultado.getTipoPrimitivo()) 
                    {
                        case STRING:
                            return res1.toString()+res2.toString();
                        case DOUBLE:
                            if (t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.DOUBLE) {
                                return ((char)(res1)) + (double)res2;
                            }else if(t1.getTipoPrimitivo() == Tipos.DOUBLE && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                return (double)res1 + (char)res2;
                            }
                            return new Double(res1.toString()) +new Double(res2.toString());
                        case INT:
                        case CHAR:
                            if (t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.INT) {
                                return ((char)(res1)) + (int)res2;
                            }else if(t1.getTipoPrimitivo() == Tipos.INT && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                return (int)res1 + (char)res2;
                            }else if(t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                return (char)res1 + (char)res2;
                            }
                            return (int)res1 + (int)res2;
                        default:
                            System.out.println("ERROR en suma");
                            return null;                            
                    }
                case RESTA:
                    switch (tipoResultado.getTipoPrimitivo()) 
                    {
                        case STRING:
                            System.out.println("ERROR no se puede restar tipo string");
                            return null;
                        case DOUBLE:
                            if (t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.DOUBLE) {
                                return ((char)(res1)) - (double)res2;
                            }else if(t1.getTipoPrimitivo() == Tipos.DOUBLE && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                return (double)res1 - (char)res2;
                            }
                            return new Double(res1.toString()) - new Double(res2.toString());
                        case INT:
                        case CHAR:
                            if (t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.INT) {
                                return ((char)(res1)) - (int)res2;
                            }else if(t1.getTipoPrimitivo() == Tipos.INT && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                return (int)res1 - (char)res2;
                            }else if(t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                return (char)res1 - (char)res2;
                            }
                            return (int)res1 - (int)res2;
                        default:
                            System.out.println("ERROR en resta");
                            return null;
                    }
                case MULTIPLICACION:
                    switch (tipoResultado.getTipoPrimitivo()) 
                    {
                        case STRING:
                            System.out.println("ERROR no se puede multiplicar tipo string");
                            return null;
                        case DOUBLE:
                            if (t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.DOUBLE) {
                                return ((char)(res1)) * (double)res2;
                            }else if(t1.getTipoPrimitivo() == Tipos.DOUBLE && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                return (double)res1 * (char)res2;
                            }
                            return new Double(res1.toString()) * new Double(res2.toString());
                        case INT:
                        case CHAR:
                            if (t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.INT) {
                                return ((char)(res1)) * (int)res2;
                            }else if(t1.getTipoPrimitivo() == Tipos.INT && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                return (int)res1 * (char)res2;
                            }else if(t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                return (char)res1 * (char)res2;
                            }
                            return (int)res1 * (int)res2;
                        default:
                            System.out.println("ERROR en multiplicacion");
                            return null;
                    }
                case DIVISION:
                    switch (tipoResultado.getTipoPrimitivo()) 
                    {
                        case STRING:
                            System.out.println("ERROR no se puede dividir tipo string");
                            return null;
                        case DOUBLE:
                            try {
                                if (t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.DOUBLE) {
                                return ((char)(res1)) / (double)res2;
                                }else if(t1.getTipoPrimitivo() == Tipos.DOUBLE && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                    return (double)res1 / (char)res2;
                                }
                                return new Double(res1.toString()) / new Double(res2.toString());
                            } catch (Exception e) {
                                System.out.println("ERROR: Division con cero ["+e.getMessage());
                                return null;
                            }
                        case INT:
                        case CHAR:
                            try {
                                if (t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.INT) {
                                    return ((char)(res1)) / (int)res2;
                                }else if(t1.getTipoPrimitivo() == Tipos.INT && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                    return (int)res1 / (char)res2;
                                }else if(t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                    return (char)res1 / (char)res2;
                                }
                                return (int)res1 / (int)res2;
                            } catch (Exception e) {
                                System.out.println("ERROR: Division con cero ["+e.getMessage());
                                return null;
                            } 
                        default:
                            System.out.println("ERROR en division");
                            return null;
                    }
                case POTENCIA:
                    switch (tipoResultado.getTipoPrimitivo()) 
                    {
                        case STRING:
                            System.out.println("ERROR no se puede hacer potencia tipo string");
                            return null;
                        case DOUBLE:
                            try {
                                if (t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.DOUBLE) {
                                    return Math.pow((char)res1,(double)res2);
                                }else if(t1.getTipoPrimitivo() == Tipos.DOUBLE && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                    return Math.pow((double)res1, (char)res2);
                                }
                                return Math.pow(new Double(res1.toString()) , new Double(res2.toString()));
                            } catch (Exception e) {
                                System.out.println("ERROR: Potencia ["+e.getMessage());
                                return null;
                            }
                        case INT:
                        case CHAR:
                            try {
                                if (t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.INT) {
                                    return Math.pow(((char)(res1)) , (int)res2);
                                }else if(t1.getTipoPrimitivo() == Tipos.INT && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                    return Math.pow((int)res1 , (char)res2);
                                }else if(t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                    return Math.pow((char)res1 , (char)res2);
                                }
                                return Math.pow((int)res1 , (int)res2);
                            } catch (Exception e) {
                                System.out.println("ERROR: con potencia en: ["+e.getMessage());
                                return null;
                            }
                        default:
                            System.out.println("ERROR en potencia");
                            return null;
                    }
                case MODULO:
//                    if (!"0".equals(String.valueOf(res2))) {
                        switch (tipoResultado.getTipoPrimitivo()) 
                        {
                            case STRING:
                                System.out.println("ERROR no se puede hacer modulo tipo string");
                                return null;
                            case DOUBLE:
                                if (t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.DOUBLE) {
                                    return ((char)(res1)) % (double)res2;
                                }else if(t1.getTipoPrimitivo() == Tipos.DOUBLE && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                    return (double)res1 % (char)res2;
                                }
                                return new Double(res1.toString()) % new Double(res2.toString());
                            case INT:
                            case CHAR:
                                if (t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.INT) {
                                    return ((char)(res1)) % (int)res2;
                                }else if(t1.getTipoPrimitivo() == Tipos.INT && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                    return (int)res1 % (char)res2;
                                }else if(t1.getTipoPrimitivo() == Tipos.CHAR && t2.getTipoPrimitivo() == Tipos.CHAR) {
                                    return (char)res1 % (char)res2;
                                }
                                return (int)res1 % (int)res2;
                            default:
                                System.out.println("ERROR en multiplicacion");
                                return null;
                        }
//                    }
//                    return 0;
                    
            }
        //}
        }
        return null;
    }
}

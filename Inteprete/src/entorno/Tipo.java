/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entorno;

/**
 *
 * @author p_ab1
 */
public class Tipo {
    private Tipos tipoPrimitivo;   
    private String tipoObjeto;
    
    public boolean isNumeric(){
        if (tipoPrimitivo == Tipos.INT || tipoPrimitivo == Tipos.DOUBLE || tipoPrimitivo == Tipos.CHAR ) 
        {
            return true;
        }
        return false;
    }
    
    public boolean isBoolean(){
        return tipoPrimitivo == Tipos.BOOL;
    }
    
    public boolean isDouble(){
        return (tipoPrimitivo == Tipos.DOUBLE);
    }
    
    public boolean isInt(){
        return (tipoPrimitivo == Tipos.INT);
    }
    
    public boolean isChar(){
        return (tipoPrimitivo == Tipos.CHAR);
    }
    
    public boolean isString(){
        return (tipoPrimitivo == Tipos.STRING);
    }
    
    public Tipos getTipoPrimitivo() {
        return tipoPrimitivo;
    }
    

    public void setTipoPrimitivo(Tipos tipoPrimitivo) {
        this.tipoPrimitivo = tipoPrimitivo;
    }

    public String getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }
    

    public Tipo(Tipos tp) {
        this.tipoPrimitivo = tp;
    }

    public Tipo(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }
     
     public enum Tipos
    {
            STRING,
            INT,
            DOUBLE,
            CHAR,
            BOOL,
            NULL,
            OBJETO,
            VOID
    }
 
            
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.Nativo;

import entorno.Entorno;
import entorno.Simbolo;
import entorno.Tipo;

/**
 *
 * @author p_ab1
 */
public class Objeto extends Simbolo{
    private String idGenerador;
    private Entorno atributos;
                //string clase que lo genero
    //lista de simbolos funiones,clases,variables o tambien puede ser entorno

    public Objeto(String idGenerador, Entorno atributos, String identificador, Rol rol, Tipo tipo) {
        super(identificador, rol, tipo);
        this.idGenerador = idGenerador;
        this.atributos = atributos;
    }

    public String getIdGenerador() {
        return idGenerador;
    }

    public Entorno getAtributos() {
        return atributos;
    }
    
    
    
    
}

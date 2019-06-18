/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ast.Nativo;

/**
 *
 * @author p_ab1
 */
public class Modificador {
    private TipoMod tipoMod;

    public Modificador(TipoMod tipoMod) {
        this.tipoMod = tipoMod;
    }

    public TipoMod getTipoMod() {
        return tipoMod;
    }

    public void setTipoMod(TipoMod tipoMod) {
        this.tipoMod = tipoMod;
    }
    
    public enum TipoMod{
        PUBLIC,
        PRIVATE,
        PROTECTED,
        ABSTRACT,
        STATIC,
        FINAL
    }
}

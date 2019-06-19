/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inteprete;

import ast.AST;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JOptionPane;
/**
 *
 * @author p_ab1
 */
public class Inteprete {
    public static Ventana v;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        v = new Ventana();
        v.setVisible(true);       
        
    }  
    
    public static Ventana getVentana(){
        return v;
    }

}

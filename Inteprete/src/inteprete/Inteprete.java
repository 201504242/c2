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
  int cuenta = 0;
        do 
        { 
            cuenta++; 
            if (cuenta == 3) 
            { 
                continue; 
            } 
        System.out.println(cuenta); 
        } while (cuenta < 5);
//        for (int i = 0; i < 10; i++) {
//            for (int j = 10; j > 0; j--) {
//                System.out.println("i: "+i +" j: "+j);
//            }
//        }
//        int contador = 1;
//        while ( contador <= 5 ) {
//           System.out.println("*****\n");
//           contador++;
//        }
    }  
    
    public static Ventana getVentana(){
        return v;
    }

}

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
        
                
        try {
            /*FileWriter fw = new FileWriter("Entrada.txt");
            fw.write(entrada);
            fw.close();*/
//             String entrada = "C:\\Users\\p_ab1\\Desktop\\entrada.txt";
//            
//            new Sintactico(new Lexico(new BufferedReader(new FileReader(entrada)))).parse();
//            
//            AST arbol = Sintactico.arbol;
//            if (arbol != null) {
//                arbol.ejecutar();
//            }            
            v = new Ventana();
            v.setVisible(true);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "A ocurrido un Error "+ex.getMessage());
        }
        
        
    }  
    
    public static Ventana getVentana(){
        return v;
    }
    
     static int ackermann(int m, int n) {        
           
         if (m == 0) {
            return (n + 1);
        } else if (m > 0 && n == 0) {
            return ackermann(m - 1, 1);
        } else {
            return ackermann(m - 1, ackermann(m, n - 1));
        }
    }

}

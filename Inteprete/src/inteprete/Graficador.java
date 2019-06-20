/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inteprete;

import ast.AST;
import ast.general.Expresion;
import ast.general.Instruccion;
import ast.general.NodoAST;
import entorno.Entorno;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

/**
 *
 * @author p_ab1
 */
public class Graficador {
    static LinkedList<Graficador> listaEntorno = new LinkedList();
    String id;
    Entorno save;

    public Graficador(String id, Entorno save) {
        this.id = id;
        this.save = save;
    }
    
    
    public static void addEntorno(String id, Entorno e){
        listaEntorno.add(new Graficador(id,e));
    }
    
     public static String recorrido(AST raiz) {
        String cuerpo = "";
        for (NodoAST hijo : raiz.getInstrucciones()) {
            if (hijo!=null) {
                if (hijo instanceof Instruccion) 
                {
                    Instruccion i = (Instruccion) hijo;
                }
                else if (hijo instanceof Expresion) {
                    Expresion e = (Expresion) hijo;
                    cuerpo += "\"" + e.getClass().getCanonicalName() +"_"+e.getClass().getTypeName();
                }
//            cuerpo += "\"" + raiz.idNodo + "_" + raiz.Etiqueta + "=" + raiz.valor + "\"->\"" + hijos.idNodo + "_" + hijos.Etiqueta + "=" + hijos.valor + "\"";
//            cuerpo += recorrido2(hijo);            
            }
        }
        return cuerpo;
    }
     
    public static void Graficar(String cadena, String cad) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        String nombre = cad;
        String archivo = nombre + ".dot";
        try {
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);
            pw.println("digraph G {node[shape=box, style=filled, color=Gray95]; edge[color=blue];rankdir=UD \n");
            pw.println(cadena);
            pw.println("\n}");
            fichero.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";
            //path del archivo creado con el codigo del graphviz que queremos
            String fileInputPath = nombre+".dot";
            //path de salida del grafo, es decir el path de la imagen que vamos a crear con graphviz
            String fileOutputPath = nombre+".jpg";
            //tipo de imagen de salida, en este caso es jpg
            String tParam = "-Tjpg";
            String tOParam = "-o";
            //recordemos el comando en la consola de windows: C:\Archivos de programa\Graphviz 2.21\bin\dot.exe -Tjpg grafo1.txt -o grafo1.jpg Esto es lo que concatenamos en el vector siguiente:
            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;
            Runtime rt = Runtime.getRuntime();
            rt.exec( cmd );
            
//            String cmd = "dot.exe -Tpng " + nombre + ".dot -o " + cad + ".png"; //Comando de apagado en linux
//            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}

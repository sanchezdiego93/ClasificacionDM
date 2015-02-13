/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.JFrame;
import weka.classifiers.*;
import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author diegosanchez
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        //
        JFrame frame = new JFrame("Clasificacion");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Formulario_Entrada fr = new Formulario_Entrada();
        fr.setSize(584, 297);
        frame.getContentPane().add(fr);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);        
    }
    
}

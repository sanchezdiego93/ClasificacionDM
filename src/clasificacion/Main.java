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
        String[] data = new String[8];
        data[0] = "usual";
        data[1] = "proper";
        data[2] = "complete";
        data[3] = "1";
        data[4] = "convenient";
        data[5] = "convenient";
        data[6] = "nonprob";
        data[7] = "recommended";
        
        Clasificacion cl = new Clasificacion();
        
        cl.clasificar(data);
        
    }
    
}

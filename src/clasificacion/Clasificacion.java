/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificacion;

import java.io.InputStream;
import java.util.Dictionary;
import java.util.Enumeration;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

/**
 *
 * @author diegosanchez
 */
public class Clasificacion {
    private Classifier classModel;
    private Instances dataModel;
    private String classModelFile = "";

    public Clasificacion() throws Exception {
        InputStream classModelStream;
		
		//  Create a stream object for the model file embedded
		//  within the JAR file.
		
                classModelStream = getClass().getResourceAsStream(classModelFile);
		classModel = (Classifier)SerializationHelper.read(classModelStream);
    }
    
    public void close() {
        classModel = null;
        classModelFile = null;
    }
    
    public String classifySpecies(Dictionary<String, String> measures) throws Exception {
        
        FastVector dataClasses = new FastVector();
        FastVector dataAttribs = new FastVector();
        Attribute Class;
        
        double values[] = new double[measures.size() + 1];
        int i = 0, maxIndex = 0;
        
        dataClasses.addElement("not_recom");
        dataClasses.addElement("recommend");
        dataClasses.addElement("very_recom");
        dataClasses.addElement("priority");
        dataClasses.addElement("spec_prior");
        
        Class = new Attribute("class", dataClasses);
        
        for (Enumeration<String> keys = measures.keys(); keys.hasMoreElements(); ) {
                String key = keys.nextElement();
                double val = Double.parseDouble(measures.get(key));			
                dataAttribs.addElement(new Attribute(key));
                values[i++] = val;
        }
        
        dataAttribs.addElement(Class);
        dataModel = new Instances("classify", dataAttribs, 0);
        dataModel.setClass(Class);
        dataModel.add(new Instance(1, values));
        
        double cl[] = classModel.distributionForInstance(dataModel.instance(0));

        for(i = 0; i < cl.length; i++){
                if(cl[i] > cl[maxIndex]){
                        maxIndex = i;
                }
        }
        
        return dataModel.classAttribute().value(maxIndex);
    }
}

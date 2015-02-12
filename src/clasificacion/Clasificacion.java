/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasificacion;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.core.SerializationHelper;

/**
 *
 * @author diegosanchez
 */
public class Clasificacion {
    
    public Clasificacion(){
    }
    
    public void clasificar(String[] testCases) throws Exception{
        String ruta = "nursery_model.model";

        InputStream classModelStream;
        classModelStream = getClass().getResourceAsStream(ruta);
        //classModel = (Classifier)SerializationHelper.read(classModelStream);
        Classifier clasify = (Classifier)SerializationHelper.read(classModelStream);
        
        FastVector parents = new FastVector();
        parents.addElement("usual");
        parents.addElement("pretentious");
        parents.addElement("great_pret");
        Attribute _parent = new Attribute("parents", parents); 

        FastVector nurs = new FastVector();
        nurs.addElement("proper");
        nurs.addElement("less_proper");
        nurs.addElement("improper");
        nurs.addElement("critical");
        nurs.addElement("very_crit");
        Attribute _has_nurs = new Attribute("has_nurs", nurs);

        FastVector form = new FastVector();
        form.addElement("complete");
        form.addElement("completed");
        form.addElement("incomplete");
        form.addElement("foster");
        Attribute _form = new Attribute("form", form);

        FastVector children = new FastVector();
        children.addElement("1");
        children.addElement("2");
        children.addElement("3");
        children.addElement("more");
        Attribute _children = new Attribute("children", children);

        FastVector housing = new FastVector();
        housing.addElement("convenient");
        housing.addElement("less_conv");
        housing.addElement("critical");
        Attribute _housing = new Attribute("housing", housing);

        FastVector finance = new FastVector();
        finance.addElement("convenient");
        finance.addElement("inconv");
        Attribute _finance = new Attribute("finance", finance);

        FastVector social = new FastVector();
        social.addElement("nonprob");
        social.addElement("slightly_prob");
        social.addElement("problematic");
        Attribute _social = new Attribute("social", social);

        FastVector health = new FastVector();
        health.addElement("recommended");
        health.addElement("priority");
        health.addElement("not_recom");
        Attribute _health = new Attribute("health", health);

        FastVector Class = new FastVector();
        Class.addElement("not_recom");
        Class.addElement("recommend");
        Class.addElement("very_recom");
        Class.addElement("priority");
        Class.addElement("spec_prior");
        Attribute _Class = new Attribute("class", Class);
        
        FastVector atributos = new FastVector(9);
        atributos.addElement(_parent);
        atributos.addElement(_has_nurs);
        atributos.addElement(_form);
        atributos.addElement(_children);
        atributos.addElement(_housing);
        atributos.addElement(_finance);
        atributos.addElement(_social); 
        atributos.addElement(_health);
        atributos.addElement(_Class);
        
        ArrayList<Attribute> atributs = new ArrayList<>();
        atributs.add(_parent);
        atributs.add(_has_nurs);
        atributs.add(_form);
        atributs.add(_children);
        atributs.add(_housing);
        atributs.add(_finance);
        atributs.add(_social);
        atributs.add(_health);
        atributs.add(_Class);
        
        //Aquí se crea la instacia, que tiene todos los atributos del modelo
        Instances dataTest = new Instances("TestCases", atributos, 1);
        dataTest.setClassIndex(8);
        
        
        Instance setPrueba = new Instance(9);

        int index = -1;
        for(int i = 0; i<8; i++)
        {
            index = atributs.get(i).indexOfValue(testCases[i]);
            //System.out.println(i + " " + atributs.get(i)  + " " + index + " " + testCases[i]);
            setPrueba.setValue(atributs.get(i), index);

        }

        //Agregando el set que se desea evaluar.
        dataTest.add(setPrueba);

        //Realizando la Predicción
        //La instancia es la 0 debido a que es la unica que se encuentra.
        double valorP = clasify.classifyInstance(dataTest.instance(0));
        //get the name of the class value
        String prediccion=dataTest.classAttribute().value((int)valorP);
        
        System.out.println(prediccion);
    }
}

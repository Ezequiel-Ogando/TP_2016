/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mvnp;

import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualRecognitionOptions;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author utnso
 */
public class Manager {
    
    public VisualRecognition crear_servicio()
    {
      VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_19);
      service.setApiKey("ebe3359ae70494ba901dfde15b81dcf6816ce9e3");
      
      return(service);
    }
    
    public VisualRecognitionOptions crear_options_face(String ruta)
    {
        VisualRecognitionOptions options = new VisualRecognitionOptions.Builder().images(new File(ruta)).build();
        return options;
    }
    
    public String consultar_caras( VisualRecognition service, VisualRecognitionOptions options)
    {
        DetectedFaces result = service.detectFaces(options).execute();
        return(result.toString());
    }
    
    public String clasificar(VisualRecognition service, ClassifyImagesOptions options_cl)
    {
        VisualClassification clasificaciones = service.classify(options_cl).execute();
        return (clasificaciones.toString());
    }
    
    public ClassifyImagesOptions crear_options_clasify(VisualRecognition service, String ruta)
    {
    //ArrayList <VisualClassifier> lista = new ArrayList<VisualClassifier>();
       List<VisualClassifier> lista =  service.getClassifiers().execute();
       System.out.println("cantidad de clasificadores: " + lista.size() );
       System.out.println(lista.get(0).toString());
       System.out.println(lista.get(1).toString());
       
       //------------------- ARMAR LISTA DE CLASIFICADORES
       List<String> lista_clasif = new ArrayList<String>();
       //JSONObject obj_clasif = new JSONObject(lista.toString());
       
       for (int i=0; i < lista.size(); i++)
       {
           JSONObject obj_clasif = new JSONObject(lista.get(i).toString());
           lista_clasif.add(obj_clasif.getString("classifier_id"));
       }
       lista_clasif.add("default");
        ClassifyImagesOptions options_cl = new ClassifyImagesOptions.Builder().images(new File(ruta)).classifierIds(lista_clasif).build();
        return (options_cl);
    }
}

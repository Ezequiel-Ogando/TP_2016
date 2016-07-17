/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mvnp;



import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.DetectedFaces;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ImageFace;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualRecognitionOptions;
import java.io.File;
import org.json.*;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author utnso
 */
public class camara {

    /**
     * @param args the command line arguments
     */
    private static Clasificador clasificador = new Clasificador();
    
    public static void main(String[] args) {
        // TODO code application logic here
      VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_19);
      service.setApiKey("ebe3359ae70494ba901dfde15b81dcf6816ce9e3");
      
      String ruta;
      if ( args.length > 0 )
        ruta = args[0];
      else
          ruta = "/home/utnso/31.jpg";
      //ruta = "/home/utnso/31.jpg";
      
      /*System.out.println("Classify an image");
      ClassifyImagesOptions options = new ClassifyImagesOptions.Builder().images(new File("/home/utnso/cara.jpg")).build();
      VisualClassification result = service.classify(options).execute();
      System.out.println(result);*/
      
      System.out.println("Faces detection");
      
      //VisualRecognitionOptions options = new VisualRecognitionOptions.Builder().images(new File("/home/utnso/31.jpg")).build();
      VisualRecognitionOptions options = new VisualRecognitionOptions.Builder().images(new File(ruta)).build();
      
      DetectedFaces result = service.detectFaces(options).execute();
      //result.getImages().get(0);
      //System.out.println(result.getImages().toArray().length);
      
      String cad = result.toString();
      System.out.println(result);
      
       char mayoria = clasificador.mayoria(result.toString());
//       System.out.println("genero predominante: " + mayoria);
       
       Edad rango = clasificador.rango_edad(mayoria, cad);
       System.out.println("rango: " + rango.get_inferior() + "-" + rango.get_superior());
       
      
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
       VisualClassification clasificaciones = service.classify(options_cl).execute();
       System.out.println("Resultado de clasificacion: ");
       System.out.println(clasificaciones.toString());
              
      
    }
    
    public void calsificar()
    {
        
        
    }
    
}

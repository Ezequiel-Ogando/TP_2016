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
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualRecognitionOptions;
import java.io.File;

import static java.lang.Thread.sleep;


/**
 *
 * @author utnso
 */
public class camara {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_19);
      service.setApiKey("9627434df5f2fbab7ccbae527d934b2a7d0b36bd");
      
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
      VisualRecognitionOptions options = new VisualRecognitionOptions.Builder().images(new File(ruta.toString())).build();
      
      DetectedFaces result = service.detectFaces(options).execute();
      //result.getImages().get(0);
      System.out.println(result.getImages().toArray().length);
      
      String cad = result.toString();
      System.out.println(result);
              
      
    }
    
}

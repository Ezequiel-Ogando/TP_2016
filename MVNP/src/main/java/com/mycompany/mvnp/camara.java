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

    private static Clasificador clasificador = new Clasificador();
    
    public static void main(String[] args) {
      
      String ruta;
      if ( args.length > 0 )
        ruta = args[0];
      else
          ruta = "/home/utnso/grupo.jpg";

      Manager manejador = new Manager();
      VisualRecognition service = manejador.crear_servicio();
      
      VisualRecognitionOptions options_face = manejador.crear_options_face(ruta);
      ClassifyImagesOptions options_clasif = manejador.crear_options_clasify(service, ruta);
      
      System.out.println("Faces detection");
      String caras_detectadas = manejador.consultar_caras(service, options_face);
      System.out.println(caras_detectadas);
      char mayoria = clasificador.mayoria(caras_detectadas);
      //Si mayoria es N entonces no se detectaron personas o hubo un error interno en el servicio
      if (mayoria != 'N')
      {       
        System.out.println("Mayoria: " + mayoria);
        Edad rango = clasificador.rango_edad2(mayoria, caras_detectadas);
        System.out.println("rango: " + rango.get_inferior() + "-" + rango.get_superior()); 
       
        String clasificaciones = manejador.clasificar(service, options_clasif);
       
        System.out.println("Resultado de clasificacion: ");
        System.out.println(clasificaciones);
        
        mostrar_marcas(clasificaciones);
        
      }
              
      
    }
    
    private static void mostrar_marcas(String clasificaciones)
    {
        List<Marca> marcas = clasificador.listar_marcas(clasificaciones);
        if (!marcas.isEmpty())
        {
            for(int i=0; i< marcas.size(); i++)
            {
                System.out.println("id: " + marcas.get(i).get_id() + ", nombre: " + marcas.get(i).get_nombre());
            }
        }
    }
    
}

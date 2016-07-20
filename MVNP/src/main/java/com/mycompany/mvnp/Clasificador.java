/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mvnp;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author utnso
 */
public class Clasificador {
    
    public char mayoria(String cad)
    {
        char c=' ';
        int cant_h = 0;
        int cant_m = 0;
        JSONObject obj = new JSONObject(cad);
        JSONArray ar_imagen = obj.getJSONArray("images");
        JSONObject imagen = ar_imagen.getJSONObject(0);
        JSONArray arr_caras;
        try{
            arr_caras = imagen.getJSONArray("faces");
            if (arr_caras.length() != 0)
            {
                for (int i = 0; i< arr_caras.length(); i++){
                    String genero;
                    try{
                        genero = arr_caras.getJSONObject(i).getJSONObject("gender").getString("gender");
                    }
                    catch(Exception ex){
                        genero="MALE";
                    }
                    if (genero.length() == 4)
                        cant_h++;
                    else
                        cant_m++;
                    System.out.println(genero);
                }
      
                if (cant_h > cant_m)  
                    c='M';
                else
                    c='F';
            }
            else
                c='N';
        }
        catch(Exception e1){
            c='N';
        }
       return c;
    }
    
    public Edad rango_edad(char predominante, String cad)
    {
        Edad rango = new Edad();
        JSONObject obj = new JSONObject(cad);
        JSONArray ar_imagen = obj.getJSONArray("images");
        JSONObject imagen = ar_imagen.getJSONObject(0);
        JSONArray arr_caras = imagen.getJSONArray("faces");
        
        int cant_cambios = 0;
        int minimo_global=0;
        int maximo_global=0;
        int max_cambio = -1;
        int sw;
        List<Edad> personas = this.listar_por_genero(predominante, arr_caras);
        for (int j = 0; j< personas.size(); j++){
                                      
                rango.set_superior(personas.get(j).get_superior());
                rango.set_inferior(personas.get(j).get_inferior());
                cant_cambios=0;
                System.out.println(j + " ( " + rango.get_inferior() + ", " + rango.get_superior() + " )");
                
                for (int i = 0 ; i< personas.size(); i++){
                
                    int limite_sup = personas.get(i).get_superior();
                    int limite_inf = personas.get(i).get_inferior();
                    sw = 0;
                    if ( (limite_inf <= rango.get_inferior()) && (limite_sup >= rango.get_superior())){
                        // Si el rango esta incluido dentro de los limites de la persona registro un acierto
                        sw=1;
                    }
                    else
                    {
                        if ( ( limite_inf >= rango.get_inferior() ) && ( limite_inf <=  rango.get_superior() )  )
                        {
                            rango.set_inferior( limite_inf );
                            sw=1;
                        }
                
                        if ( ( limite_sup   <= rango.get_superior() ) && ( limite_sup >=  rango.get_inferior() ) )
                        {
                            rango.set_superior( limite_sup );
                            sw=1;
                        }
                    }
                    if (sw != 0)
                        cant_cambios++;
                        //System.out.println(genero + "( " + limite_inf + ", " + limite_sup + " )");
                }
                if (cant_cambios > max_cambio)
                {
                    minimo_global=rango.get_inferior();
                    maximo_global=rango.get_superior();
                    max_cambio = cant_cambios;
                }
        }
        rango.set_inferior(minimo_global);
        rango.set_superior(maximo_global);
        return rango;
    }
    
    public List<Edad> listar_por_genero(char predominante, JSONArray arr_caras)
    {
        List<Edad> personas = new ArrayList<Edad>();
        
        for (int j = 0; j< arr_caras.length(); j++){
            
             String genero = arr_caras.getJSONObject(j).getJSONObject("gender").getString("gender");
             if (genero.charAt(0) == predominante)
             {
                Edad persona = new Edad();
                
                try{ 
                    persona.set_superior( arr_caras.getJSONObject(j).getJSONObject("age").getInt("max"));
                }
                catch(Exception ex){
                    persona.set_superior(100); 
                }
                
                try{
                    persona.set_inferior(arr_caras.getJSONObject(j).getJSONObject("age").getInt("min"));
                }
                catch(Exception ex){
                    persona.set_inferior(1);
                }
                
                personas.add(persona);
             }
        }
        
        return personas;
    }
    
    public List<Marca> listar_marcas(String cad)
    {
        List<Marca> lista_marcas = new ArrayList<Marca>();
        try{
            JSONObject obj = new JSONObject(cad);
            JSONArray ar_imagen = obj.getJSONArray("images");
            JSONObject imagen = ar_imagen.getJSONObject(0);
            JSONArray arr_classifiers = imagen.getJSONArray("classifiers");
            if (arr_classifiers.length() != 0)
            {
                Marca elemento = new Marca();
                for (int i = 0; i< arr_classifiers.length(); i++){
                    try{
                        elemento.set_id( arr_classifiers.getJSONObject(i).getString("classifier_id") );
                        elemento.set_nombre( arr_classifiers.getJSONObject(i).getString("name") );
                        lista_marcas.add(elemento);
                    }
                    catch(Exception ex){
                    }
                }
            }
        }
        catch(Exception e){
        }
        return(lista_marcas);
    }
    
    
     public Edad rango_edad2(char predominante, String cad)
    {
        Edad rango = new Edad();
        JSONObject obj = new JSONObject(cad);
        JSONArray ar_imagen = obj.getJSONArray("images");
        JSONObject imagen = ar_imagen.getJSONObject(0);
        JSONArray arr_caras = imagen.getJSONArray("faces");
        
        List<Edad> personas = this.listar_por_genero(predominante, arr_caras);
        int mayor=0;
        int pos_ini=0;
        int sw = 0;
        int [] proyeccion = new int [101];
        
        for (int i = 0; i <= 100; i++){
            proyeccion[i]=0;
        }
        
        for (int j = 0; j< personas.size(); j++){
            
            int desde = personas.get(j).get_inferior();
            int hasta = personas.get(j).get_superior();
            sw=0;
            
            while (desde <= hasta){
                proyeccion[desde]++;
                if (proyeccion[desde] > mayor)
                {
                    mayor = proyeccion[desde];
                    //pos_ini = desde;    
                    rango.set_inferior(desde);
                    sw=1;
                }
                else
                    if ( (proyeccion[desde] == mayor) && (sw==1) )
                        rango.set_superior(desde);
                desde++;
            }
            
        }
        
        //int pos_fin = pos_ini;
        
        
        /*while ( (sw!= 1) && (pos_fin <= 100) ){
            pos_fin++;
            if (proyeccion[pos_fin] != mayor)
                sw=1;
        }*/
        
        for (int m=0; m < 100; m+=10){
            System.out.println(proyeccion[m] + " " + proyeccion[m+1] + " " + proyeccion[m+2] + " " + proyeccion[m + 3] + " " + proyeccion[m+4] + " " + proyeccion[m + 5] +
                    " " + proyeccion[m + 6] + " "  + proyeccion[m+7] + " " + proyeccion[m+8] + " " + proyeccion[m+ 9]
            );
        }
        //rango.set_inferior(pos_ini);
        //rango.set_superior(pos_fin);
        return rango;
    }
    
    
    
    
}



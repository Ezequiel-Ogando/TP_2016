/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mvnp;

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
       
           //AGREGADO
      JSONObject obj = new JSONObject(cad);
      JSONArray ar_imagen = obj.getJSONArray("images");
      JSONObject imagen = ar_imagen.getJSONObject(0);
      JSONArray arr_caras = imagen.getJSONArray("faces");
      
      //JSONObject arr = obj.getJSONObject("images");
      
      if (arr_caras.length() != 0)
      {
        for (int i = 0; i< arr_caras.length(); i++){
            String genero = arr_caras.getJSONObject(i).getJSONObject("gender").getString("gender");
            if (genero.compareTo("MALE") == 1)
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
       return c;
    }
    
    public Edad rango_edad(char predominante, String cad)
    {
        Edad rango = new Edad();
        JSONObject obj = new JSONObject(cad);
        JSONArray ar_imagen = obj.getJSONArray("images");
        JSONObject imagen = ar_imagen.getJSONObject(0);
        JSONArray arr_caras = imagen.getJSONArray("faces");
        
        for (int i = 0; i< arr_caras.length(); i++){
            String genero = arr_caras.getJSONObject(i).getJSONObject("gender").getString("gender");
            int limite_sup = arr_caras.getJSONObject(i).getJSONObject("age").getInt("max");
            int limite_inf = arr_caras.getJSONObject(i).getJSONObject("age").getInt("min");
            
            if (genero.charAt(0) == predominante)
            {
                System.out.println(genero + "( " + limite_inf + ", " + limite_sup + " )");
                
                if ( ( limite_inf   > rango.get_inferior() ) && ( limite_inf <  rango.get_superior() )  )
                    rango.set_inferior( limite_inf );
                
                if ( ( limite_sup   < rango.get_superior() ) && ( limite_sup >  rango.get_inferior() ) )
                    rango.set_superior( limite_sup );
              
                System.out.println(genero + "( " + limite_inf + ", " + limite_sup + " )");
            }
            
            
        }
        return rango;
    }
    
}

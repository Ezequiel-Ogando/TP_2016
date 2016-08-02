/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zz1;

/**
 *
 * @author utnso
 */

import cl.model.dao.HibernateUtil;
import cl.model.dao.PublicidadDAO;
import cl.model.dao.MarcaDAO;
import cl.model.pojos.Publicidad;
import cl.model.pojos.Marca;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.stream.FileImageInputStream;
import org.hibernate.SessionFactory;


public class ZZ1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       
       /* MarcaDAO ma = new MarcaDAO();
        String descripcion = ma.consultarTipoPorMarca("nike_1354551410 ");
        if (descripcion.length() > 2)
            System.out.println(descripcion);*/
       
        /*PublicidadDAO pu = new PublicidadDAO();
        pu.consultarJoin();*/
    
    
    //  PARA AGREGAR UNA PUBLICIDAD O MARCA:
    /*  Ingreso ingresar = new Ingreso();
      ingresar.ingresar_marca();
      ingresar_publicidad();*/
        
        Ingreso ingresar = new Ingreso();
        
        //Creo lista de marcas detectadas vacia o con elementos
        //List<Marca_item> marcas_detectadas = new ArrayList<Marca_item>();
        List<Marca_item> marcas_detectadas = ingresar.generarLista();
        char sexo = 'm';
        byte min=1, max=100, h=3;
        
        if (marcas_detectadas.size() == 0){
            traerPublicidadSinTipo(sexo, min, max, h);          
        }
        else{
            traerPublicidadConTipo(marcas_detectadas, sexo, min, max, h);
        }
        
    }
    
    public static void traerPublicidadSinTipo(char sexo, byte min, byte max, byte h){
        PublicidadDAO pubDao = new PublicidadDAO();
        
        List<Integer> lista_pub = pubDao.consultarIdPublicidadesSinTipo(sexo, min, max, h);
            
        if (!lista_pub.isEmpty()){
            //Encontro publicidades que cumplan con el criterio
            System.out.println("cantidad elementos: " + lista_pub.size());
            int    eleccion = (int) (Math.random() * lista_pub.size());
            Publicidad pu = pubDao.consultarPorId(lista_pub.get(eleccion));
            System.out.println("indice de lista: " + eleccion);
            System.out.println("publicidad recuperada id:" + pu.getPubId());
        }
        else{
            //No encontro publicidades que cumplan con el criterio, ahora busca una publicidad para cualquier perfil
            int cantidad_registros_tabla = pubDao.MaximoId();
            System.out.println("Maximo id: " + cantidad_registros_tabla);
            int id;
                if (cantidad_registros_tabla != 1)
                    id = (int) (Math.random() * (cantidad_registros_tabla)) + 1;
                else
                    id = 1;
            Publicidad pu = pubDao.consultarPorId(id);
            System.out.println("publicidad recuperada id: " + pu.getPubId());
        }
    }
    
    public static void traerPublicidadConTipo(List<Marca_item> marcas_detectadas, char sexo, byte min, byte max, byte h){
        PublicidadDAO pubDao = new PublicidadDAO();
        MarcaDAO maDao = new MarcaDAO();
        //char sexo = 'm';
        //byte min=1, max=100, h=3;
        
        int sw=0;
        int i=0;
        while ( (i < marcas_detectadas.size()) && (sw==0)){
            String tipo_ropa = maDao.consultarTipoPorMarca(marcas_detectadas.get(i).get_nombre());
            System.out.println("nombre: " + marcas_detectadas.get(i).get_nombre());
            System.out.println("tipo de ropa: " + tipo_ropa);
            List<Integer> lista_pub = pubDao.consultarJoin(sexo, min, max, h, tipo_ropa);
            if (!lista_pub.isEmpty()){
                int eleccion = (int) (Math.random() * (lista_pub.size()));
                System.out.println("indice lista: " + eleccion);
                Publicidad pu = pubDao.consultarPorId(lista_pub.get(eleccion));
                System.out.println("publicidad recuperada id:" + pu.getPubId());
                sw=1;
            }
            i++;
        }
        if (sw == 0){
            traerPublicidadSinTipo(sexo, min, max, h);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zz1;

import cl.model.dao.MarcaDAO;
import cl.model.dao.PublicidadDAO;
import cl.model.pojos.Marca;
import cl.model.pojos.Publicidad;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.stream.FileImageInputStream;

/**
 *
 * @author utnso
 */
public class Ingreso {
   
    public void ingresar_publicidad(){
        FileImageInputStream fileimput = null;
        File file = new File("/home/utnso/4.jpg");
        byte[] fileArray = new byte[(int) file.length()];
        try{
            fileimput = new FileImageInputStream(file);
            fileimput.read(fileArray);
            fileimput.close();
            MarcaDAO madao = new MarcaDAO();
            // Mapea el registro de marca segun el Id a un objeto java
            Marca ma = madao.consultarPorId(3);
            byte edad_min=1, edad_max=100, hor_min = 1, hor_max = 24;
            char genero = 'M';
            Publicidad p = new Publicidad( ma, genero, edad_min, edad_max, hor_min, hor_max, fileArray);
            PublicidadDAO pud = new PublicidadDAO();
            pud.insertarPublicidad(p);
        }
        catch(Exception ex)
        {
            System.out.println("No se pudo grabar");
        }
        
    }
    
    public void ingresar_marca(){
                MarcaDAO madao = new MarcaDAO();
                Marca ma = new Marca("rolex", "relojes");
                madao.insertarMarca(ma);
    }
    
    public List<Marca_item> generarLista(){
        List<Marca_item> l_marcas = new ArrayList<Marca_item>();
        Marca_item item = new Marca_item();
        item.set_id("rolex_xxx");
        item.set_nombre("rolex");
        l_marcas.add(item);
      /*  Marca_item item2 = new Marca_item();
        item2.set_id("puma_xxx");
        item2.set_nombre("puma");
        l_marcas.add(item2);*/
        Marca_item item3 = new Marca_item();
        item3.set_id("legacy_xxx");
        item3.set_nombre("legacy");
        l_marcas.add(item3);
        Marca_item item2 = new Marca_item();
        item2.set_id("puma_xxx");
        item2.set_nombre("puma");
        l_marcas.add(item2);
        return l_marcas;
    }
            
}

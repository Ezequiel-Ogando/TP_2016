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
public class Marca_item {
    private String id;
    private String nombre;
    
    public void set_id(String id_marca)
    {
        id = id_marca;
    }
    
    public void set_nombre(String nombre_marca)
    {
        nombre = nombre_marca;
    }
    
    public String get_id()
    {
        return(id);
    }
    
    public String get_nombre()
    {
        return(nombre);
    }
    
}

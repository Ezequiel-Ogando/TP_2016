package cl.model.pojos;
// Generated 31/07/2016 18:38:38 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Marca generated by hbm2java
 */
public class Marca  implements java.io.Serializable {


     private Integer marcaId;
     private String nombre;
     private String descripcion;
     private Set publicidads = new HashSet(0);

    public Marca() {
    }

	
    public Marca(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public Marca(String nombre, String descripcion, Set publicidads) {
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.publicidads = publicidads;
    }
   
    public Integer getMarcaId() {
        return this.marcaId;
    }
    
    public void setMarcaId(Integer marcaId) {
        this.marcaId = marcaId;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set getPublicidads() {
        return this.publicidads;
    }
    
    public void setPublicidads(Set publicidads) {
        this.publicidads = publicidads;
    }




}



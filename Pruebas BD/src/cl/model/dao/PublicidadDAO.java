/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model.dao;

/**
 *
 * @author utnso
 */

import cl.model.pojos.Publicidad;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;
import java.util.List;
import org.hibernate.SQLQuery;

public class PublicidadDAO {
    
    public Publicidad consultarPorId(int pub_id)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Publicidad pu = (Publicidad) sesion.get(Publicidad.class, pub_id);
        return pu;
    }
    
    public List<Integer> consultarJoin(char sexo, byte edad_min, byte edad_max, byte hora, String descripcion)
    {
        String res=" ";
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        /*SQLQuery query = sesion.createSQLQuery("select P.* from publicidad P inner join marca M on M.marca_id = P.marca_id ");
        List<Publicidad> ma = (List<Publicidad>) query.list();*/
        /*Query query = sesion.createQuery("select P.pubId from Publicidad P inner join P.marca as M on M.descripcion = '" + descripcion + "'"
                + " and P.sexo = '" + sexo + "' and P.edadMin <= " + edad_min + " and P.edadMax >= " + edad_max
       + " and P.horarioMin <= " + hora + " and P.horarioMax >= " + hora);*/
        Query query = sesion.createQuery("select P.pubId from Publicidad P inner join P.marca as M on M.descripcion = '" + descripcion + "'"
                + " where P.sexo = '" + sexo + "' and P.edadMin >= " + edad_min + " and P.edadMin <= " + edad_min + " and P.edadMax >= " + edad_max 
                + " and P.horarioMin <= " + hora + " and P.horarioMax >= " + hora);
                
        List<Integer> lista_ids = new ArrayList<Integer>();
        lista_ids = query.list();
        sesion.close();
        return lista_ids;
    }
    
    public List<Integer> consultarIdPublicidadesSinTipo(char sexo, byte edad_min, byte edad_max, byte hora)
    {
       List<Integer> lista_publicidades = new ArrayList<Integer>();
       String res=" ";
       SessionFactory sf = HibernateUtil.getSessionFactory();
       Session sesion = sf.openSession();
       Query query = sesion.createQuery("select pubId from Publicidad where sexo = '" + sexo + "' and edadMin <= " + edad_min + " and edadMax >= " + edad_max
       + " and horarioMin <= " + hora + " and horarioMax >= " + hora);
       lista_publicidades = query.list();
       sesion.close();
        return lista_publicidades;
    
    }
   
    public Integer MaximoId(){
        Integer res=1;
        SessionFactory sf = HibernateUtil.getSessionFactory();
       Session sesion = sf.openSession();
       Query query = sesion.createQuery("select max(pubId) from Publicidad");
       List<Integer> lista_pub = query.list();
       if (!lista_pub.isEmpty()){
           res = lista_pub.get(0);
       }
        return res;
    }
    
    public String insertarPublicidad(Publicidad pu)
    {
        String resultado = "transaccion satisfactoria";
        SessionFactory sf = null;
        Session sesion = null;
        Transaction tx = null;
        try{
            sf = HibernateUtil.getSessionFactory();
            sesion = sf.openSession();
            tx = sesion.beginTransaction();
            sesion.save(pu);
            tx.commit();
            sesion.close();
        }
        catch(Exception ex){
            tx.rollback();
            resultado = "transaccion detenida";
        }
        return resultado;
    }
}

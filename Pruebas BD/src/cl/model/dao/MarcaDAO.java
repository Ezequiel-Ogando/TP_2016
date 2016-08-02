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

import cl.model.pojos.Marca;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;
import java.util.List;
import org.hibernate.SQLQuery;

public class MarcaDAO {
    
    /*public Marca consultarPorId(int marca_id)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Marca ma = (Marca) sesion.get(Marca.class, marca_id);
        return ma;
    }*/
    
    public String consultarTipoPorMarca(String nombre)
    {
        String res=" ";
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Query query = sesion.createQuery("from Marca where nombre = '" + nombre +"'");
        List<Marca> ma = query.list();
        sesion.close();
        if (ma.isEmpty())
            res ="N";
        else
            res = ma.get(0).getDescripcion();
        return res;
    }
    
    public Marca consultarPorId(int id)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Marca ma = (Marca) sesion.get(Marca.class, id);
        return ma;
    }
    
    public String insertarMarca(Marca ma)
    {
        String resultado = "transaccion satisfactoria";
        SessionFactory sf = null;
        Session sesion = null;
        Transaction tx = null;
        try{
            sf = HibernateUtil.getSessionFactory();
            sesion = sf.openSession();
            tx = sesion.beginTransaction();
            sesion.save(ma);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mvnp;

/**
 *
 * @author utnso
 */
public class Edad {
    private int intervalo_inf=0;
    private int intervalo_sup=200;
    
    public void set_inferior(int inf)
    {
        intervalo_inf = inf;
    }
    
    public void set_superior(int sup)
    {
        intervalo_sup = sup;
    }
    
    public int get_inferior()
    {
        return(intervalo_inf);
    }
    
    public int get_superior()
    {
        return(intervalo_sup);
    }
    
}

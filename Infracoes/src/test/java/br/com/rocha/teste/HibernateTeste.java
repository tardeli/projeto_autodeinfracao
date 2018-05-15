/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rocha.teste;

import br.com.rocha.infracoes.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author DaRocha
 */
public class HibernateTeste {
    @Test
    //@Ignore
    public void rodarBanco(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.close();
        HibernateUtil.getSessionFactory().openSession();
    
    }
    
}


package br.com.rocha.infracoes.dao;

import br.com.rocha.infracoes.modelo.Guarda;
import br.com.rocha.infracoes.util.HibernateUtil;
import java.io.Serializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author DaRocha
 */
public class GuardaDao extends Generic_Dao<Guarda> implements Serializable{
    private Session sessao;   
    
    public boolean pesquisarCodigo(Integer codigo){
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sessao.createCriteria(Guarda.class);
            criteria.add(Restrictions.ilike("codigo", codigo));
            Guarda guarda = (Guarda) criteria.uniqueResult();
            if(guarda==null){
                return false;
            }else{
                return true;
            }         
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            sessao.close();
        } 
    }
}

package br.com.rocha.infracoes.dao;

import br.com.rocha.infracoes.modelo.Bairro;
import br.com.rocha.infracoes.util.HibernateUtil;
import java.io.Serializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author DaRocha
 */
public class BairroDao extends Generic_Dao<Bairro> implements Serializable{
    private Session sessao;   
    
    public boolean pesquisarCodigo(String nome){
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sessao.createCriteria(Bairro.class);
            criteria.add(Restrictions.ilike("nome", nome));
            Bairro bairro = (Bairro) criteria.uniqueResult();
            if(bairro==null){
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

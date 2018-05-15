package br.com.rocha.infracoes.dao;

import br.com.rocha.infracoes.modelo.Endereco;
import br.com.rocha.infracoes.util.HibernateUtil;
import java.io.Serializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author DaRocha
 */
public class EnderecoDao extends Generic_Dao<Endereco> implements Serializable{
    private Session sessao;   
    
    public boolean pesquisarCodigo(String cep){
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sessao.createCriteria(Endereco.class);
            criteria.add(Restrictions.ilike("cep", cep));
            Endereco endereco = (Endereco) criteria.uniqueResult();
            if(endereco==null){
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

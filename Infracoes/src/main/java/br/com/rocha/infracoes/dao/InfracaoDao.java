package br.com.rocha.infracoes.dao;

import br.com.rocha.infracoes.modelo.Infracao;
import br.com.rocha.infracoes.util.HibernateUtil;
import java.io.Serializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Tardeli
 */
public class InfracaoDao extends Generic_Dao<Infracao> implements Serializable {

    private Session sessao;   
    
    public boolean pesquisarCodigo(String Codigo){
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sessao.createCriteria(Infracao.class);
            criteria.add(Restrictions.ilike("codigo", Codigo));
            Infracao infracao = (Infracao) criteria.uniqueResult();
            if(infracao==null){
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

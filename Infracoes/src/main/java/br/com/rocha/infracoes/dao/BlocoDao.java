package br.com.rocha.infracoes.dao;

import br.com.rocha.infracoes.modelo.Bloco;
import br.com.rocha.infracoes.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author DaRocha
 */
public class BlocoDao extends Generic_Dao<Bloco>{
    
    private Session sessao;
    private Transaction transacao;

    public List<Bloco> listaBloco_semRepetir(){
        try {
            sessao = (Session) HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = sessao.createCriteria(Bloco.class);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            return criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            sessao.close();
        } 
    }
    
    
    public Boolean pesquisarCodigo(Long codigo){
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sessao.createCriteria(Bloco.class);
            criteria.add(Restrictions.ilike("numeroBloco", codigo));
            Bloco bloco = (Bloco) criteria.uniqueResult();
            if(bloco==null){
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

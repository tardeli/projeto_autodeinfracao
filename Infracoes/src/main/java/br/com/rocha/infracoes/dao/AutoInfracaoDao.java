package br.com.rocha.infracoes.dao;

import br.com.rocha.infracoes.modelo.AutoInfracao;
import br.com.rocha.infracoes.util.HibernateUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author DaRocha
 */
public class AutoInfracaoDao extends Generic_Dao<AutoInfracao> implements Serializable{
    private Session sessao;   
    
    public boolean pesquisarCodigo(String numeroAit){
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sessao.createCriteria(AutoInfracao.class);
            criteria.add(Restrictions.ilike("numeroAit", numeroAit));
            AutoInfracao autoInfracao = (AutoInfracao) criteria.uniqueResult();
            if(autoInfracao==null){
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

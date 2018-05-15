
package br.com.rocha.infracoes.converter;

import br.com.rocha.infracoes.dao.InfracaoDao;
import br.com.rocha.infracoes.modelo.Infracao;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;

/**
 *
 * @author Tardeli
 */
@FacesConverter("infracaoConverter")
public class InfracaoConverter implements Converter{

    //usando quando é clicado na caixa de seleção- Monta objeto apartir do ID selecionado
    @Override 
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Long codigo = Long.parseLong(value);
            InfracaoDao infracaoDao = new InfracaoDao();
            Infracao objeto = infracaoDao.buscarObjeto(codigo);
            return objeto;
        } catch (RuntimeException ex) {
            return null;
        }    
    }

    //recebe o objeto e retorna o Id em string
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            Infracao infracao = (Infracao) value;
            Long codigo = infracao.getId();
            return codigo.toString();
        } catch (RuntimeException ex) {
            return null;
        }
    }
    
}


package br.com.rocha.infracoes.converter;

import br.com.rocha.infracoes.dao.BairroDao;
import br.com.rocha.infracoes.modelo.Bairro;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;

/**
 *
 * @author Tardeli
 */
@FacesConverter("bairroConverter")
public class BairroConverter implements Converter{

    //usando quando é clicado na caixa de seleção- Monta objeto apartir do ID selecionado
    @Override 
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Long codigo = Long.parseLong(value);
            BairroDao bairroDao = new BairroDao();
            Bairro objeto = bairroDao.buscarObjeto(codigo);
            return objeto;
        } catch (RuntimeException ex) {
            return null;
        }    
    }

    //recebe o objeto e retorna o Id em string
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            Bairro bairro = (Bairro) value;
            Long codigo = bairro.getId();
            return codigo.toString();
        } catch (RuntimeException ex) {
            return null;
        }
    }
    
}

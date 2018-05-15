
package br.com.rocha.infracoes.converter;

import br.com.rocha.infracoes.dao.GuardaDao;
import br.com.rocha.infracoes.modelo.Guarda;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;

/**
 *
 * @author Tardeli
 */
@FacesConverter("guardaConverter")
public class GuardaConverter implements Converter{

    //usando quando é clicado na caixa de seleção- Monta objeto apartir do ID selecionado
    @Override 
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Long codigo = Long.parseLong(value);
            GuardaDao guardaDao = new GuardaDao();
            Guarda objeto = guardaDao.buscarObjeto(codigo);
            return objeto;
        } catch (RuntimeException ex) {
            return null;
        }    
    }

    //recebe o objeto e retorna o Id em string
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            Guarda guarda = (Guarda) value;
            Long codigo = guarda.getId();
            return codigo.toString();
        } catch (RuntimeException ex) {
            return null;
        }
    }
    
}


package br.com.rocha.infracoes.converter;

import br.com.rocha.infracoes.dao.EnderecoDao;
import br.com.rocha.infracoes.modelo.Endereco;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;

/**
 *
 * @author Tardeli
 */
@FacesConverter("enderecoConverter")
public class EnderecoConverter implements Converter{

    //usando quando é clicado na caixa de seleção- Monta objeto apartir do ID selecionado
    @Override 
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Long codigo = Long.parseLong(value);
            EnderecoDao enderecoDao = new EnderecoDao();
            Endereco objeto = enderecoDao.buscarObjeto(codigo);
            return objeto;
        } catch (RuntimeException ex) {
            return null;
        }    
    }

    //recebe o objeto e retorna o Id em string
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        try {
            Endereco endereco = (Endereco) value;
            Long codigo = endereco.getId();
            return codigo.toString();
        } catch (RuntimeException ex) {
            return null;
        }
    }
    
}

package br.com.rocha.infracoes.bean;

import br.com.rocha.infracoes.modelo.Login;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

/**
 *
 * @author Da Rocha
 */
@ManagedBean
@RequestScoped
public class LoginBean {

    private Login login;

    public LoginBean() {
    }

    public void limpar() {
        login = new Login();
    }
    
    public String validarLogin() {
        if (this.login.getUsuario().equals("admin") && this.login.getSenha().equals("admin")) {
            try {
                limpar();
                Messages.addGlobalInfo("Login efetuado com sucesso");
                //Faces.redirect("./index.xhtml");  
                return "index.xhtml";
            } catch (Exception ex) {
                //Messages.addGlobalError(ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            Messages.addGlobalInfo("Usuário ou senha incorretos!");
            limpar();
        }
        return "login.xhtml"; 
    }
    
    public Login getLogin() {
        if(login==null){
            return login = new Login();
        }
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    

}

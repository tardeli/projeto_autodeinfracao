package br.com.rocha.teste;

import br.com.rocha.infracoes.dao.GuardaDao;
import br.com.rocha.infracoes.modelo.Guarda;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.JDBCConnectionException;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author DaRocha
 */
public class GuardaTeste {

    GuardaDao guardaDao = new GuardaDao();
    Guarda guarda = new Guarda();
    
    @Test
    //@Ignore
    public void salvar() {

        guarda= new Guarda();
        guarda.setCodigo(48267);
        guarda.setNome("Rocha");
        
        
            if (this.guarda.getId() == null) {
                if (guardaDao.pesquisarCodigo(this.guarda.getCodigo())) {
                    System.out.println("Cadastro já existe");
                } else {
                    guardaDao.salvarOuAtualizarObjeto(this.guarda);
                    System.out.println("Salvo com sucesso");                   
                    this.guarda = new Guarda();
                }
            } else {
                guardaDao.salvarOuAtualizarObjeto(this.guarda);
                
                this.guarda = new Guarda();
            }
        
    }

 

}

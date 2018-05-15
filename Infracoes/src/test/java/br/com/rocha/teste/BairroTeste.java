package br.com.rocha.teste;

import br.com.rocha.infracoes.dao.BairroDao;
import br.com.rocha.infracoes.modelo.Bairro;
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
public class BairroTeste {

    BairroDao dao = new BairroDao();
    Bairro bairro = new Bairro();

    @Test
    @Ignore
    public void listar() {

        List<Bairro> lista = dao.listarObjetos();
        try {
            for (Bairro bairro : lista) {
                System.out.println(bairro.toString());
            }
        } catch (ExceptionInInitializerError ex) {
            System.out.println("Falha banco dados -->> " + ex.getCause());
        } catch (Exception ex) {
            System.out.println("Erro -->> " + ex.getCause());
        }
    }

    @Test
    @Ignore
    public void salvar() {
        bairro.setNome("Bela Vista54444444444444444444444444444444444444444444444444444444444444444444444444");
        try {
            dao.salvarOuAtualizarObjeto(bairro);
        } catch (ConstraintViolationException ex) {
            System.out.println("Já existe cadastro para esse registro -->> " + ex.getCause());
        } catch (ExceptionInInitializerError ex) {
            System.out.println("Falha banco dados -->> " + ex.getCause());
        } catch (Exception ex) {
            System.out.println("Erro -->> " + ex.getCause());
        }

    }

    @Test
    @Ignore
    public void gerarXml() {
        String caminho = "C:\\Users\\Da Rocha\\OneDrive\\Projeto Infracoes\\ArquivoXML\\infracoes.xml";

        List<Bairro> infracoes = dao.listarObjetos();

        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.autodetectAnnotations(true);
        xStream.alias("infracoes", List.class);

        String xml = xStream.toXML(infracoes);

        File arquivo = new File(caminho);
        try {
            PrintWriter printWriter = new PrintWriter(arquivo);
            printWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "\n");
            printWriter.write(xml);
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

package br.com.rocha.infracoes.bean;

import br.com.rocha.infracoes.dao.BairroDao;
import br.com.rocha.infracoes.modelo.Bairro;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.omnifaces.util.Messages;

/**
 *
 * @author Tardeli
 */
@SuppressWarnings("serial")
@ManagedBean
//@ViewScoped
@SessionScoped
public class BairroBean implements Serializable {

    private Bairro bairro = new Bairro();
    @SuppressWarnings("unused")
    private List<Bairro> listaObjetos;
    private BairroDao bairroDao = new BairroDao();

    public BairroBean() {
        this.bairro = new Bairro();
        listaObjetos = new ArrayList<>();
    }

    public void novo() {
        this.bairro = new Bairro();
    }

    public void salvar() {
        try {
            if (this.bairro.getId() == null) {
                if (bairroDao.pesquisarCodigo(this.bairro.getNome())) {
                    Messages.addGlobalInfo("Já existe cadastro para esse bairro!");
                } else {
                    bairroDao.salvarOuAtualizarObjeto(this.bairro);
                    getListaObjetos();
                    Messages.addGlobalInfo("Salvo com sucesso!");
                    this.bairro = new Bairro();
                }
            } else {
                bairroDao.salvarOuAtualizarObjeto(this.bairro);
                getListaObjetos();
                Messages.addGlobalInfo("Atualizado com sucesso!");
                this.bairro = new Bairro();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void gerarXMLInfracoes(){
        List<Bairro> bairros = bairroDao.listarObjetos();
        
        String caminho = "C:\\Users\\Da Rocha\\OneDrive\\Projeto Infracoes\\ArquivoXML\\bairros.xml";

        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.autodetectAnnotations(true);
        xStream.alias("bairros", List.class);

        String xml = xStream.toXML(bairros);

        File arquivo = new File(caminho);
        try {
            PrintWriter printWriter = new PrintWriter(arquivo);
            printWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+"\n");
            printWriter.write(xml);
            printWriter.flush();
            printWriter.close();
            Messages.addGlobalInfo("Gerado com sucesso!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Messages.addGlobalInfo("Erro relatório!");
        }
       
    }

    public boolean pesquisarCodigo() {

        return true;
    }

    public void excluir(Bairro c) {

    }

    public void carregarDadosEditar(Bairro c) {
        this.bairro = c;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    public BairroDao getClienteDao() {
        return bairroDao;
    }

    public void setClienteDao(BairroDao bairroDao) {
        this.bairroDao = bairroDao;
    }

    public List<Bairro> getListaObjetos() {
        return listaObjetos = bairroDao.listarObjetos();
    }

    public void setListaObjetos(List<Bairro> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }
}
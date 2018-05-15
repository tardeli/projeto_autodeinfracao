package br.com.rocha.infracoes.bean;

import br.com.rocha.infracoes.dao.InfracaoDao;
import br.com.rocha.infracoes.modelo.Infracao;
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
public class InfracaoBean implements Serializable {

    private Infracao infracao = new Infracao();
    @SuppressWarnings("unused")
    private List<Infracao> listaObjetos;
    private InfracaoDao infracaoDao = new InfracaoDao();

    
    public InfracaoBean() {
        this.infracao = new Infracao();
        listaObjetos = new ArrayList<>();
        getListaObjetos();
    }

    public void novo() {
        this.infracao = new Infracao();
    }

    public void salvar() {
        try {
            if (this.infracao.getId() == null) {
                if (infracaoDao.pesquisarCodigo(this.infracao.getCodigo())) {
                    Messages.addGlobalInfo("Já existe cadastro para esse código!");
                } else {
                    infracaoDao.salvarOuAtualizarObjeto(this.infracao);
                    getListaObjetos();
                    Messages.addGlobalInfo("Salvo com sucesso!");
                    this.infracao = new Infracao();
                }
            } else {
                infracaoDao.salvarOuAtualizarObjeto(this.infracao);
                getListaObjetos();
                Messages.addGlobalInfo("Atualizado com sucesso!");
                this.infracao = new Infracao();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void gerarXMLInfracoes(){
        List<Infracao> infracoes = infracaoDao.listarObjetos();
        
        String caminho = "C:\\Users\\Da Rocha\\OneDrive\\Projeto Infracoes\\ArquivoXML\\infracoes.xml";

        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.autodetectAnnotations(true);
        xStream.alias("infracoes", List.class);

        String xml = xStream.toXML(infracoes);

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

    public void excluir(Infracao c) {

    }

    public void carregarDadosEditar(Infracao c) {
        this.infracao = c;
    }

    public Infracao getInfracao() {
        return infracao;
    }

    public void setInfracao(Infracao infracao) {
        this.infracao = infracao;
    }

    public InfracaoDao getClienteDao() {
        return infracaoDao;
    }

    public void setClienteDao(InfracaoDao infracaoDao) {
        this.infracaoDao = infracaoDao;
    }

    public List<Infracao> getListaObjetos() {
        return listaObjetos = infracaoDao.listarObjetos();
    }

    public void setListaObjetos(List<Infracao> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }
}

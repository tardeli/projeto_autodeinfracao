package br.com.rocha.infracoes.bean;

import br.com.rocha.infracoes.dao.BairroDao;
import br.com.rocha.infracoes.dao.EnderecoDao;
import br.com.rocha.infracoes.modelo.Bairro;
import br.com.rocha.infracoes.modelo.Endereco;
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
import javax.faces.bean.ViewScoped;
import org.omnifaces.util.Messages;

/**
 *
 * @author Tardeli
 */
@SuppressWarnings("serial")
@ManagedBean
//@ViewScoped
@SessionScoped
public class EnderecoBean implements Serializable {

    private Endereco endereco = new Endereco();
    @SuppressWarnings("unused")
    private List<Endereco> listaObjetos;
    private EnderecoDao enderecoDao = new EnderecoDao();
    
    private List<Bairro> bairros = new ArrayList<>();
    private BairroDao bairroDao = new BairroDao();

    public EnderecoBean() {
        this.endereco = new Endereco();
        listaObjetos = new ArrayList<>();
    }

    public void novo() {
        this.endereco = new Endereco();
    }

    public void salvar() {
        try {
            if (this.endereco.getId() == null) {
                if (enderecoDao.pesquisarCodigo(this.endereco.getCep())) {
                    Messages.addGlobalInfo("Já existe cadastro para esse endereco!");
                } else {
                    enderecoDao.salvarOuAtualizarObjeto(this.endereco);
                    getListaObjetos();
                    Messages.addGlobalInfo("Salvo com sucesso!");
                    this.endereco = new Endereco();
                }
            } else {
                enderecoDao.salvarOuAtualizarObjeto(this.endereco);
                getListaObjetos();
                Messages.addGlobalInfo("Atualizado com sucesso!");
                this.endereco = new Endereco();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void gerarXMLInfracoes(){
        List<Endereco> enderecos = enderecoDao.listarObjetos();
        
        String caminho = "C:\\Users\\Da Rocha\\OneDrive\\Projeto Infracoes\\ArquivoXML\\enderecos.xml";

        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.autodetectAnnotations(true);
        xStream.alias("enderecos", List.class);

        String xml = xStream.toXML(enderecos);

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

    public void excluir(Endereco c) {

    }

    public void carregarDadosEditar(Endereco c) {
        this.endereco = c;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public EnderecoDao getClienteDao() {
        return enderecoDao;
    }

    public void setClienteDao(EnderecoDao enderecoDao) {
        this.enderecoDao = enderecoDao;
    }

    public List<Endereco> getListaObjetos() {
        return listaObjetos = enderecoDao.listarObjetos();
    }

    public void setListaObjetos(List<Endereco> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public List<Bairro> getBairros() {
        return bairros = bairroDao.listarObjetos();
    }

    public void setBairros(List<Bairro> bairros) {
        this.bairros = bairros;
    }
    
    

}

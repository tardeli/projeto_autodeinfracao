package br.com.rocha.infracoes.bean;

import br.com.rocha.infracoes.dao.GuardaDao;
import br.com.rocha.infracoes.modelo.Guarda;
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
public class GuardaBean implements Serializable {

    private Guarda guarda = new Guarda();
    @SuppressWarnings("unused")
    private List<Guarda> listaObjetos;
    private GuardaDao guardaDao = new GuardaDao();

    public GuardaBean() {
        this.guarda = new Guarda();
        listaObjetos = new ArrayList<>();
    }

    public void novo() {
        this.guarda = new Guarda();
    }

    public void salvar() {
        try {
            if (this.guarda.getId() == null) {
                if (guardaDao.pesquisarCodigo(this.guarda.getCodigo())) {
                    Messages.addGlobalInfo("Já existe cadastro para esse código!");
                } else {
                    guardaDao.salvarOuAtualizarObjeto(this.guarda);
                    getListaObjetos();
                    Messages.addGlobalInfo("Salvo com sucesso!");
                    this.guarda = new Guarda();
                }
            } else {
                guardaDao.salvarOuAtualizarObjeto(this.guarda);
                getListaObjetos();
                Messages.addGlobalInfo("Atualizado com sucesso!");
                this.guarda = new Guarda();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void gerarXMLInfracoes(){
        List<Guarda> guardas = guardaDao.listarObjetos();
        
        String caminho = "C:\\Users\\Da Rocha\\OneDrive\\Projeto Infracoes\\ArquivoXML\\guardas.xml";

        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.autodetectAnnotations(true);
        xStream.alias("guardas", List.class);

        String xml = xStream.toXML(guardas);

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

    public void excluir(Guarda c) {

    }

    public void carregarDadosEditar(Guarda c) {
        this.guarda = c;
    }

    public Guarda getGuarda() {
        return guarda;
    }

    public void setGuarda(Guarda guarda) {
        this.guarda = guarda;
    }

    public GuardaDao getClienteDao() {
        return guardaDao;
    }

    public void setClienteDao(GuardaDao guardaDao) {
        this.guardaDao = guardaDao;
    }

    public List<Guarda> getListaObjetos() {
        return listaObjetos = guardaDao.listarObjetos();
    }

    public void setListaObjetos(List<Guarda> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }
}

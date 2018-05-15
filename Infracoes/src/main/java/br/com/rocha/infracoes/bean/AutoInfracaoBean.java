package br.com.rocha.infracoes.bean;

import br.com.rocha.infracoes.dao.EnderecoDao;
import br.com.rocha.infracoes.dao.GuardaDao;
import br.com.rocha.infracoes.dao.InfracaoDao;
import br.com.rocha.infracoes.dao.AutoInfracaoDao;
import br.com.rocha.infracoes.enumeradores.StatusAIT;
import br.com.rocha.infracoes.modelo.Endereco;
import br.com.rocha.infracoes.modelo.Guarda;
import br.com.rocha.infracoes.modelo.Infracao;
import br.com.rocha.infracoes.modelo.AutoInfracao;
import br.com.rocha.infracoes.util.HibernateUtil;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Tardeli
 */
@SuppressWarnings("serial")
@ManagedBean(name="autoInfracaoBean")
//@ViewScoped
@SessionScoped
public class AutoInfracaoBean implements Serializable {

    private AutoInfracao autoInfracao = new AutoInfracao();
    private List<AutoInfracao> listaAits = new ArrayList<>();
    private AutoInfracaoDao autoInfracaoDao = new AutoInfracaoDao();

    private List<Endereco> enderecos = new ArrayList<>();
    private EnderecoDao enderecoDao = new EnderecoDao();

    private List<Infracao> infracoes = new ArrayList<>();
    private InfracaoDao infracaoDao = new InfracaoDao();

    private List<Guarda> guardas = new ArrayList<>();
    private GuardaDao guardaDao = new GuardaDao();

    private StatusAIT[] statusAIT;

    public StatusAIT[] getStatusAIT() {
        return StatusAIT.values();
    }

    public void setStatusAIT(StatusAIT[] statusAIT) {
        this.statusAIT = statusAIT;
    }

    public AutoInfracaoBean() {
        this.getListaAits();

    }

    public void novo() {
        this.autoInfracao = new AutoInfracao();
    }

    public void salvar() {

        
            autoInfracaoDao.salvarOuAtualizarObjeto(this.autoInfracao);
            getListaAits();
            Messages.addGlobalInfo("Salvo com sucesso!");
            this.autoInfracao = new AutoInfracao();
        

}

public void imprimir() throws ParseException {
        Map<String, Object> paramentros = new HashMap<>();

        DataTable tabela = (DataTable) Faces.getViewRoot().findComponent("form_autoInfracao:dataTable");
        Map<String, Object> filtros = tabela.getFilters();

        String filtroNome = (String) filtros.get("guarda");
        String filtroData = (String) filtros.get("data");

        if (filtroData == null) {
            Messages.addGlobalInfo("Selecione uma data!");
        } else {

            String[] separarData = filtroData.split("-");

            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date filtroData_Inicio = (Date) formatter.parse(separarData[0]);
            Date filtroData_Final = (Date) formatter.parse(separarData[1]);

            if (filtroData != null) {
                paramentros.put("PESQUISAR_DATA_I", filtroData_Inicio);
                paramentros.put("PESQUISAR_DATA_F", filtroData_Final);
            }

            if (filtroNome == null) {
                paramentros.put("PESQUISAR_GUARDA", "%%");
            } else {
                paramentros.put("PESQUISAR_GUARDA", "%" + filtroNome + "%");
            }

            Connection conexao = HibernateUtil.getConnection();
            String src = Faces.getRealPath("/Relatorio/autoInfracao.jasper");

            try {
                JasperPrint jasperPrint = JasperFillManager.fillReport(src, paramentros, conexao);
                //JasperViewer viewer = new JasperViewer(jasperPrint, true);
                //viewer.setVisible(true);

                JasperPrintManager.printReport(jasperPrint, true);
                //JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/Users/Tardeli/OneDrive/ProjetoWeb_Inicio/CasadasMarmitasMavem/src/main/webapp/relatorio/RelatorioClientes.pdf");

                //limpar 
                tabela = null;
                filtros = null;
                jasperPrint = null;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    
    public boolean filterByDate(Object value, Object filter, Locale locale) {

        String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null || filterText.isEmpty()) {
            return true;
        }
        if (value == null) {
            return false;
        }

        DateFormat df = SimpleDateFormat.getDateInstance(SimpleDateFormat.MEDIUM);

        Date filterDate = (Date) value;
        Date dateFrom;
        Date dateTo;
        try {
            String fromPart = filterText.substring(0, filterText.indexOf("-"));
            String toPart = filterText.substring(filterText.indexOf("-") + 1);
            dateFrom = fromPart.isEmpty() ? null : df.parse(fromPart);
            dateTo = toPart.isEmpty() ? null : df.parse(toPart);
        } catch (ParseException pe) {
            //LOGGER.log(Level.SEVERE, "unable to parse date: " + filterText, pe);
            return false;
        }

        return (dateFrom == null || filterDate.after(dateFrom)) && (dateTo == null || filterDate.before(dateTo));

    }


    public void gerarXMLInfracoes() {
        List<AutoInfracao> autoInfracaos = autoInfracaoDao.listarObjetos();

        String caminho = "C:\\Users\\Da Rocha\\OneDrive\\Projeto Infracoes\\ArquivoXML\\autoInfracaos.xml";

        XStream xStream = new XStream(new DomDriver("UTF-8"));
        xStream.autodetectAnnotations(true);
        xStream

.alias("autoInfracaos", List.class
);

        String xml = xStream.toXML(autoInfracaos);

        File arquivo = new File(caminho);
        try {
            PrintWriter printWriter = new PrintWriter(arquivo);
            printWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "\n");
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

    public void excluir(AutoInfracao c) {

    }

    public void carregarDadosEditar(AutoInfracao c) {
        this.autoInfracao = c;
    }

    public AutoInfracao getNotificacao() {
        return autoInfracao;
    }

    public void setNotificacao(AutoInfracao autoInfracao) {
        this.autoInfracao = autoInfracao;
    }

    public AutoInfracaoDao getClienteDao() {
        return autoInfracaoDao;
    }

    public void setClienteDao(AutoInfracaoDao autoInfracaoDao) {
        this.autoInfracaoDao = autoInfracaoDao;
    }

    public List<AutoInfracao> getListaAits() {
        return listaAits = autoInfracaoDao.listarObjetos();
    }

    public void setListaAits(List<AutoInfracao> listaAits) {
        this.listaAits = listaAits;
    }

    

    public List<Endereco> getEnderecos() {
        return enderecos = enderecoDao.listarObjetos();
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Infracao> getInfracoes() {
        return infracoes = infracaoDao.listarObjetos();
    }

    public void setInfracoes(List<Infracao> infracoes) {
        this.infracoes = infracoes;
    }

    public List<Guarda> getGuardas() {
        return guardas = guardaDao.listarObjetos();
    }

    public void setGuardas(List<Guarda> guardas) {
        this.guardas = guardas;
    }

    public AutoInfracao getAutoInfracao() {
        return autoInfracao;
    }

    public void setAutoInfracao(AutoInfracao autoInfracao) {
        this.autoInfracao = autoInfracao;
    }

    

}

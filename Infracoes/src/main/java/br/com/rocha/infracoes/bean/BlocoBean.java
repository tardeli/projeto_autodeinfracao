package br.com.rocha.infracoes.bean;

import br.com.rocha.infracoes.dao.BlocoDao;
import br.com.rocha.infracoes.dao.GuardaDao;
import br.com.rocha.infracoes.enumeradores.StatusBloco;
import br.com.rocha.infracoes.modelo.AutoInfracao;
import br.com.rocha.infracoes.modelo.Bloco;
import br.com.rocha.infracoes.modelo.Guarda;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.omnifaces.util.Messages;

/**
 *
 * @author Tardeli
 */
@SuppressWarnings("serial")
@ManagedBean
//@ViewScoped
@SessionScoped
public class BlocoBean implements Serializable {

    private Bloco bloco = new Bloco();
    @SuppressWarnings("unused")
    private List<Bloco> listaObjetos;
    private BlocoDao blocoDao = new BlocoDao();

    private List<Guarda> guardas = new ArrayList<>();
    private GuardaDao guardaDao = new GuardaDao();
    
    private Long totalAit;
    private Long ait_Inicio;
    private Long ait_Final;
    private String letra;

    private StatusBloco[] statusBloco;

    public StatusBloco[] getStatusBloco() {
        return StatusBloco.values();
    }

    public void setStatusBloco(StatusBloco[] statusBloco) {
        this.statusBloco = statusBloco;
    }

    public BlocoBean() {
        this.bloco = new Bloco();
        listaObjetos = new ArrayList<>();
    }

    public void novo() {
        this.bloco = new Bloco();
        totalAit = null;
    }

    public void processarCondigosAits(){
        ait_Inicio = Long.valueOf(this.bloco.getNumeroInicioBloco().replaceAll("[^0-9]", ""));
        ait_Final = Long.valueOf(this.bloco.getNumeroFinalBloco().replaceAll("[^0-9]", ""));
        letra = this.bloco.getNumeroFinalBloco().replaceAll("[^a-zA-Z]+", "");
    }
    
    public void salvar() {

        processarCondigosAits();
        
        try {
            if (this.bloco.getCodigo() == null) {
                if (blocoDao.pesquisarCodigo(this.bloco.getNumeroBloco())) {
                    Messages.addGlobalInfo("Já existe cadastro para esse código!");
                } else {
                    
                    for (int i = 0; i < totalAit+1; i++) {
                        AutoInfracao autoInfracao = new AutoInfracao();
                        autoInfracao.setBloco(bloco);
                        autoInfracao.setNumeroAit(ait_Inicio.toString() + letra);
                        this.bloco.getListaAits().add(autoInfracao);
                        ait_Inicio++;
                    }

                    blocoDao.salvarOuAtualizarObjeto(this.bloco);
                    getListaObjetos();
                    Messages.addGlobalInfo("Salvo com sucesso!");
                    novo();
                }
            } else {
               
                for (int i = 0; i < totalAit+1; i++) {

                    this.bloco.getListaAits().get(i).setNumeroAit(ait_Inicio.toString()+letra);
                    ait_Inicio++;
               
                }

                blocoDao.salvarOuAtualizarObjeto(this.bloco);
                getListaObjetos();
                Messages.addGlobalInfo("Atualizado com sucesso!");
                novo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void verificar(){
        processarCondigosAits();
        totalAit = (ait_Final-ait_Inicio);
        
        Messages.addGlobalInfo("Será cadastrado "+totalAit+" Aits");
        
    }
    

    public void imprimir() {

    }

    public boolean pesquisarCodigo() {

        return true;
    }

    public void excluir(Bloco c) {

    }

    public void carregarDadosEditar(Bloco c) {
        this.bloco = c;
        verificar();
    }

    public Bloco getBloco() {
        return bloco;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }

    public BlocoDao getClienteDao() {
        return blocoDao;
    }

    public void setClienteDao(BlocoDao blocoDao) {
        this.blocoDao = blocoDao;
    }

    public List<Bloco> getListaObjetos() {
        return listaObjetos = blocoDao.listaBloco_semRepetir();
    }

    public void setListaObjetos(List<Bloco> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public List<Guarda> getGuardas() {
        return guardas = guardaDao.listarObjetos();
    }

    public void setGuardas(List<Guarda> guardas) {
        this.guardas = guardas;
    }

    public Long getTotalAit() {
        return totalAit;
    }

    public void setTotalAit(Long totalAit) {
        this.totalAit = totalAit;
    }
    
    

}

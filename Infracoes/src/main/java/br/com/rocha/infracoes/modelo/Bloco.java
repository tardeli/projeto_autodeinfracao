
package br.com.rocha.infracoes.modelo;

import br.com.rocha.infracoes.enumeradores.StatusBloco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DaRocha
 */
@Entity
@Table(name = "Bloco")
public class Bloco implements Serializable{
    
    public Bloco() {
       this.setStatusBloco(statusBloco.Ativo);
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;
    
    @Column(unique = true)
    private Long numeroBloco;
    
    @Column(length = 15)
    private String numeroInicioBloco;
    
    @Column(length = 15)
    private String numeroFinalBloco;
    
    @ManyToOne
    @JoinColumn(name = "guarda")
    private Guarda guarda;
    
    @Temporal(TemporalType.DATE)
    private Date dataCadastro;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private StatusBloco statusBloco;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "bloco")
    private List<AutoInfracao> listaAits = new ArrayList<>();

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getNumeroBloco() {
        return numeroBloco;
    }

    public void setNumeroBloco(Long numeroBloco) {
        this.numeroBloco = numeroBloco;
    }

    public String getNumeroInicioBloco() {
        return numeroInicioBloco;
    }

    public void setNumeroInicioBloco(String numeroInicioBloco) {
        this.numeroInicioBloco = numeroInicioBloco;
    }

    public String getNumeroFinalBloco() {
        return numeroFinalBloco;
    }

    public void setNumeroFinalBloco(String numeroFinalBloco) {
        this.numeroFinalBloco = numeroFinalBloco;
    }

    public Guarda getGuarda() {
        return guarda;
    }

    public void setGuarda(Guarda guarda) {
        this.guarda = guarda;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public StatusBloco getStatusBloco() {
        return statusBloco;
    }

    public void setStatusBloco(StatusBloco statusBloco) {
        this.statusBloco = statusBloco;
    }

    

    public List<AutoInfracao> getListaAits() {
        if(listaAits==null){
            return listaAits = new  ArrayList<>();
        }
        return listaAits;
    }

    public void setListaAits(List<AutoInfracao> listaAits) {
        this.listaAits = listaAits;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bloco other = (Bloco) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bloco{" + "codigo=" + codigo + ", numeroBloco=" + numeroBloco + ", numeroInicioBloco=" + numeroInicioBloco + ", numeroFinalBloco=" + numeroFinalBloco + ", guarda=" + guarda + ", dataCadastro=" + dataCadastro + ", statusBloco=" + statusBloco + ", listaAits=" + listaAits + '}';
    }
    
}
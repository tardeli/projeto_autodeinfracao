package br.com.rocha.infracoes.modelo;

import br.com.rocha.infracoes.enumeradores.StatusAIT;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author DaRocha
 */
@Entity
@Table(name = "AutoInfracao")
@XStreamAlias(value = "AutoInfracao")
public class AutoInfracao implements Serializable{
   
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XStreamAlias(value = "id")
    @XStreamAsAttribute
    private Long id;
    @Column(length = 12)
    private String numeroAit;
    
    @ManyToOne
    @JoinColumn(nullable = true) 
    private Bloco bloco;
    
    @Column(length = 8)
    private String placa;
    @ManyToOne
    @JoinColumn(name = "endereco")
    @ForeignKey(name = "fk_endereco_notificacao")
    private Endereco endereco;
    @Column(length = 20)
    private String numeroReferencia;
    @Temporal(TemporalType.DATE)
    private Date data;
    @Temporal(TemporalType.TIME)
    private Date hora;
    @ManyToOne
    @JoinColumn(name = "infracao")
    @ForeignKey(name = "fk_infracao_notificacao")
    private Infracao infracao;
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private StatusAIT statusAIT;
    @ManyToOne
    @JoinColumn(name = "guardarecebedor")
    private Guarda guardaRecebedor;
    @Temporal(TemporalType.DATE)
    private Date dataEntrega;
    
    public AutoInfracao() {
        this.setStatusAIT(statusAIT.Ativo);
        this.dataEntrega = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroAit() {
        return numeroAit;
    }

    public void setNumeroAit(String numeroAit) {
        this.numeroAit = numeroAit;
    }

    public Bloco getBloco() {
        return bloco;
    }

    public void setBloco(Bloco bloco) {
        this.bloco = bloco;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Infracao getInfracao() {
        return infracao;
    }

    public void setInfracao(Infracao infracao) {
        this.infracao = infracao;
    }

    public StatusAIT getStatusAIT() {
        return statusAIT;
    }

    public void setStatusAIT(StatusAIT statusAIT) {
        this.statusAIT = statusAIT;
    }

    public Guarda getGuardaRecebedor() {
        return guardaRecebedor;
    }

    public void setGuardaRecebedor(Guarda guardaRecebedor) {
        this.guardaRecebedor = guardaRecebedor;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final AutoInfracao other = (AutoInfracao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AutoInfracao{" + "id=" + id + ", numeroAit=" + numeroAit + ", bloco=" + bloco + ", placa=" + placa + ", endereco=" + endereco + ", numeroReferencia=" + numeroReferencia + ", data=" + data + ", hora=" + hora + ", infracao=" + infracao + ", statusAIT=" + statusAIT + ", guardaRecebedor=" + guardaRecebedor + ", dataEntrega=" + dataEntrega + '}';
    }

    
   
    
    

}
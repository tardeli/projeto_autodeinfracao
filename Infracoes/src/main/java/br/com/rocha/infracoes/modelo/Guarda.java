package br.com.rocha.infracoes.modelo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Tardeli
 */
@Entity
@Table(name = "Guarda")
@XStreamAlias(value = "Guarda")
public class Guarda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@XStreamAsAttribute //coloca o id dentro da tag infracao
    private Long id;
    
    @Column(length = 6, unique = true)
    private Integer codigo;
    @Column(length = 20)
    private String nome;
    
    public Guarda() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Guarda other = (Guarda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Guarda{" + "id=" + id + ", codigo=" + codigo + ", nome=" + nome + '}';
    }

       
}

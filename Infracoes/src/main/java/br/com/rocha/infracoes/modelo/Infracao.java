package br.com.rocha.infracoes.modelo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;
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
@Table(name = "Infracao")
@XStreamAlias(value = "Infracao")
public class Infracao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@XStreamAsAttribute //coloca o id dentro da tag infracao
    private Long id;
    
    @Column(length = 8, unique = true)
    private String codigo;
    @Column(length = 100)
    private String descricao;
    @Column(length = 20)
    private String amparo;
    @Column(length = 20)
    private String infrator;
    @Column(length = 20)
    private String gravidade;
    @Column(length = 20)
    private String orgao;
    
    public Infracao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAmparo() {
        return amparo;
    }

    public void setAmparo(String amparo) {
        this.amparo = amparo;
    }

    public String getInfrator() {
        return infrator;
    }

    public void setInfrator(String infrator) {
        this.infrator = infrator;
    }

    public String getGravidade() {
        return gravidade;
    }

    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    public String getOrgao() {
        return orgao;
    }

    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }
   
    public boolean gerarArquivo_XML(List<Infracao> infracoes) {
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
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Infracao other = (Infracao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Infracao{" + "id=" + id + ", codigo=" + codigo + ", descricao=" + descricao + ", amparo=" + amparo + ", infrator=" + infrator + ", gravidade=" + gravidade + ", orgao=" + orgao + '}';
    }

       
}

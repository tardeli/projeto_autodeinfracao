package br.com.rocha.infracoes.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class ProgressBarBean implements Serializable {

    //valor entre 0 a 100 que sera usado no progressbar
    private Integer progresso;
    //quantidade de notas que o usuario informou
    private Integer sizeNum;

    /**
     * valores padrao de quantidade e notas
     */
    @PostConstruct
    public void init() {
        sizeNum = 50;
    }

    //reseta o progresso
    private void resetarProgresso() {
        progresso = 0;
    }

    /**
     * atualiza o progresso
     */
    private void atualizarProgresso(int i) {
        //calculo para o percentual do processo em relacao ao tempo
        progresso = (i * 100) / sizeNum;
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processar() {
        try {
            resetarProgresso();
            for (int i = 0; i <= sizeNum; i++) {
                atualizarProgresso(i);
            }
            resetarProgresso();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getProgresso() {
        if (progresso == null) {
            progresso = 0;
        }
        return progresso;
    }

    public void setProgresso(Integer progresso) {
        this.progresso = progresso;
    }

    public Integer getSizeNum() {
        return sizeNum;
    }

    public void setSizeNum(Integer sizeNum) {
        this.sizeNum = sizeNum;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rocha.teste;

import br.com.rocha.infracoes.dao.InfracaoDao;
import br.com.rocha.infracoes.modelo.Infracao;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.omnifaces.util.Messages;

/**
 *
 * @author DaRocha
 */
public class InfracaoTeste {

    InfracaoDao dao = new InfracaoDao();
    Infracao infracao = new Infracao();

    @Test
    //@Ignore
    public void listar() {
        List<Infracao> lista = dao.listarObjetos();
        for (Infracao infracao : lista) {
            System.out.println(infracao.toString());
        }
    }

    @Test
    @Ignore
    public void gerarXml() {
        String caminho = "C:\\Users\\Da Rocha\\OneDrive\\Projeto Infracoes\\ArquivoXML\\infracoes.xml";

        List<Infracao> infracoes = dao.listarObjetos();

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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rocha.teste;

import br.com.rocha.infracoes.dao.EnderecoDao;
import br.com.rocha.infracoes.modelo.Endereco;
import com.google.gson.Gson;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author DaRocha
 */
public class EnderecoTeste {
    
    @Test
    @Ignore
    public void pesquisar(){
        EnderecoDao dao = new EnderecoDao();
        boolean resultado = dao.pesquisarCodigo("11111-111");
        System.out.println("resultado = "+resultado);
    
    }
    
    @Test
    //@Ignore
    public void json(){
        EnderecoDao dao = new EnderecoDao();
        Endereco endereco = dao.buscarObjeto(2L);
        
        Gson gson = new Gson();
                      
        System.out.println(gson.toJson(endereco));
    
    }
}

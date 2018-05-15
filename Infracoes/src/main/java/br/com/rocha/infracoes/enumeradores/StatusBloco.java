/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rocha.infracoes.enumeradores;

/**
 *
 * @author Tardeli
 */
public enum StatusBloco {
    Cancelado("Cancelado"),
    Ativo("Ativo"),
    Substituido("Substituido"),
    Extraviado("Extraviado"),
    Finalizado("Finalizado");
    
    private final String nome;

    private StatusBloco(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

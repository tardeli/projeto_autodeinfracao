package br.com.rocha.infracoes.enumeradores;

/**
 *
 * @author Tardeli
 */
public enum StatusAIT {
    Cancelado("Cancelado"),
    Ativo("Ativo"),
    Finalizado("Finalizado"),
    Substituido("Substituido"),
    Extraviado("Extraviado");
    
    private final String nome;

    private StatusAIT(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

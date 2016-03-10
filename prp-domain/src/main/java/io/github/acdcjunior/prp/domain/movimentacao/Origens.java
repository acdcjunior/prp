package io.github.acdcjunior.prp.domain.movimentacao;

public enum Origens {

    CONTA (1), FATURA (2);

    public final int id;

    private Origens(int id) {
        this.id = id;
    }

}

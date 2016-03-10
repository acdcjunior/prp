package io.github.acdcjunior.prp.domain.mescontabil;

import org.hibernate.annotations.Immutable;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Immutable
public class AnoMes implements Serializable {

    private int ano;
    private int mes;

    public int ano() {
        return ano;
    }

    public int mes() {
        return mes;
    }

    protected AnoMes() { }

    public AnoMes(int ano, int mes) {
        this.ano = ano;
        this.mes = mes;
    }

    @Override
    public String toString() {
        return ano + "-" + (mes < 10 ? "0" : "") + mes;
    }

}
package io.github.acdcjunior.prp.legacy.domain.mescontabil;

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

    @Override
    @SuppressWarnings({"all", "gerado pelo IntelliJ"})
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnoMes anoMes = (AnoMes) o;

        if (ano != anoMes.ano) return false;
        return mes == anoMes.mes;
    }

    @Override
    @SuppressWarnings({"all", "gerado pelo IntelliJ"})
    public int hashCode() {
        int result = ano;
        result = 31 * result + mes;
        return result;
    }

}
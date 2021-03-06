package io.github.acdcjunior.prp.legacy.domain.mescontabil;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Representa um Mês, no sentido contábil.
 * <p/>
 * Cada mês contábil terá N movimentações para cada origem e N previsões.
 */
@Entity
@Table(name = "tb_mescontabil")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MesContabil {

    @EmbeddedId
    private AnoMes anoMes;

    @Column(name = "contacarregada")
    private boolean contaCarregada;

    @Column(name = "faturacarregada")
    private boolean faturaCarregada;

    @Column(name = "mesconcluido")
    private boolean mesConcluido;

    @Column
    private String comentario;

    protected MesContabil() { }

    public MesContabil(AnoMes anoMes) {
        this.anoMes = anoMes;
    }

    public AnoMes getAnoMes() {
        return anoMes;
    }

    public boolean isContaCarregada() {
        return contaCarregada;
    }

    public boolean isFaturaCarregada() {
        return faturaCarregada;
    }

    public boolean isMesConcluido() {
        return mesConcluido;
    }

    public String getComentario() {
        return comentario;
    }

}
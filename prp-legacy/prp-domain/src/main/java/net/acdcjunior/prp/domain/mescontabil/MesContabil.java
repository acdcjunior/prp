package net.acdcjunior.prp.domain.mescontabil;

import net.acdcjunior.prp.domain.BaseEntity;
import net.acdcjunior.prp.domain.movimentacao.Origem;
import net.acdcjunior.prp.domain.previsao.Previsao;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Representa um Mês, no sentido contábil.
 * <p/>
 * Cada mês contábil terá N movimentações para cada origem e N previsões.
 */
@Entity
@Table(name = "TB_MESCONTABIL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MesContabil {

    @EmbeddedId
    private AnoMes anoMes;

    @Column
    private boolean contaCarregada;

    @Column
    private boolean faturaCarregada;

    @Column
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
package net.acdcjunior.prp.application.mescontabil;

import net.acdcjunior.prp.domain.mescontabil.AnoMes;
import net.acdcjunior.prp.domain.mescontabil.MesContabil;
import net.acdcjunior.prp.domain.mescontabil.MesContabilRepository;
import net.acdcjunior.prp.domain.movimentacao.Movimentacao;
import net.acdcjunior.prp.domain.movimentacao.MovimentacaoRepository;
import net.acdcjunior.prp.domain.movimentacao.Origens;
import net.acdcjunior.prp.domain.previsao.PrevisaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class MesContabilService {

    @Autowired
    private MesContabilRepository mesContabilRepository;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private PrevisaoRepository previsaoRepository;


    @Transactional(readOnly = true)
    public List<MesContabilDto> all() {

        List<Movimentacao> movimentacoes = movimentacaoRepository.findAll();
        LocalDate minData = movimentacoes.stream().min(Comparator.comparing(Movimentacao::getData)).map(Movimentacao::getData).get();

        LocalDate dataMes = minData.withDayOfMonth(1);
        LocalDate hoje = LocalDate.now().plusMonths(1);

        List<MesContabilDto> meses = new LinkedList<>();
        while(hoje.isAfter(dataMes)) {
            int ano = dataMes.getYear();
            int mes = dataMes.getMonthValue();
            MesContabil mesContabil = mesContabilRepository.find(ano, mes);
            meses.add(go(ano, mes, mesContabil));
            dataMes = dataMes.plusMonths(1);
        }
        return meses;
    }

    @Transactional(readOnly = true)
    public MesContabilDto mes(int ano, int mes) {
        MesContabil mesContabil = mesContabilRepository.find(ano, mes);
        return go(ano, mes, mesContabil);
    }

    private MesContabilDto go(int ano, int mes, MesContabil mesContabil) {
        MesContabilDto mesContabilDto = new MesContabilDto();

        if (mesContabil != null) {
            mesContabilDto.anoMes = mesContabil.getAnoMes().toString();
            mesContabilDto.contaCarregada = mesContabil.isContaCarregada();
            mesContabilDto.faturaCarregada = mesContabil.isFaturaCarregada();
            mesContabilDto.mesConcluido = mesContabil.isMesConcluido();
            mesContabilDto.comentario = mesContabil.getComentario();
        } else {
            mesContabilDto.anoMes = new AnoMes(ano, mes).toString();
            mesContabilDto.contaCarregada = false;
            mesContabilDto.faturaCarregada = false;
            mesContabilDto.mesConcluido = false;
            mesContabilDto.comentario = "[MES GERADO]";
        }

        mesContabilDto.qtdPrevisoes = previsaoRepository.findByAnoMes(ano, mes).size();

        List<Movimentacao> movimentacoes = movimentacaoRepository.findByAnoMes(ano, mes);
        mesContabilDto.qtdMovimentacoesConta = movimentacoes.stream().filter(m -> m.getOrigem().getId() == Origens.CONTA.id).count();
        mesContabilDto.qtdMovimentacoesContaComPrevisao = movimentacoes.stream().filter(m -> m.getOrigem().getId() == Origens.CONTA.id && m.getRealiza() != null).count();

        mesContabilDto.qtdMovimentacoesFatura = movimentacoes.stream().filter(m -> m.getOrigem().getId() == Origens.FATURA.id).count();
        mesContabilDto.qtdMovimentacoesFaturaComPrevisao = movimentacoes.stream().filter(m -> m.getOrigem().getId() == Origens.FATURA.id && m.getRealiza() != null).count();

        return mesContabilDto;
    }

}
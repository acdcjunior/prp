package net.acdcjunior.prp.domain.movimentacao;

import org.springframework.stereotype.Component;


@Component
public class MovimentacaoFactory {

	public Movimentacao criar(Movimentacao movimentacaoDados, Movimentacao movimentacaoBase) {
		Movimentacao movimentacaoCriada = new Movimentacao();
		movimentacaoCriada.setData(movimentacaoDados.getData());
		movimentacaoCriada.setNumeroDocumento(movimentacaoDados.getNumeroDocumento());
		movimentacaoCriada.setDescricao1(movimentacaoDados.getDescricao1());
		movimentacaoCriada.setDescricao2(movimentacaoDados.getDescricao2());
		movimentacaoCriada.setValor(movimentacaoDados.getValor());
		movimentacaoCriada.setRealiza(movimentacaoDados.getRealiza());
		
		movimentacaoCriada.setAnterior(movimentacaoBase);
		movimentacaoCriada.setOrigem(movimentacaoBase.getOrigem());
		movimentacaoCriada.setSaldo(movimentacaoBase.getSaldo().add(movimentacaoDados.getValor()));
		return movimentacaoCriada;
	}

}
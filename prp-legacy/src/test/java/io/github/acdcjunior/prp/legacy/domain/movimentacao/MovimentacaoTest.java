package io.github.acdcjunior.prp.legacy.domain.movimentacao;

import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MovimentacaoTest {

	@Test
	public void isNotInMonth__deve_trazer_false_se_for_o_mesmo_mes() {
		// given
		Movimentacao m = new Movimentacao();
		m.setData(LocalDate.of(2014, 6, 15));
		// when
		boolean notInMonth = m.isNotInMonth(2014, 6);
		// then
		assertThat(notInMonth, is(equalTo(false)));
	}
	
	@Test
	public void isNotInMonth__deve_trazer_true_se_nao_for_o_mesmo_mes() {
		// given
		Movimentacao m = new Movimentacao();
        m.setData(LocalDate.of(2014, 6, 15));
		// when
		boolean notInMonth = m.isNotInMonth(2014, 7);
		// then
		assertThat(notInMonth, is(equalTo(true)));
	}

}
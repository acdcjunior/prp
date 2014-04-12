package net.acdcjunior.prp.domain.movimentacao;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.joda.time.DateTime;
import org.junit.Test;

public class MovimentacaoTest {

	@Test
	public void isNotInMonth__deve_trazer_false_se_for_o_mesmo_mes() {
		// given
		Movimentacao m = new Movimentacao();
		m.setData(new DateTime(2014, 6, 15, 0, 0).toDate());
		// when
		boolean notInMonth = m.isNotInMonth(2014, 6);
		// then
		assertThat(notInMonth, is(equalTo(false)));
	}
	
	@Test
	public void isNotInMonth__deve_trazer_true_se_nao_for_o_mesmo_mes() {
		// given
		Movimentacao m = new Movimentacao();
		m.setData(new DateTime(2014, 6, 15, 0, 0).toDate());
		// when
		boolean notInMonth = m.isNotInMonth(2014, 7);
		// then
		assertThat(notInMonth, is(equalTo(true)));
	}

}
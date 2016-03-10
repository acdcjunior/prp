package io.github.acdcjunior.prp.controller;

import io.github.acdcjunior.prp.test.ControllerIntegrationTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore
public class OrigemControllerTest extends ControllerIntegrationTest {
	
	@Test
	public void criarMovimentacoes() throws Exception {
		String json = "[{\"id\":327,\"data\":\"2012-01-29\",\"numeroDocumento\":\"208855\",\"descricao1\":\"MOCK\",\"descricao2\":null,\"descricao3\":null,"
				+ "\"valor\":112.06,\"origem\":{\"id\":1,\"nome\":\"Conta-Corrente BANCO\",\"alias\":\"contacorrente\"},\"saldo\":22499.17,\"realiza\":null,"
				+ "\"anteriorId\":326,\"_readOnly\":true,\"saldoCalculado\":\"22499.17\"},"
				+ "{\"saldoCalculado\":\"22832.06\",\"data\":\"2012-02-01\",\"numeroDocumento\":\"332\",\"descricao1\":\"332d1\",\"descricao2\":\"332d2\",\"valor\":332.89}]";
		buildMockMvc().perform(post("/origem/1").contentType(MediaType.APPLICATION_JSON).content(json))
		.andDo(print())
		.andExpect(status().isCreated());
	}
	
}
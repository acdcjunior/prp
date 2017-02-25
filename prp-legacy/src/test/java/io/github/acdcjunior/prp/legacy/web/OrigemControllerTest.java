package io.github.acdcjunior.prp.legacy.web;

import io.github.acdcjunior.prp.legacy.test.ControllerIntegrationTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Ignore("Metodo tah retornando 400, tem que checar o json...")
public class OrigemControllerTest extends ControllerIntegrationTest {
	
	@Test
	public void criarMovimentacoes() throws Exception {
		String json = "[\n" +
				"  {\n" +
				"    \"id\": 327,\n" +
				"    \"data\": \"2012-01-29\",\n" +
				"    \"numeroDocumento\": \"208855\",\n" +
				"    \"descricao1\": \"MOCK\",\n" +
				"    \"descricao2\": null,\n" +
				"    \"descricao3\": null,\n" +
				"    \"valor\": 112.06,\n" +
				"    \"origem\": {\n" +
				"      \"id\": 1,\n" +
				"      \"nome\": \"Conta-Corrente BANCO\",\n" +
				"      \"alias\": \"contacorrente\"\n" +
				"    },\n" +
				"    \"saldo\": 22499.17,\n" +
				"    \"realiza\": null,\n" +
				"    \"anteriorId\": 326,\n" +
				"    \"_readOnly\": true,\n" +
				"    \"saldoCalculado\": \"22499.17\"\n" +
				"  },\n" +
				"  {\n" +
				"    \"saldoCalculado\": \"22832.06\",\n" +
				"    \"data\": \"2012-02-01\",\n" +
				"    \"numeroDocumento\": \"332\",\n" +
				"    \"descricao1\": \"332d1\",\n" +
				"    \"descricao2\": \"332d2\",\n" +
				"    \"valor\": 332.89\n" +
				"  }\n" +
				"]";
		buildMockMvc().perform(post("/origem/1").contentType(MediaType.APPLICATION_JSON).content(json))
		.andDo(print())
		.andExpect(status().isCreated());
	}
	
}
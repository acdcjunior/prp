package io.github.acdcjunior.prp.web;

import io.github.acdcjunior.prp.test.ControllerIntegrationTest;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Ignore
public class MovimentacaoControllerTest extends ControllerIntegrationTest {
	
	@Test
	public void movimentacoes__deve_trazer_200_ok() throws Exception {
		buildMockMvc().perform(get("/movimentacao"))
                    .andDo(print())
                	.andExpect(status().isOk())
                	.andExpect(view().name("movimentacao/list"))
                	.andExpect(model().attributeExists("movimentacoes"));
	}
	
	@Test
	public void update__deve_salvar_mov_e_trazer_200_ok() throws Exception {
		buildMockMvc().perform(put("/movimentacao/10").contentType(MediaType.APPLICATION_JSON).content("{\"id\": 10, \"descricao1\": \"bozo\"}"))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	public void update__deve_trazer_400_se_id_diferir() throws Exception {
		buildMockMvc().perform(put("/movimentacao/10").contentType(MediaType.APPLICATION_JSON).content("{\"id\": 999}"))
		.andDo(print())
		.andExpect(status().isBadRequest());
	}
	
	@Test
	public void limitrofes() throws Exception {
		buildMockMvc().perform(get("/movimentacao/limitrofes/2011/2").accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}

}
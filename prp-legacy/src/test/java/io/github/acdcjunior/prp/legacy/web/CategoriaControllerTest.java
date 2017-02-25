package io.github.acdcjunior.prp.legacy.web;

import io.github.acdcjunior.prp.legacy.test.ControllerIntegrationTest;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CategoriaControllerTest extends ControllerIntegrationTest {
	
	@Test
	public void id() throws Exception {
		buildMockMvc().perform(get("/categoria/1"))
                    .andDo(print())
                	.andExpect(status().isOk())
                	.andExpect(view().name("categoria/id"))
                	.andExpect(model().attributeExists("categoria"));
	}
	
	@Test
	public void all() throws Exception {
		buildMockMvc().perform(get("/categoria"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(view().name("categoria/all"))
					.andExpect(model().attributeExists("categorias"));
	}

}
package io.github.acdcjunior.prp.controller;

import io.github.acdcjunior.prp.test.ControllerIntegrationTest;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Ignore
public class UsuarioControllerTest extends ControllerIntegrationTest {
	
	@Test
	public void go() {
	}
	
	@Test
	public void listarUsuarios__deve_trazer_200_ok() throws Exception {
		buildMockMvc().perform(get("/usuarios"))
                    .andDo(print())
                	.andExpect(status().isOk())
                	.andExpect(view().name("usuarios"))
                	.andExpect(forwardedUrl("/WEB-INF/layout/layout.jsp"));
	}

	@Test
	public void novoUsuario__deve_trazer_200_ok() throws Exception {
		buildMockMvc().perform(get("/usuarios/novo"))
        			.andExpect(status().isOk())
                	.andExpect(view().name("usuarios-novo"))
                	.andExpect(forwardedUrl("/WEB-INF/layout/layout.jsp"));
	}

	@Test
	public void criarUsuario__deve_trazer_fazer_redirect() throws Exception {
		buildMockMvc().perform(post("/usuarios").param("nome", "bob"))
        			.andDo(print())
        			.andExpect(status().isMovedTemporarily())
        			.andExpect(redirectedUrl("/usuarios"));
	}

	@Test
	public void variavelString__deve_trazer_200_ok() throws Exception {
		buildMockMvc().perform(get("/usuarios-string"))
			          .andDo(print())
			    	  .andExpect(status().isOk())
			    	  .andExpect(view().name("usuarios-string"))
			    	  .andExpect(model().attributeExists("variavelString"))
			    	  .andExpect(forwardedUrl("/WEB-INF/layout/layout.jsp"));
	}

	@Test
	public void variavelMap__deve_trazer_200_ok() throws Exception {
		buildMockMvc().perform(get("/usuarios-map"))
			          .andDo(print())
			    	  .andExpect(status().isOk())
			    	  .andExpect(view().name("usuarios-map"))
			    	  .andExpect(model().attribute("variavelMap", hasKey("um")))
			    	  .andExpect(model().attribute("variavelMap", hasKey("dois")))
			    	  .andExpect(forwardedUrl("/WEB-INF/layout/layout.jsp"));
	}

}
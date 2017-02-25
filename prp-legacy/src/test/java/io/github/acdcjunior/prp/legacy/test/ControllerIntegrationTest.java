package io.github.acdcjunior.prp.legacy.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class ControllerIntegrationTest {
	
    @Autowired
    private WebApplicationContext wac;
    
    protected MockMvc buildMockMvc() {
		return MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

}
package io.github.acdcjunior.prp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest(randomPort = true)
@DirtiesContext
public class VelocityTest {

	@Value("${local.server.port}")
	private int port;

	@Test
	public void testVelocityTemplate() throws Exception {
		ResponseEntity<String> entity = new TestRestTemplate()
				.getForEntity("http://localhost:" + this.port + "/velocity", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertTrue("Wrong body:\n" + entity.getBody(),
				entity.getBody().contains("Hello, Bob"));
	}

	@Test
	public void testVelocityErrorTemplate() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);

		ResponseEntity<String> responseEntity = new TestRestTemplate().exchange(
				"http://localhost:" + this.port + "/does-not-exist", HttpMethod.GET,
				requestEntity, String.class);

		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		assertTrue("Wrong body:\n" + responseEntity.getBody(),
				responseEntity.getBody().contains("Something went wrong: 404 Not Found"));
	}

}
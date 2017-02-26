package io.github.acdcjunior.prp.groovy

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.DefaultResponseErrorHandler
import org.springframework.web.client.RestTemplate

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

import static org.junit.Assert.assertEquals

class PrpGroovyApplicationTests {

	private static ConfigurableApplicationContext context

	@BeforeClass
	static void start() throws Exception {
		Future<ConfigurableApplicationContext> future = Executors
				.newSingleThreadExecutor().submit(
						new Callable<ConfigurableApplicationContext>() {
							@Override
							ConfigurableApplicationContext call() throws Exception {
								return SpringApplication
										.run(PrpGroovyApplication.class)
							}
						})
		context = future.get(60, TimeUnit.SECONDS)
	}

	@AfterClass
	static void stop() {
		if (context != null) {
			context.close()
		}
	}

	@Test
	void testHome() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = getRestTemplate().getForEntity(
				"http://localhost:8080", Map.class)
		assertEquals(HttpStatus.OK, entity.getStatusCode())
		@SuppressWarnings("unchecked")
		Map<String, Object> body = entity.getBody()
		assertEquals("Hello World", body.get("message"))
	}

	@Test
	void testErrorPageDirectAccess() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = getRestTemplate().getForEntity(
				"http://localhost:8080/error", Map.class)
		assertEquals(HttpStatus.OK, entity.getStatusCode())
		@SuppressWarnings("unchecked")
		Map<String, Object> body = entity.getBody()
		assertEquals("None", body.get("error"))
		assertEquals(999, body.get("status"))
	}

	private String getPassword() {
		return context.getBean(SecurityProperties.class).getUser().getPassword()
	}

	private RestTemplate getRestTemplate() {

		RestTemplate restTemplate = new RestTemplate()
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			@Override
			void handleError(ClientHttpResponse response) throws IOException {
			}
		})
		restTemplate

	}

}

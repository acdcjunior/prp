package io.github.acdcjunior.prp.legacy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Map;

@Controller
public class VelocityController {

	@Value("${application.message:Hello World}")
	private String message = "Hello World";

	@RequestMapping("/velocity")
	public String welcome(Map<String, Object> model) {
		model.put("time", new Date());
		model.put("message", this.message);
		return "welcome";
	}

}
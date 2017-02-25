package io.github.acdcjunior.prp.extratos.web;

import io.github.acdcjunior.prp.extratos.domain.Origem;
import io.github.acdcjunior.prp.extratos.domain.OrigemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrigemController {

	@Autowired
	private OrigemRepository origemRepository;

	@GetMapping("/origens")
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Origem> helloWorld() {
		return this.origemRepository.findAll();
	}

}

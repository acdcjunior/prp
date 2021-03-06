package io.github.acdcjunior.prp.legacy.web.rest;

import io.github.acdcjunior.prp.legacy.domain.previsao.Previsao;
import io.github.acdcjunior.prp.legacy.domain.previsao.PrevisaoRepository;
import io.github.acdcjunior.prp.legacy.domain.previsaomovimentacoes.PrevisaoComMovs;
import io.github.acdcjunior.prp.legacy.domain.previsaomovimentacoes.PrevisaoComMovsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rest/previsoes")
public class PrevisaoRestController {

	@Autowired
	private PrevisaoRepository previsaoRepository;

    @Autowired
    private PrevisaoComMovsRepository previsaoComMovsRepository;
	
    @RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<PrevisaoComMovs> getAllPrevisoes() {
        return previsaoComMovsRepository.findAll();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Previsao getPrevisaoById(@PathVariable("id") int id) {
        return previsaoRepository.findById(id);
    }

    @RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public Previsao create(@RequestBody Previsao p) {
    	System.out.println("@# CREATING: "+p.getDescricao());
    	previsaoRepository.save(p);
        return p; 
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public Previsao update(@RequestBody Previsao p) {
    	previsaoRepository.save(p);
        return p; 
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public Previsao remove(@PathVariable("id") int id) {
        Previsao p = previsaoRepository.findById(id);
    	if (p == null) {
    		throw new RuntimeException("Previsao #"+id+" nao existe!");
    	}
    	previsaoRepository.remove(p);
    	return p;
    }

}
package net.acdcjunior.prp.web.controller.rest;

import java.util.List;

import net.acdcjunior.prp.domain.previsao.Previsao;
import net.acdcjunior.prp.domain.previsao.PrevisaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest/previsoes")
public class PrevisaoRestController {

	@Autowired
	private PrevisaoRepository previsaoRepository;
	
    @RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Previsao> getAllPrevisoes() {
        return previsaoRepository.findAll();
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
    	System.out.println("@# UPDATING: "+p.getDescricao());
    	previsaoRepository.save(p);
        return p; 
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public Previsao remove(@PathVariable("id") int id, @RequestBody Previsao p) {
    	if (p.getId() != id) {
    		throw new RuntimeException("Id da URL diferente do da previsao fornecida!");
    	}
    	System.out.println("@# DELETING: "+p.getDescricao());
    	previsaoRepository.remove(p);
    	return p;
    }

}
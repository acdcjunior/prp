package net.acdcjunior.prp.web.controller.rest;

import net.acdcjunior.prp.domain.movimentacao.Movimentacao;
import net.acdcjunior.prp.domain.movimentacao.MovimentacaoRepository;
import net.acdcjunior.prp.domain.previsao.Previsao;
import net.acdcjunior.prp.domain.previsao.PrevisaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rest/movimentacoes")
public class MovimentacaoRestController {

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;

	@Autowired
	private PrevisaoRepository previsaoRepository;
	
    @RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Movimentacao> getAllMovimentacoes() {
        return movimentacaoRepository.findAllPorDataNumDoc();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Movimentacao getMovimentacaoById(@PathVariable("id") int id) {
        return movimentacaoRepository.findById(id);
    }

    @RequestMapping(method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public Movimentacao create(@RequestBody Movimentacao m) {
    	movimentacaoRepository.save(m);
        return m;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Transactional
    public Movimentacao update(@RequestBody Movimentacao m) {
        Movimentacao movimentacao = movimentacaoRepository.findById(m.getId());
        movimentacao.setNumeroDocumento(m.getNumeroDocumento());
        movimentacao.setDescricao1(m.getDescricao1());
        movimentacao.setDescricao2(m.getDescricao2());

        Previsao previsaoRealizada = m.getRealiza();
        if (previsaoRealizada != null) {
            previsaoRealizada = previsaoRepository.findById(previsaoRealizada.getId());
        }
        movimentacao.setRealiza(previsaoRealizada);

        movimentacaoRepository.save(movimentacao);
        return m;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Movimentacao remove(@PathVariable("id") int id, @RequestBody Movimentacao movimentacao) {
    	if (movimentacao.getId() != id) {
    		throw new RuntimeException("Id da URL diferente do da movimentacao fornecida!");
    	}
        System.out.println("Tentou remover movimentacao #"+movimentacao.getId());
    	return movimentacao;
    }

}
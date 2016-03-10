package io.github.acdcjunior.prp.web.controller;

import io.github.acdcjunior.prp.domain.movimentacao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/origem")
public class OrigemController {

	@Autowired
	private OrigemRepository origemRepository;
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private OrigemService origemService;
    
    @RequestMapping(method=RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
    public String all(Model model) {
        model.addAttribute("origens", origemRepository.findAll());
        return "origens";
    }
    
    @RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Origem> json() {
    	return origemRepository.findAll();
    }
    
    @RequestMapping(value="/{origemId}/saldo", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> saldo(@PathVariable int origemId) {
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	Origem origem = origemRepository.findById(origemId);
    	Movimentacao movimentacao = movimentacaoRepository.findUltimaMovimentacaoByOrigem(origem);
    	
    	Map<String, Object> saldo = new HashMap<>();
    	saldo.put("UltimaMovimentacaoSaldo", movimentacao.getSaldo());
    	saldo.put("UltimaMovimentacaoData", movimentacao.getData());
    	saldo.put("UltimaMovimentacaoOrdem", 999);
    	return saldo;
    }
    
    @RequestMapping(value="/{origemId}/ultimamovimentacao", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Movimentacao ultimamovimentacao(@PathVariable int origemId) {
    	Origem origem = origemRepository.findById(origemId);
    	return movimentacaoRepository.findUltimaMovimentacaoByOrigem(origem);
    }
    
	@RequestMapping(value="/{origemId}", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> criarMovimentacoes(@PathVariable int origemId, @RequestBody List<Movimentacao> movimentacoes) {
		try {
			origemService.criarMovimentacoes(origemId, movimentacoes);
		} catch (OrigemNaoEquivalenteException | MovimentacaoBaseNaoEhAUltimaDaOrigemException e) {
			return new ResponseEntity<>("Erro: "+e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
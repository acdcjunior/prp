package net.acdcjunior.prp.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.acdcjunior.prp.domain.categoria.Categoria;
import net.acdcjunior.prp.domain.categoria.CategoriaRepository;
import net.acdcjunior.prp.domain.movimentacao.Movimentacao;
import net.acdcjunior.prp.domain.movimentacao.MovimentacaoRepository;
import net.acdcjunior.prp.domain.movimentacao.MovimentacaoService;
import net.acdcjunior.prp.domain.movimentacaosumario.MovimentacaoSumarioAno;
import net.acdcjunior.prp.domain.movimentacaosumario.MovimentacaoSumarioFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/movimentacao")
public class MovimentacaoController {

	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private MovimentacaoService movimentacaoService;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private MovimentacaoSumarioFactory movimentacaoSumarioFactory;
	
    @RequestMapping(method=RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
    public String all(Model model) {
        model.addAttribute("movimentacoes", movimentacaoRepository.findAll());
        model.addAttribute("descricao", "Todos os anos");
        return "movimentacao/list";
    }
    
    @RequestMapping(value="/add", method=RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
    public String add() {
    	return "movimentacao/add";
    }
    
    @RequestMapping(value="/{ano}", method=RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
    public String ano(@PathVariable("ano") int ano, Model model) {
    	model.addAttribute("movimentacoes", movimentacaoRepository.findByAno(ano));
    	model.addAttribute("descricao", ano);
    	return "movimentacao/list";
    }
    
    @RequestMapping(value="/{ano}/{mes}", method=RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
    public String anoMes(@PathVariable("ano") int ano, @PathVariable("mes") int mes, Model model) {
    	model.addAttribute("movimentacoes", movimentacaoRepository.findByAnoMes(ano, mes));
    	model.addAttribute("descricao", ano+"/"+mes);
    	return "movimentacao/list";
    }
    
    @RequestMapping(value="/limitrofes/{ano}/{mes}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> anoMesJson(@PathVariable("ano") int ano, @PathVariable("mes") int mes, Model model) {
    	return movimentacaoService.getMovimentacoesMesComLimitrofes(ano, mes);
    }
    
    @RequestMapping(value="/sumario", method=RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
    public String sumario(Model model) {
    	List<Integer> anos = movimentacaoRepository.findAnos();
    	List<MovimentacaoSumarioAno> sumarios = new ArrayList<>(anos.size());
    	for (Integer ano : anos) {
			sumarios.add(movimentacaoSumarioFactory.createMovimentacaoSumarioAno(ano));
		}
    	model.addAttribute("sumarios", sumarios);
    	return "movimentacao/sumario";
    }
    
    @RequestMapping(value="/previsao/{categoriaId}/{ano}/{mes}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Movimentacao> previstasNaCategoriaNoAnoMes(@PathVariable("categoriaId") int categoriaId,
    								 @PathVariable("ano") int ano,
    								 @PathVariable("mes") int mes) {
    	Categoria categoria = categoriaRepository.findById(categoriaId);
    	List<Movimentacao> movimentacoes = movimentacaoRepository.findByPrevisaoAnoMesCategoria(ano, mes, categoria);
    	return movimentacoes;
    }
    
    @RequestMapping(value="/semcategoria/{ano}/{mes}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Movimentacao> semCategoriaNoAnoMes(@PathVariable("ano") int ano, @PathVariable("mes") int mes) {
    	return movimentacaoRepository.findByAnoMesSemCategoria(ano, mes);
    }
    
	@RequestMapping(value="/{movimentacaoId}", method=RequestMethod.PATCH, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public ResponseEntity<Void> setRealiza(@PathVariable("movimentacaoId") int movimentacaoId, @RequestBody int previsaoId) {
		movimentacaoService.alterarRealiza(movimentacaoId, previsaoId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{movimentacaoId}", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> update(@PathVariable("movimentacaoId") int movimentacaoId, @RequestBody Movimentacao movimentacao) {
		if (movimentacaoId != movimentacao.getId()) {
			return new ResponseEntity<>("Id da movimentacao passada difere da URL tentada", HttpStatus.BAD_REQUEST);
		}
		movimentacaoService.update(movimentacao);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/{movimentacaoId}", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Movimentacao getJson(@PathVariable("movimentacaoId") int movimentacaoId) {
		return movimentacaoRepository.findById(movimentacaoId);
	}

}
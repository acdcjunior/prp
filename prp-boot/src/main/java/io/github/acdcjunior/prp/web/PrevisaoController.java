package io.github.acdcjunior.prp.web;

import io.github.acdcjunior.prp.domain.ImpossivelExcluirException;
import io.github.acdcjunior.prp.domain.ano.categorianoano.CategoriaNoAnoFactory;
import io.github.acdcjunior.prp.domain.ano.categorianoano.CategoriaNoMes;
import io.github.acdcjunior.prp.domain.categoria.Categoria;
import io.github.acdcjunior.prp.domain.categoria.CategoriaRepository;
import io.github.acdcjunior.prp.domain.movimentacao.Movimentacao;
import io.github.acdcjunior.prp.domain.movimentacao.MovimentacaoRepository;
import io.github.acdcjunior.prp.domain.previsao.Previsao;
import io.github.acdcjunior.prp.domain.previsao.PrevisaoRepository;
import io.github.acdcjunior.prp.domain.previsao.PrevisaoService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.util.*;

import static org.apache.commons.lang.StringUtils.leftPad;

@Controller
@RequestMapping("/previsao")
public class PrevisaoController {

	@Autowired
	private PrevisaoRepository previsaoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;
	
	@Autowired
	private PrevisaoService previsaoService;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private CategoriaNoAnoFactory categoriaNoAnoFactory;
    
    @RequestMapping(method=RequestMethod.GET, produces={MediaType.ALL_VALUE, MediaType.TEXT_HTML_VALUE})
    public String all(Model model) {
        model.addAttribute("previsoes", previsaoRepository.findAll());
        model.addAttribute("descricao", "Todos os anos");
        return "previsao/list";
    }
    
    @RequestMapping(value="ano/{ano}", method=RequestMethod.GET, produces={MediaType.ALL_VALUE, MediaType.TEXT_HTML_VALUE})
    public String ano(@PathVariable("ano") int ano, Model model) {
    	model.addAttribute("previsoes", previsaoRepository.findByAno(ano));
    	model.addAttribute("descricao", ano);
    	model.addAttribute("linkNext", "movimentacao/"+(ano+1));
    	model.addAttribute("linkPrev", "movimentacao/"+(ano-1));
    	return "previsao/list";
    }
    
    @RequestMapping(value="/{ano}/{mes}", method=RequestMethod.GET, produces={MediaType.ALL_VALUE, MediaType.TEXT_HTML_VALUE})
    public String anoMes(@PathVariable("ano") int ano, @PathVariable("mes") int mes, Model model) {
    	model.addAttribute("previsoes", previsaoRepository.findByAnoMes(ano, mes));
    	model.addAttribute("descricao", ano+"/"+mes);
    	model.addAttribute("linkNext", "previsao/" + (mes == 12 ? (ano+1)+"/1" : ano+"/"+(mes+1)) );
    	model.addAttribute("linkPrev", "previsao/" + (mes == 1 ? (ano-1)+"/12" : ano+"/"+(mes-1)) );
    	return "previsao/list";
    }
    
    @RequestMapping(value="bills/{ano}/{mes}", method=RequestMethod.GET, produces={MediaType.ALL_VALUE, MediaType.TEXT_HTML_VALUE})
    public String billAnoMes(@PathVariable("ano") int ano, @PathVariable("mes") int mes, Model model) {
    	List<Previsao> findByAnoMes = previsaoRepository.findByAnoMes(ano, mes);
    	for (Iterator<Previsao> iterator = findByAnoMes.iterator(); iterator.hasNext();) {
			Previsao previsao = iterator.next();
			if (!previsao.isBill()) {
				iterator.remove();
			}
		}
    	Collections.sort(findByAnoMes, new Comparator<Previsao>() {
			@Override
			public int compare(Previsao p, Previsao q) {
		        if (p.getData().isBefore(q.getData())) {
		            return -1;
		        } else if (p.getData().isAfter(q.getData())) {
		            return 1;
		        } else {
		            return 0;
		        }  
			}
		});
		model.addAttribute("previsoes", findByAnoMes);
    	model.addAttribute("descricao", ano+"/"+mes);
    	model.addAttribute("linkNext", "previsao/bills/" + (mes == 12 ? (ano+1)+"/1" : ano+"/"+(mes+1)) );
    	model.addAttribute("linkPrev", "previsao/bills/" + (mes == 1 ? (ano-1)+"/12" : ano+"/"+(mes-1)) );
    	return "previsao/bill";
    }
    
    @RequestMapping(value="/categoria/{categoriaId}", method=RequestMethod.GET, produces={MediaType.ALL_VALUE, MediaType.TEXT_HTML_VALUE})
    public String categoriaAnoMesHTML(@PathVariable("categoriaId") int categoriaId, Model model) {
    	Categoria categoria = categoriaRepository.findById(categoriaId);
    	model.addAttribute("previsoes", previsaoRepository.findByCategoria(categoria));
    	model.addAttribute("descricao", categoria.getNome()+" (Categoria)");
    	return "previsao/list";
    }
    
    @RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Previsao> allJson() {
    	return previsaoRepository.findAll();
    }
    
    @RequestMapping(value="/{previsaoId}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable("previsaoId") int previsaoId) {
    	try {
    		previsaoService.excluir(previsaoId);
		} catch (ImpossivelExcluirException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
		}
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value="/{previsaoId}", method=RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
    public ModelAndView id(@PathVariable("previsaoId") int previsaoId) {
    	Previsao previsao = previsaoRepository.findById(previsaoId);
    	List<Movimentacao> movimentacoes = movimentacaoRepository.findByRealiza(previsao);
    	
    	Map<String, Object> model = new HashMap<>();
    	model.put("previsao", previsao);
    	model.put("movimentacoes", movimentacoes);
    	return new ModelAndView("previsao/id", model);
    }
    
    @RequestMapping(value="/{ano}/{mes}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Previsao> anoMes(@PathVariable("ano") int ano, @PathVariable("mes") int mes) {
    	return previsaoRepository.findByAnoMes(ano, mes);
    }
    
    @RequestMapping(value="/{categoriaId}/{ano}/{mes}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Previsao> categoriaAnoMes(@PathVariable("categoriaId") int categoriaId,
    								 	  @PathVariable("ano") int ano,
    								 	  @PathVariable("mes") int mes) {
    	Categoria categoria = categoriaRepository.findById(categoriaId);
    	return previsaoRepository.findByAnoMesCategoria(ano, mes, categoria);
    }
    
	@RequestMapping(method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public ResponseEntity<Void> add(@RequestBody Previsao previsao) {
		previsaoService.salvar(previsao);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", servletContext.getContextPath() + "/previsao/" + previsao.getId());
		return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
	}
	
    @RequestMapping(value="/add", method=RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
    public String add(Model model,
    		@RequestParam(value="categoria", required=false) Integer categoriaId,
    		@RequestParam(value="ano", required=false) Integer ano,
    		@RequestParam(value="mes", required=false) Integer mes,
            @RequestParam(value="descricao", required=false) String descricao) {

        model.addAttribute("descricaoInicial", descricao);
    	
        {        
	        String categoriaInicial = "";
	        if (categoriaId != null) {
	        	categoriaInicial = categoriaId.toString();
	        }
	        model.addAttribute("categoriaInicial", categoriaInicial);
        }
        {
	        String dataInicial = "";
	        if (ano != null && mes != null) {
	        	dataInicial = ano + "-" + leftPad(mes.toString(), 2, '0') + "-01";
	        }
	        model.addAttribute("dataInicial", dataInicial);
    	}
        {   
        	List<Categoria> categorias = categoriaRepository.findAll();
        	ObjectMapper objMapper = new ObjectMapper();
	        String categoriasJson = "[]";
	        try {
	        	categoriasJson = objMapper.writeValueAsString(categorias);
			} catch (Exception e) { e.printStackTrace(); }
	        
			model.addAttribute("categorias", categoriasJson);
        }
        return "previsao/add";
    }
    
	@RequestMapping(value="/{idPrevisao}/realizada", method=RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public ResponseEntity<CategoriaNoMes> realizada(@PathVariable("idPrevisao") int idPrevisao, @RequestBody boolean realizada) {
		Previsao previsao;
		if (realizada) {
			previsao = previsaoService.realizar(idPrevisao);
		} else {
			previsao = previsaoService.desrealizar(idPrevisao);
		}
		
		CategoriaNoMes categoriaNoMes = categoriaNoAnoFactory.createCategoriaNoMes(previsao.getAno(), previsao.getMes(), previsao.getCategoria());
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", servletContext.getContextPath() + "/previsao/" + idPrevisao);
		return new ResponseEntity<>(categoriaNoMes, responseHeaders, HttpStatus.OK);
	}
	
}
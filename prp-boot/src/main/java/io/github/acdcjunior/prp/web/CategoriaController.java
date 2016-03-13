package io.github.acdcjunior.prp.web;

import io.github.acdcjunior.prp.domain.ImpossivelExcluirException;
import io.github.acdcjunior.prp.domain.categoria.Categoria;
import io.github.acdcjunior.prp.domain.categoria.CategoriaRepository;
import io.github.acdcjunior.prp.domain.categoria.CategoriaService;
import io.github.acdcjunior.prp.domain.previsao.Previsao;
import io.github.acdcjunior.prp.domain.previsao.PrevisaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private PrevisaoRepository previsaoRepository;
	
	@Autowired
	private CategoriaService categoriaService;
    
    @RequestMapping(method=RequestMethod.GET)
    public String all(Model model) {
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "categoria/all";
    }
    
    @RequestMapping(value="/{categoriaId}", method=RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable("categoriaId") int categoriaId) {
    	try {
			categoriaService.excluir(categoriaId);
		} catch (ImpossivelExcluirException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
		}
    	return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value="/{categoriaId}", method=RequestMethod.GET, produces=MediaType.TEXT_HTML_VALUE)
    public ModelAndView id(@PathVariable("categoriaId") int categoriaId) {
    	Categoria categoria = categoriaRepository.findById(categoriaId);
    	
		Map<String, List<Previsao>> meses = new LinkedHashMap<>();
    	List<Previsao> previsoes = previsaoRepository.findByCategoria(categoria);
    	for (Previsao previsao : previsoes) {
			String mes = new SimpleDateFormat("yyyy-MM").format(previsao.getData());
			List<Previsao> previsoesNoMes = meses.get(mes);
			if (previsoesNoMes == null) {
				previsoesNoMes = new ArrayList<>();
				meses.put(mes, previsoesNoMes);
			}
			previsoesNoMes.add(previsao);
		}
    	
    	Map<String, Object> model = new HashMap<>();
    	model.put("categoria", categoria);
    	model.put("meses", meses);
    	return new ModelAndView("categoria/id", model);
    }

}
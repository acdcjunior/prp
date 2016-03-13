package io.github.acdcjunior.prp.web;

import io.github.acdcjunior.prp.domain.ano.Ano;
import io.github.acdcjunior.prp.domain.ano.AnoFactory;
import io.github.acdcjunior.prp.domain.ano.saldodomesnoano.SaldoDoMesNoAnoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ano")
public class AnoController {

    @Autowired
    private AnoFactory anoFactory;
    @Autowired
    private SaldoDoMesNoAnoFactory saldoFactory;
    
    @RequestMapping(value="/{numeroAno}")
    public ModelAndView ano(@PathVariable int numeroAno) {
    	Ano ano = anoFactory.createAno(numeroAno);
        return new ModelAndView("ano", "ano", ano);
    }
    
    @RequestMapping(value="/resetsaldos")
    @ResponseBody
    public void resetsaldos() {
    	saldoFactory.resetSaldos();
    }
    
}
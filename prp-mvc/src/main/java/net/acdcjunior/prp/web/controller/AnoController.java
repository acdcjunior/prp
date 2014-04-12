package net.acdcjunior.prp.web.controller;

import net.acdcjunior.prp.domain.ano.Ano;
import net.acdcjunior.prp.domain.ano.AnoFactory;
import net.acdcjunior.prp.domain.saldo.SaldoFactory;

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
    private SaldoFactory saldoFactory;
    
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
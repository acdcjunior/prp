package net.acdcjunior.prp.web.controller.rest;

import net.acdcjunior.prp.application.mescontabil.MesContabilDto;
import net.acdcjunior.prp.application.mescontabil.MesContabilService;
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
@RequestMapping("/rest/mes")
public class MesContabilController {

	@Autowired
	private MesContabilService mesContabilService;

    @RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MesContabilDto> all() {
        return mesContabilService.all();
    }

    @RequestMapping(value="/{ano}/{mes}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MesContabilDto get(@PathVariable("ano") int ano, @PathVariable("mes") int mes) {
        return mesContabilService.mes(ano, mes);
    }

}
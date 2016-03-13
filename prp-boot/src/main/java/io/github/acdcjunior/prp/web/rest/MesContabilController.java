package io.github.acdcjunior.prp.web.rest;

import io.github.acdcjunior.prp.application.mescontabil.MesContabilDto;
import io.github.acdcjunior.prp.application.mescontabil.MesContabilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
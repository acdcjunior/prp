package io.github.acdcjunior.prp.web.controller;

import io.github.acdcjunior.prp.arquivo.Arquivo;
import io.github.acdcjunior.prp.arquivo.ArquivoRepository;
import io.github.acdcjunior.prp.web.infrastructure.UploadUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

@Controller
@RequestMapping("/arquivo")
public class ArquivoController {

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private UploadUtil uploadUtil;

    @RequestMapping
    public String index(Map<String, Object> model) {
        model.put("arquivos", arquivoRepository.findAll());
        return "arquivo/list";
    }

    @Transactional
    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String save(@ModelAttribute("arquivo") Arquivo arquivo, @RequestParam("file") MultipartFile file) {
        arquivo.setNomeArquivo(file.getOriginalFilename());
        arquivo.setConteudo(uploadUtil.createBlob(file));
        arquivo.setTipoConteudo(file.getContentType());
        arquivoRepository.save(arquivo);
        return "redirect:/arquivo/";
    }

    @RequestMapping("/download/{id}")
    public String download(@PathVariable("id") Integer idArquivo, HttpServletResponse response) throws Exception {
        Arquivo doc = arquivoRepository.findById(idArquivo);
        response.setHeader("Content-Disposition", "inline;filename=\"" + doc.getNomeArquivo() + "\"");
        OutputStream out = response.getOutputStream();
        response.setContentType(doc.getTipoConteudo());
        IOUtils.copy(doc.getConteudo().getBinaryStream(), out);
        out.flush();
        out.close();
        return null;
    }

}
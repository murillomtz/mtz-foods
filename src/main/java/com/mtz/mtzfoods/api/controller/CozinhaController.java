package com.mtz.mtzfoods.api.controller;

import com.mtz.mtzfoods.api.model.CozinhasXmlWrapper;
import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody //Indica que a respostar desse controler, devem ir para a resposta da request HTTP
@RestController //É um controlador com @ResponseBody imbutido
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
    @Autowired
    private CozinhaRepository cozinhaRepository;

    //@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)//Metodo produz apenas formato JSON
    @GetMapping()
    public List<Cozinha> listar() {
        return cozinhaRepository.todas();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(cozinhaRepository.todas());
    }

    @GetMapping(value = "/{cozinhaId}")
    public Cozinha buscar(@PathVariable Long cozinhaId) {
        return cozinhaRepository.potId(cozinhaId);
    }


}

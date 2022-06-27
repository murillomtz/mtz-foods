package com.mtz.mtzfoods.api.controller;

import com.mtz.mtzfoods.api.exceptionhandler.Problema;
import com.mtz.mtzfoods.domain.exception.EntidadeNaoEncontradaException;
import com.mtz.mtzfoods.domain.exception.EstadoNaoEncontradoException;
import com.mtz.mtzfoods.domain.exception.NegocioException;
import com.mtz.mtzfoods.domain.model.Cidade;
import com.mtz.mtzfoods.domain.repository.CidadeRepository;
import com.mtz.mtzfoods.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

//@Controller
//@ResponseBody //Indica que a respostar desse controler, devem ir para a resposta da request HTTP
@RestController //É um controlador com @ResponseBody imbutido
@RequestMapping(value = "/cidades")
public class CidadeController {
    @Autowired
    private CidadeRepository repository;

    @Autowired
    private CadastroCidadeService service;

    //@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)//Metodo produz apenas formato JSON
    @GetMapping()
    public List<Cidade> listar() {
        return repository.findAll();
    }

    @GetMapping(value = "/{cidadeId}")
    public Cidade buscar(@PathVariable Long cidadeId) {
        return service.buscarOuFalhar(cidadeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody Cidade cidade) {
        try {
            return service.salvar(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @PutMapping("/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
        try {
            Cidade cidadeAtual = service.buscarOuFalhar(cidadeId);

            BeanUtils.copyProperties(cidade, cidadeAtual, "id");

            return service.salvar(cidadeAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cidadeId) {
        service.excluir(cidadeId);
    }



}

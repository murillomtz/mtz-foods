package com.mtz.mtzfoods.api.controller;

import com.mtz.mtzfoods.domain.exception.EntidadeEmUsoException;
import com.mtz.mtzfoods.domain.exception.EntidadeNaoEncontradaException;
import com.mtz.mtzfoods.domain.model.Cidade;
import com.mtz.mtzfoods.domain.repository.CidadeRepository;
import com.mtz.mtzfoods.domain.service.CadastroCidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return repository.todas();
    }

    @GetMapping(value = "/{cidadeId}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
        Cidade cidade = repository.potId(cidadeId);

        if (cidade != null) {
            return ResponseEntity.ok(cidade);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
        try {
            cidade = service.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<?> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {

        try {
            Cidade cidadeAtual = repository.potId(cidadeId);

            if (cidadeAtual != null) {
                /**
                 * Copia a cidade para cidadeAtual, e ignora o ID, pois está null
                 * */
                BeanUtils.copyProperties(cidade, cidadeAtual, "id");

                cidadeAtual = service.salvar(cidadeAtual);
                return ResponseEntity.ok(cidadeAtual);
            }

            return ResponseEntity.notFound().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }

    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId) {
        try {
            service.excluir(cidadeId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }


}

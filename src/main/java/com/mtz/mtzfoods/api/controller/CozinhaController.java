package com.mtz.mtzfoods.api.controller;

import com.mtz.mtzfoods.domain.exception.EntidadeEmUsoException;
import com.mtz.mtzfoods.domain.exception.EntidadeNaoEncontradaException;
import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.repository.CozinhaRepository;
import com.mtz.mtzfoods.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody //Indica que a respostar desse controler, devem ir para a resposta da request HTTP
@RestController //É um controlador com @ResponseBody imbutido
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
    @Autowired
    private CozinhaRepository repository;

    @Autowired
    private CadastroCozinhaService service;

    //@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)//Metodo produz apenas formato JSON
    @GetMapping()
    public List<Cozinha> listar() {
        return repository.todas();
    }

    @GetMapping(value = "/{cozinhaId}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
        Cozinha cozinha = repository.potId(cozinhaId);

        if (cozinha != null) {
            return ResponseEntity.ok(cozinha);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adcionar(@RequestBody Cozinha cozinha) {
        service.salvar(cozinha);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long cozinhaId,
                                             @RequestBody Cozinha cozinha) {

        Cozinha cozinhaAtual = repository.potId(cozinhaId);
        if (cozinhaAtual != null) {
            /**
             * Copia a cozinha para cozinhaAtual, e ignora o ID, pois está null
             * */
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

            cozinhaAtual = service.salvar(cozinhaAtual);
            return ResponseEntity.ok(cozinhaAtual);
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {
        try {
            service.excluir(cozinhaId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }


}

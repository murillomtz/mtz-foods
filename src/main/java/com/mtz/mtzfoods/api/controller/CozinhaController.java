package com.mtz.mtzfoods.api.controller;

import com.mtz.mtzfoods.domain.exception.EntidadeNaoEncontradaException;
import com.mtz.mtzfoods.domain.exception.NegocioException;
import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.repository.CozinhaRepository;
import com.mtz.mtzfoods.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return repository.findAll();
    }

    @GetMapping(value = "/{cozinhaId}")
    public Cozinha buscar(@PathVariable Long cozinhaId) {
        return service.buscarOuFalhar(cozinhaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha adicionar(@RequestBody Cozinha cozinha) {
        return service.salvar(cozinha);

    }
///////
    @PutMapping("/{cozinhaId}")
    public Cozinha atualizar(@PathVariable Long cozinhaId,
                             @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = service.buscarOuFalhar(cozinhaId);

        /**
         * Copia a cozinha para cozinhaAtual, e ignora o ID, pois está null
         * */
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        return service.salvar(cozinhaAtual);
    }

//    @PutMapping("/{cozinhaId}")
//    public Cozinha atualizar(@PathVariable Long cozinhaId,
//                             @RequestBody Cozinha cozinha) {
//        Cozinha cozinhaAtual = service.buscarOuFalhar(cozinhaId);
//
//        /**
//         * Copia a cozinha para cozinhaAtual, e ignora o ID, pois está null
//         * */
//        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
//        try {
//
//            return service.salvar(cozinhaAtual);
//        } catch (
//                EntidadeNaoEncontradaException e) {
//            throw new NegocioException(e.getMessage());
//        }
//    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long cozinhaId) {

        service.excluir(cozinhaId);

    }


}

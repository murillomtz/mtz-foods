package com.mtz.mtzfoods.api.controller;

import com.mtz.mtzfoods.domain.exception.EntidadeEmUsoException;
import com.mtz.mtzfoods.domain.exception.EntidadeNaoEncontradaException;
import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.model.Restaurante;
import com.mtz.mtzfoods.domain.repository.RestauranteRepository;
import com.mtz.mtzfoods.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@ResponseBody //Indica que a respostar desse controler, devem ir para a resposta da request HTTP
@RestController //É um controlador com @ResponseBody imbutido
@RequestMapping(value = "/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteRepository repository;

    @Autowired
    private CadastroRestauranteService service;

    //@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)//Metodo produz apenas formato JSON
    @GetMapping()
    public List<Restaurante> listar() {
        return repository.todas();
    }

    @GetMapping(value = "/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante = repository.potId(restauranteId);

        if (restaurante != null) {
            return ResponseEntity.ok(restaurante);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adcionar(@RequestBody Restaurante restaurante) {
        service.salvar(restaurante);
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {

        Restaurante restauranteAtual = repository.potId(restauranteId);
        if (restauranteAtual != null) {
            /**
             * Copia a restaurante para restauranteAtual, e ignora o ID, pois está null
             * */
            BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

            restauranteAtual = service.salvar(restauranteAtual);
            return ResponseEntity.ok(restauranteAtual);
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> remover(@PathVariable Long restauranteId) {
        try {
            service.excluir(restauranteId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }


}

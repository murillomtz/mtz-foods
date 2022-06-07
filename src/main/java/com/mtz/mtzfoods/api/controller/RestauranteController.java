package com.mtz.mtzfoods.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtz.mtzfoods.domain.exception.EntidadeEmUsoException;
import com.mtz.mtzfoods.domain.exception.EntidadeNaoEncontradaException;
import com.mtz.mtzfoods.domain.model.Restaurante;
import com.mtz.mtzfoods.domain.repository.RestauranteRepository;
import com.mtz.mtzfoods.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            restaurante = service.salvar(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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

    /**
     * {@link PatchMapping: N indicado a usar, devido o tralho. No projeto terá outras formas melhores}
     */
    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos) {

        Restaurante restauranteAtual = repository.potId(restauranteId);
        if (restauranteAtual == null) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteAtual);

        return atualizar(restauranteId, restauranteAtual);
    }


    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        //Converter JSON EM JAVA
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.
                convertValue(dadosOrigem, Restaurante.class);


        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);//Para acessar variaveis privadas dentro do da classe
            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
            //System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);
            ReflectionUtils.setField(field, restauranteDestino, novoValor);

        });
    }


}

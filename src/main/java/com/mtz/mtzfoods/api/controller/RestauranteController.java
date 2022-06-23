package com.mtz.mtzfoods.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtz.mtzfoods.domain.exception.EntidadeNaoEncontradaException;
import com.mtz.mtzfoods.domain.exception.NegocioException;
import com.mtz.mtzfoods.domain.model.Restaurante;
import com.mtz.mtzfoods.domain.repository.RestauranteRepository;
import com.mtz.mtzfoods.domain.service.CadastroRestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return repository.findAll();
    }

    @GetMapping(value = "/{restauranteId}")
    public Restaurante buscar(@PathVariable Long restauranteId) {
        return service.buscarOuFalhar(restauranteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody Restaurante restaurante) {
        try {
            return service.salvar(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public Restaurante atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {

        Restaurante restauranteAtual = service.buscarOuFalhar(restauranteId);

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
        try {
            return service.salvar(restauranteAtual);
        } catch (
                EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

//    @DeleteMapping("/{restauranteId}")
//    public ResponseEntity<Restaurante> remover(@PathVariable Long restauranteId) {
//        try {
//            service.excluir(restauranteId);
//            return ResponseEntity.noContent().build();
//
//        } catch (EntidadeNaoEncontradaException e) {
//            return ResponseEntity.notFound().build();
//
//        } catch (EntidadeEmUsoException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
//    }

    /**
     * {@link PatchMapping: N indicado a usar, devido o tralho. No projeto terá outras formas melhores}
     */
    @PatchMapping("/{restauranteId}")
    public Restaurante atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos) {
        Restaurante restauranteAtual = service.buscarOuFalhar(restauranteId);


        merge(campos, restauranteAtual);

        return atualizar(restauranteId, restauranteAtual);
    }


    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        //Converter JSON EM JAVA
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

//			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }
}




package com.mtz.mtzfoods.api.controller;

import com.mtz.mtzfoods.domain.exception.EntidadeEmUsoException;
import com.mtz.mtzfoods.domain.exception.EntidadeNaoEncontradaException;
import com.mtz.mtzfoods.domain.model.Estado;
import com.mtz.mtzfoods.domain.repository.EstadoRepository;
import com.mtz.mtzfoods.domain.service.CadastroEstadoService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
//@ResponseBody //Indica que a respostar desse controler, devem ir para a resposta da request HTTP
@RestController //É um controlador com @ResponseBody imbutido
@RequestMapping(value = "/estados")
public class EstadoController {
    @Autowired
    private EstadoRepository repository;

    @Autowired
    private CadastroEstadoService service;

    //@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)//Metodo produz apenas formato JSON
    @GetMapping()
    public List<Estado> listar() {
        return repository.todas();
    }

    @GetMapping(value = "/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
        Estado estado = repository.potId(estadoId);

        if (estado != null) {
            return ResponseEntity.ok(estado);
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado) {
        return service.salvar(estado);
    }


    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {

        Estado estadoAtual = repository.potId(estadoId);
        if (estadoAtual != null) {
            /**
             * Copia o estado para estadoAtual, e ignora o ID, pois está null
             * */
            BeanUtils.copyProperties(estado, estadoAtual, "id");

            estadoAtual = service.salvar(estadoAtual);
            return ResponseEntity.ok(estadoAtual);
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<?> remover(@PathVariable Long estadoId) {
        try {
            service.excluir(estadoId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }


    }


}

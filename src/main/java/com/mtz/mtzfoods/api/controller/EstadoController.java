package com.mtz.mtzfoods.api.controller;

import com.mtz.mtzfoods.domain.model.Estado;
import com.mtz.mtzfoods.domain.repository.EstadoRepository;
import com.mtz.mtzfoods.domain.service.CadastroEstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        return repository.findAll();
    }

    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId) {
        return service.buscarOuFalhar(estadoId);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar(@RequestBody Estado estado) {
        return service.salvar(estado);
    }


    @PutMapping("/{estadoId}")
    public Estado atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {

        Estado estadoAtual = service.buscarOuFalhar(estadoId);

        BeanUtils.copyProperties(estado, estadoAtual, "id");

        return service.salvar(estadoAtual);


    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long estadoId) {
        service.excluir(estadoId);
    }


}

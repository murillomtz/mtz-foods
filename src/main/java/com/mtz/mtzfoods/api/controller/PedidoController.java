package com.mtz.mtzfoods.api.controller;

import com.mtz.mtzfoods.api.assembler.PedidoInputDisassembler;
import com.mtz.mtzfoods.api.assembler.PedidoModelAssembler;
import com.mtz.mtzfoods.api.assembler.PedidoResumoModelAssembler;
import com.mtz.mtzfoods.api.model.PedidoModel;
import com.mtz.mtzfoods.api.model.PedidoResumoModel;
import com.mtz.mtzfoods.api.model.input.PedidoInput;
import com.mtz.mtzfoods.domain.exception.EntidadeNaoEncontradaException;
import com.mtz.mtzfoods.domain.exception.NegocioException;
import com.mtz.mtzfoods.domain.model.Pedido;
import com.mtz.mtzfoods.domain.model.Usuario;
import com.mtz.mtzfoods.domain.repository.PedidoRepository;
import com.mtz.mtzfoods.domain.repository.filter.PedidoFilter;
import com.mtz.mtzfoods.domain.service.EmissaoPedidoService;
import com.mtz.mtzfoods.infrastructure.repository.spec.PedidoSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private EmissaoPedidoService emissaoPedido;

	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;

	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;

	@Autowired
	private PedidoInputDisassembler pedidoInputDisassembler;

	@GetMapping
	public List<PedidoResumoModel> pesquisar(PedidoFilter filtro) {
		List<Pedido> todosPedidos = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro));

		return pedidoResumoModelAssembler.toCollectionModel(todosPedidos);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
		try {
			Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

			// TODO pegar usuário autenticado
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);

			novoPedido = emissaoPedido.emitir(novoPedido);

			return pedidoModelAssembler.toModel(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);

		return pedidoModelAssembler.toModel(pedido);
	}

}


package com.mtz.mtzfoods.domain.service;

import com.mtz.mtzfoods.domain.exception.NegocioException;
import com.mtz.mtzfoods.domain.exception.ProdutoNaoEncontradoException;
import com.mtz.mtzfoods.domain.exception.UsuarioNaoEncontradoException;
import com.mtz.mtzfoods.domain.model.Produto;
import com.mtz.mtzfoods.domain.model.Usuario;
import com.mtz.mtzfoods.domain.repository.ProdutoRepository;
import com.mtz.mtzfoods.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CadastroProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Transactional
	public Produto salvar(Produto produto) {
		return repository.save(produto);
	}

	public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
		return repository.findById(restauranteId, produtoId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
	}


}

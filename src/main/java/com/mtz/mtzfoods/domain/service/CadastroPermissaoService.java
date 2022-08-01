package com.mtz.mtzfoods.domain.service;

import com.mtz.mtzfoods.domain.exception.EntidadeEmUsoException;
import com.mtz.mtzfoods.domain.exception.FormaPagamentoNaoEncontradaException;
import com.mtz.mtzfoods.domain.exception.PermissaoNaoEncontradaException;
import com.mtz.mtzfoods.domain.model.FormaPagamento;
import com.mtz.mtzfoods.domain.model.Permissao;
import com.mtz.mtzfoods.domain.repository.FormaPagamentoRepository;
import com.mtz.mtzfoods.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroPermissaoService {

	@Autowired
	private PermissaoRepository repository;

	public Permissao buscarOuFalhar(Long permissaoId) {
		return repository.findById(permissaoId)
				.orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
	}
	
}

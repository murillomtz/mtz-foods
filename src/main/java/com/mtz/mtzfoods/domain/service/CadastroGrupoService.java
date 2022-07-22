package com.mtz.mtzfoods.domain.service;

import com.mtz.mtzfoods.domain.model.Permissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtz.mtzfoods.domain.exception.EntidadeEmUsoException;
import com.mtz.mtzfoods.domain.exception.GrupoNaoEncontradoException;
import com.mtz.mtzfoods.domain.model.Grupo;
import com.mtz.mtzfoods.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	private static final String MSG_GRUPO_EM_USO 
		= "Grupo de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private GrupoRepository repository;

	@Autowired
	private CadastroPermissaoService cadastroPermissao;
	
	@Transactional
	public Grupo salvar(Grupo grupo) {
		return repository.save(grupo);
	}
	
	@Transactional
	public void excluir(Long grupoId) {
		try {
			repository.deleteById(grupoId);
			repository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_GRUPO_EM_USO, grupoId));
		}
	}
	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = cadastroPermissao.buscarOuFalhar(permissaoId);

		grupo.removerPermissao(permissao);
	}

	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = cadastroPermissao.buscarOuFalhar(permissaoId);

		grupo.adicionarPermissao(permissao);
	}

	public Grupo buscarOuFalhar(Long grupoId) {
		return repository.findById(grupoId)
				.orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
	}
	
}

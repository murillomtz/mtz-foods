package com.mtz.mtzfoods.api.assembler;

import com.mtz.mtzfoods.api.model.input.CidadeInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mtz.mtzfoods.api.model.input.CidadeInput;
import com.mtz.mtzfoods.domain.model.Cidade;
import com.mtz.mtzfoods.domain.model.Estado;

@Component
public class CidadeInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public Cidade toDomainObject(CidadeInput cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
		// Para evitar org.hibernate.HibernateException: identifier of an instance of 
		// com.mtz.mtzfoods.domain.model.Estado was altered from 1 to 2
		cidade.setEstado(new Estado());
		
		modelMapper.map(cidadeInput, cidade);
	}
	
}

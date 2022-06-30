package com.mtz.mtzfoods;



import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.service.CadastroCozinhaService;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
@SpringBootTest
@RunWith(SpringRunner.class)
public class CadastroCozinhaIntegrationTests {
    @Autowired
    private CadastroCozinhaService cadastroCozinha;
    @Test
    public void testarCadastroCozinhaComSucesso() {
        //Cenario
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        //Ação

        novaCozinha = cadastroCozinha.salvar(novaCozinha);

        //Validação
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }
    @Test(expected = DataIntegrityViolationException.class)
    public void testeCadastroCozinhaSemNome(){
        //Cenario
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        //Ação

        novaCozinha = cadastroCozinha.salvar(novaCozinha);

        //Validação
    }

}

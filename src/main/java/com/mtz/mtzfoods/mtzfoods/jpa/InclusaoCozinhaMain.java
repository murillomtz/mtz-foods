package com.mtz.mtzfoods.mtzfoods.jpa;

import com.mtz.mtzfoods.mtzfoods.MtzFoodsApplication;
import com.mtz.mtzfoods.mtzfoods.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(MtzFoodsApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

        Cozinha cozinha1 =   new Cozinha();
        cozinha1.setNome("Brasileira");

        Cozinha cozinha2 =   new Cozinha();
        cozinha2.setNome("Japonesas");

        cadastroCozinha.salvar(cozinha1);
        cadastroCozinha.salvar(cozinha2);

        System.out.printf("%d ############# %s\n", cozinha1.getId(), cozinha1.getNome());
        System.out.printf("%d ############# %s\n", cozinha2.getId(), cozinha2.getNome());

    }
}

package com.mtz.mtzfoods.mtzfoods.jpa;

import com.mtz.mtzfoods.mtzfoods.MtzFoodsApplication;
import com.mtz.mtzfoods.mtzfoods.model.Cozinha;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.List;

public class consultaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(MtzFoodsApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);

        List<Cozinha> cozinhas =   cadastroCozinha.listar();

        for (Cozinha cozinha: cozinhas) {
            System.out.println(cozinha.getNome());
        }
    }
}

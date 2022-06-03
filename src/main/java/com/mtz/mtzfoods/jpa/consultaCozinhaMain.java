package com.mtz.mtzfoods.jpa;

import com.mtz.mtzfoods.MtzFoodsApplication;
import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.repository.CozinhaRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.List;

public class consultaCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(MtzFoodsApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhaRepository  = applicationContext.getBean(CozinhaRepository.class);

        List<Cozinha> cozinhas =   cozinhaRepository.todas();

        for (Cozinha cozinha: cozinhas) {
            System.out.println(cozinha.getNome());
        }
    }
}

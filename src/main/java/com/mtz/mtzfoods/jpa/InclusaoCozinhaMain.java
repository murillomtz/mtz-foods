package com.mtz.mtzfoods.jpa;

import com.mtz.mtzfoods.MtzFoodsApplication;
import com.mtz.mtzfoods.domain.model.Cozinha;
import com.mtz.mtzfoods.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoCozinhaMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(MtzFoodsApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinha1 =   new Cozinha();
        cozinha1.setNome("Brasileira");

        Cozinha cozinha2 =   new Cozinha();
        cozinha2.setNome("Japonesas");

        cozinhaRepository.save(cozinha1);
        cozinhaRepository.save(cozinha2);

        System.out.printf("%d ############# %s\n", cozinha1.getId(), cozinha1.getNome());
        System.out.printf("%d ############# %s\n", cozinha2.getId(), cozinha2.getNome());

    }
}

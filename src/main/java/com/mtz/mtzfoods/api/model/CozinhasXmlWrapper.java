package com.mtz.mtzfoods.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.mtz.mtzfoods.domain.model.Cozinha;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@JacksonXmlRootElement(localName = "cozinhas")//Especifico para XML
@Data
public class CozinhasXmlWrapper {
    @JsonProperty("cozinhas")
    @JacksonXmlElementWrapper(useWrapping = false)//Desabilita um "embrulho" quando ele es replicando o a tag do obj
    @NonNull //Para a @Data gerar um contrutor com a entidade COzinha
    private List<Cozinha> cozinhas;

}

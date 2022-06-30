package com.mtz.mtzfoods.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Builder - Padrão de projeto para construir obj nume linguagem mais fluida e legivel
 * <p>
 * JsonInclude(Include.NON_NULL) -> para nao mostrar onde for nullo
 */
@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class Problem {

    private Integer status;
    private LocalDateTime timestamp;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private List<Object> objects;

    @Getter
    @Builder
    public static class Object {

        private String name;
        private String userMessage;

    }

}

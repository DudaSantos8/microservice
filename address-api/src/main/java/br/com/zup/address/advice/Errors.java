package br.com.zup.consumer.infra.advice;

import lombok.Data;

@Data
public class Errors {
    private String field;
    private String message;

    public Errors(String field, String message) {
        this.field = field;
        this.message = message;
    }
}


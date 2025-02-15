
package com.gm.brasilerao.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resultado<T> {
    private T sucesso;
    @JsonProperty("message")
    private String erro;

    // Construtor para sucesso
    public Resultado(T sucesso) {
        this.sucesso = sucesso;

        this.erro = null;
    }

    // Construtor para erro
    public Resultado(String erro) {
        this.erro = erro;
        this.sucesso = null;
    }

    // Verifica se o resultado foi bem-sucedido
    public boolean isSucesso() {
        return sucesso != null;
    }

    // Retorna o valor de sucesso
    public T getSucesso() {
        return sucesso;
    }

    // Retorna a mensagem de erro
    public String getErro() {
        return erro;
    }}

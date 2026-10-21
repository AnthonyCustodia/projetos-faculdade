package com.senac.montadora.dtos;

public class ModeloRequestDto {

    private String nome;
    private Long montadoraId;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getMontadoraId() {
        return montadoraId;
    }

    public void setMontadoraId(Long montadoraId) {
        this.montadoraId = montadoraId;
    }
}

package com.senac.montadora.dtos;

import com.senac.montadora.entities.Montadora;

import java.util.List;

public class ModeloRequestDto {

    private String nome;
    private List<MontadoraRequestDto> montadoras;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<MontadoraRequestDto> getMontadoras() {
        return montadoras;
    }

    public void setMontadoras(List<MontadoraRequestDto> montadoras) {
        this.montadoras = montadoras;
    }
}

package com.local.feliprow.quizbandeiras;

public class Pais {

    private final String nome;
    private final int bandeira; // id do drawable

    public Pais(String nome, int bandeira) {
        this.nome = nome;
        this.bandeira = bandeira;
    }

    public String getNome() {
        return nome;
    }

    public int getBandeira() {
        return bandeira;
    }
}

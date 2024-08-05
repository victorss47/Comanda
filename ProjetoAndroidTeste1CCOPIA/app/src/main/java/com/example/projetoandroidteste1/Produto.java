package com.example.projetoandroidteste1;

import java.io.Serializable;

public class Produto implements Serializable {
    private String nome;
    private double preco;
    private int quantidade;



    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = 0;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}

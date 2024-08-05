package com.example.projetoandroidteste1;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Comanda implements Serializable {
    private int numero;
    private double valorTotal;
    private Map<String, Produto> produtos;

    public Comanda(int numero) {
        this.numero = numero;
        this.valorTotal = 0.0;
        this.produtos = new HashMap<>();
        inicializarProdutos();
    }

    private void inicializarProdutos() {
        produtos.put("Cerveja", new Produto("Cerveja", 5.0));
        produtos.put("Longneck", new Produto("Longneck", 6.0));
        produtos.put("LongneckGG", new Produto("LongneckGG", 30.0));
        produtos.put("Energético", new Produto("Energético", 8.0));
        produtos.put("Piriguete", new Produto("Piriguete", 12.0));
        produtos.put("Vinho", new Produto("Vinho", 20.0));
        produtos.put("Jurupinga", new Produto("Jurupinga", 25.0));
        produtos.put("Malibu", new Produto("Malibu", 50.0));
        produtos.put("Malibu com Agua de Coco", new Produto("Malibu com Agua de Coco", 70.0));
        produtos.put("Amarula", new Produto("Amarula", 50.0));
        produtos.put("Gim", new Produto("Gim", 70.0));
        produtos.put("WhiskyGG", new Produto("WhiskyGG", 70.0));
        produtos.put("EnergéticoGG", new Produto("EnergéticoGG", 30.0));
        produtos.put("SucoGG", new Produto("SucoGG", 12.0));
        produtos.put("Agua de CocoGG", new Produto("Agua de CocoGG", 20.0));
        produtos.put("RefrigeranteGG", new Produto("RefrigeranteGG", 12.0));
        produtos.put("AguaGG", new Produto("AguaGG", 8.0));
        produtos.put("Contini", new Produto("Contini", 25.0));
        produtos.put("Dose", new Produto("Dose", 7.0));
        produtos.put("Doses Mistas", new Produto("Doses Mistas", 10.0));
        produtos.put("Whisky's", new Produto("Whisky's", 15.0));
        produtos.put("Suco", new Produto("Suco", 4.0));
        produtos.put("Refrigerante", new Produto("Refrigerante", 4.0));
    }

    public int getNumero() {
        return numero;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Map<String, Produto> getProdutos() {
        return produtos;
    }
}


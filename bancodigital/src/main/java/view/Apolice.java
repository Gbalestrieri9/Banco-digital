package view;

import java.time.LocalDate;

public class Apolice {
    private static int contador = 0; 
    private int numeroApolice;
    private LocalDate dataContratacao;
    private String detalhesCartao;
    private double valorApolice;
    private String descricaoCondicoes;
    
    public Apolice(String detalhesCartao, double valorApolice, String descricaoCondicoes) {
        this.numeroApolice = ++contador; 
        this.dataContratacao = LocalDate.now();
        this.detalhesCartao = detalhesCartao;
        this.valorApolice = valorApolice;
        this.descricaoCondicoes = descricaoCondicoes;
    }

    public int getNumeroApolice() {
        return numeroApolice;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public String getDetalhesCartao() {
        return detalhesCartao;
    }

    public void setDetalhesCartao(String detalhesCartao) {
        this.detalhesCartao = detalhesCartao;
    }

    public double getValorApolice() {
        return valorApolice;
    }

    public void setValorApolice(double valorApolice) {
        this.valorApolice = valorApolice;
    }

    public String getDescricaoCondicoes() {
        return descricaoCondicoes;
    }

    public void setDescricaoCondicoes(String descricaoCondicoes) {
        this.descricaoCondicoes = descricaoCondicoes;
    }
    
    public void exibirInformacoes() {
        System.out.println("Número da Apólice: " + numeroApolice);
        System.out.println("Data da Contratação: " + dataContratacao);
        System.out.println("Detalhes do Cartão: " + detalhesCartao);
        System.out.println("Valor da Apólice: R$" + valorApolice);
        System.out.println("Descrição das Condições: " + descricaoCondicoes);
    }
}
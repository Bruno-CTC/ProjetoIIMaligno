package com.company.classes;

import java.util.Date;

public class Jogo {
    int idJogo, idDesenvolvedor, avaliacao;
    long vendas;
    String nome;
    Date dataLancamento;
    float preco;
    public int getIdJogo()
    {
        return idJogo;
    }
    public int getIdDesenvolvedor()
    {
        return idDesenvolvedor;
    }
    public String getNome()
    {
        return nome;
    }
    public Date getDataLancamento()
    {
        return dataLancamento;
    }
    public long getVendas()
    {
        return vendas;
    }
    public float getPreco()
    {
        return preco;
    }
    public int getAvaliacao()
    {
        return avaliacao;
    }
    public void setIdJogo(int idJogo)
    {
        this.idJogo = idJogo;
    }
    public void setIdDesenvolvedor(int idDesenvolvedor)
    {
        this.idDesenvolvedor = idDesenvolvedor;
    }
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    public void setDataLancamento(Date dataLancamento)
    {
        this.dataLancamento = dataLancamento;
    }
    public void setVendas(long vendas)
    {
        this.vendas = vendas;
    }
    public void setPreco(float preco)
    {
        this.preco = preco;
    }
    public void setAvaliacao(int avaliacao)
    {
        this.avaliacao = avaliacao;
    }

}

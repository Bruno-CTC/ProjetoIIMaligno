package com.company.classes;

import java.util.Date;

public class Jogo
{
    int    idJogo, idDesenvolvedor;
    long   vendas;
    String nome;
    Date   dataLancamento;
    float  preco, avaliacao;

    public Jogo(int idJogo, int idDesenvolvedor, float avaliacao, long vendas, String nome, Date dataLancamento, float preco) throws Exception
    {
        this.setIdJogo          (idJogo);
        this.setIdDesenvolvedor (idDesenvolvedor);
        this.setAvaliacao       (avaliacao);
        this.setVendas          (vendas);
        this.setNome            (nome);
        this.setDataLancamento  (dataLancamento);
        this.setPreco           (preco);
    }

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
    public float getAvaliacao()
    {
        return avaliacao;
    }

    public void setIdJogo(int idJogo) throws Exception
    {
        if(idJogo < 0)

            throw new Exception("id do jogo invalido.");

        this.idJogo = idJogo;
    }
    public void setIdDesenvolvedor(int idDesenvolvedor) throws Exception
    {
        if(idDesenvolvedor < 0)
            throw new Exception("id do desenvolvedor invalido.");

        this.idDesenvolvedor = idDesenvolvedor;
    }
    public void setNome(String nome) throws Exception
    {
        if(nome.trim().isEmpty()) // nome == ""
            throw new Exception("Nome invalido.");

        this.nome = nome;
    }
    public void setDataLancamento(Date dataLancamento) throws Exception
    {
        this.dataLancamento = dataLancamento;
    }
    public void setVendas(long vendas) throws Exception
    {
        if(vendas < 0)
            throw new Exception("Número de vendas invalido.");

        this.vendas = vendas;
    }
    public void setPreco(float preco) throws Exception
    {
        if(preco < 0)
            throw new Exception("Preco invalido.");

        this.preco = preco;
    }
    public void setAvaliacao(float avaliacao) throws Exception
    {
        if(avaliacao < 0)
            throw new Exception("Avaliacao invalida.");

        this.avaliacao = avaliacao;
    }

    public String toString()
    {
        return this.idJogo                    + " / " +
               this.idDesenvolvedor           + " / " +
               this.avaliacao                 + " / " +
               this.vendas                    + " / " +
               this.nome                      + " / " +
               this.dataLancamento.toString() + " / " +
               this.preco;
    }

    public boolean equals (Object obj)
    {
        if(obj==null) return false;
        if(this==obj) return true;

        if(this.getClass()!=obj.getClass()) return false;

        if(this.idJogo!=((Jogo) obj).idJogo) return false;
        if(this.idDesenvolvedor!=((Jogo) obj).idDesenvolvedor) return false;
        if(this.avaliacao!=((Jogo) obj).avaliacao) return false;
        if(this.vendas!=((Jogo) obj).vendas) return false;
        if(!this.nome.equals(((Jogo) obj).nome)) return false;
        if(this.dataLancamento!=((Jogo) obj).dataLancamento) return false;
        if(this.preco!=((Jogo) obj).preco) return false;

        return true;
    }

    public int hashCode ()
    {
        // Calcula e retorna o hashcode de this:
        int ret = 14;

        ret = 3 * ret + Integer.valueOf(this.idJogo).hashCode();
        ret = 5 * ret + Integer.valueOf(this.idDesenvolvedor).hashCode();
        ret = 7 * ret + Float.valueOf(this.avaliacao).hashCode();
        ret = 11 * ret + Long.valueOf(this.vendas).hashCode();
        ret = 13 * ret + this.nome.hashCode();
        ret = 17 * ret + this.dataLancamento.hashCode();
        ret = 19 * ret + Float.valueOf(this.preco).hashCode();

        if(ret < 0) ret = -ret;

        return ret;
    }

    public Jogo(Jogo copy) throws Exception
    {
        this.setIdJogo          (copy.idJogo);
        this.setIdDesenvolvedor (copy.idDesenvolvedor);
        this.setAvaliacao       (copy.avaliacao);
        this.setVendas          (copy.vendas);
        this.setNome            (copy.nome);
        this.setDataLancamento  (copy.dataLancamento);
        this.setPreco           (copy.preco);
    }

    public Object clone ()
    {
        // Retorna uma c�pia de this:
        try{
            return new Jogo(this);
        }
        catch (Exception e){
            return null;
        }
    }
}

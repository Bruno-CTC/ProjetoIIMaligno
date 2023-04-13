package com.company.classes;

public class Devedor {

    int idDevedor, idade;
    String nome, sexo, cep;
    float divida;

    public Devedor(int idDevedor, int idade, String nome, String sexo, String cep, float divida) throws Exception
    {
        this.setIdDevedor(idDevedor);
        this.setIdade(idade);
        this.setNome(nome);
        this.setSexo(sexo);
        this.setCep(cep);
        this.setDivida(divida);
    }

    public int getIdDevedor() {return this.idDevedor;}
    public int getIdade() {return this.idade;}
    public String getNome() { return this.nome;}
    public String getSexo() {return this.sexo;}
    public String getCep() {return this.cep;}
    public float getDivida() {return this.divida;}
    public void setIdDevedor(int novoId) throws Exception
    {
        if(idDevedor < 0)
            throw new Exception("Id inválido");
        this.idDevedor = novoId;
    }
    public void setIdade(int novaIdade) throws Exception
    {
        if(idade < 0)
            throw new Exception("Idade inválida");
        this.idade = novaIdade;
    }
    public void setNome(String novoNome) throws Exception
    {
        if(novoNome.trim().isEmpty())
            throw new Exception("Nome inválido");
        this.nome = novoNome;
    }
    public void setSexo(String novoSexo) throws Exception
    {
        if(novoSexo.trim().isEmpty())
            throw new Exception("Sexo inválido");
        this.sexo = novoSexo;
    }
    public void setCep(String novoCep) throws Exception
    {
        if(novoCep.trim().isEmpty())
            throw new Exception("Cep inválido");
        this.cep = novoCep;
    }
    public void setDivida(float novaDivida) throws Exception
    {
        if(divida <= 0)
            throw new Exception("Divida inválida");
        this.divida = novaDivida;
    }

    public String toString()
    {
        return idDevedor + " / " +
                nome     + " / " +
                idade    + " / " +
                sexo     + " / " +
                divida   + " / " +
                cep;
    }

    public boolean equals (Object obj)
    {
        if(obj==null) return false;
        if(this==obj) return true;

        if(this.getClass()!=obj.getClass()) return false;

        if(this.idDevedor!=((Devedor) obj).idDevedor) return false;
        if(!this.nome.equals(((Devedor) obj).nome)) return false;
        if(this.idade!=((Devedor) obj).idade) return false;
        if(!this.sexo.equals(((Devedor) obj).sexo)) return false;
        if(this.divida!=((Devedor) obj).divida) return false;
        if(!this.cep.equals(((Devedor) obj).cep)) return false;

        return true;
    }

    public int hashCode ()
    {
        // Calcula e retorna o hashcode de this:
        int ret = 14;

        ret = 5 * ret + Integer.valueOf(this.idDevedor).hashCode();
        ret = 7 * ret + this.nome.hashCode();
        ret = 11 * ret + Integer.valueOf(this.idade).hashCode();
        ret = 13 * ret + this.sexo.hashCode();
        ret = 17 * ret + Float.valueOf(this.divida).hashCode();
        ret = 19 * ret + this.cep.hashCode();

        if(ret < 0) ret = -ret;

        return ret;
    }

    public Devedor(Devedor copy)throws Exception
    {
        this.setIdDevedor (copy.idDevedor);
        this.setNome      (copy.nome);
        this.setIdade     (copy.idade);
        this.setSexo      (copy.sexo);
        this.setDivida    (copy.divida);
        this.setCep       (copy.cep);
    }

    public Object clone ()
    {
        // Retorna uma c�pia de this:
        try{
            return new Devedor(this);
        }
        catch (Exception e){
            return null;
        }
    }
}

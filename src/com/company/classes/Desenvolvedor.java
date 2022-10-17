package com.company.classes;

public class Desenvolvedor
{
    int idDesenvolvedor, horas, idade;
    String nome, empresa;
    float salario;

    public Desenvolvedor(int idDesenvolvedor, int horas, int idade, String nome, String empresa, float salario)throws Exception
    {
        this.setIdDesenvolvedor (idDesenvolvedor);
        this.setHoras           (horas);
        this.setIdade           (idade);
        this.setNome            (nome);
        this.setEmpresa         (empresa);
        this.setSalario         (salario);
    }

    public int getIdDesenvolvedor()
    {
        return idDesenvolvedor;
    }
    public int getHoras()
    {
        return horas;
    }
    public int getIdade()
    {
        return idade;
    }
    public String getNome()
    {
        return nome;
    }
    public String getEmpresa()
    {
        return empresa;
    }
    public float getSalario()
    {
        return salario;
    }

    public void setIdDesenvolvedor(int idDesenvolvedor) throws Exception
    {
        if(idDesenvolvedor < 0)
            throw new Exception("id do desenvolvedor invalido.");

        this.idDesenvolvedor = idDesenvolvedor;
    }
    public void setHoras(int horas) throws Exception
    {
        if(horas < 0)
            throw new Exception("Horas invalida");

        this.horas = horas;
    }
    public void setIdade(int idade) throws Exception
    {
        if(idade<=0)
            throw new Exception("Idade invalida");

        this.idade = idade;
    }
    public void setNome(String nome) throws Exception
    {
        if(nome.trim().isEmpty()) // nome == ""
            throw new Exception("Nome invalido.");

        this.nome = nome;
    }
    public void setEmpresa(String empresa) throws Exception
    {
        if(empresa.trim().isEmpty()) // empresa == ""
            throw new Exception("Nome invalido.");

        this.empresa = empresa;
    }
    public void setSalario(float salario) throws Exception
    {
        if(salario <= 0)
            throw new Exception("Salario invalido");

        this.salario = salario;
    }

    public String toString(){
        return idDesenvolvedor + " / " +
               horas           + " / " +
               idade           + " / " +
               nome            + " / " +
               empresa         + " / " +
               salario;
    }

    public boolean equals (Object obj)
    {
        if(obj==null) return false;
        if(this==obj) return true;

        if(this.getClass()!=obj.getClass()) return false;

        if(this.idDesenvolvedor!=((Desenvolvedor) obj).idDesenvolvedor) return false;
        if(this.horas!=((Desenvolvedor) obj).horas) return false;
        if(this.idade!=((Desenvolvedor) obj).idade) return false;
        if(!this.nome.equals(((Desenvolvedor) obj).nome)) return false;
        if(!this.empresa.equals(((Desenvolvedor) obj).empresa)) return false;
        if(this.salario!=((Desenvolvedor) obj).salario) return false;

        return true;
    }

    public int hashCode ()
    {
        // Calcula e retorna o hashcode de this:
        int ret = 14;

        ret = 5 * ret + Integer.valueOf(this.idDesenvolvedor).hashCode();
        ret = 7 * ret + Integer.valueOf(this.horas).hashCode();
        ret = 11 * ret + Integer.valueOf(this.idade).hashCode();
        ret = 13 * ret + this.nome.hashCode();
        ret = 17 * ret + this.empresa.hashCode();
        ret = 19 * ret + Float.valueOf(this.salario).hashCode();

        if(ret < 0) ret = -ret;

        return ret;
    }

    public Desenvolvedor(Desenvolvedor copy)throws Exception
    {
        this.setIdDesenvolvedor (copy.idDesenvolvedor);
        this.setHoras           (copy.horas);
        this.setIdade           (copy.idade);
        this.setNome            (copy.nome);
        this.setEmpresa         (copy.empresa);
        this.setSalario         (copy.salario);
    }

    public Object clone ()
    {
        // Retorna uma c�pia de this:
        try{
            return new Desenvolvedor(this);
        }
        catch (Exception e){
            return null;
        }
    }
}

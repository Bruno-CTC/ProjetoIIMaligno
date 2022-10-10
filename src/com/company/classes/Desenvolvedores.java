package com.company.classes;

import com.company.sql.BD;
import com.company.sql.MeuResultSet;

import java.sql.SQLException;

public class Desenvolvedores 
{
    // Pesquisa:
    public static boolean cadastrado (int idDesenvolvedor) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM DESENVOLVEDORES " +
                    "WHERE IDDESENVOLVEDOR = ?";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt (1, idDesenvolvedor);

            MeuResultSet resultado = (MeuResultSet)BD.COMANDO.executeQuery ();

            retorno = resultado.first(); // pode-se usar resultado.last() ou resultado.next() ou resultado.previous() ou resultado.absotule(numeroDaLinha)

            /* // ou, se preferirmos,

            String sql;

            sql = "SELECT COUNT(*) AS QUANTOS " +
                  "FROM LIVROS " +
                  "WHERE CODIGO = ?";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BD.COMANDO.executeQuery ();

            resultado.first();

            retorno = resultado.getInt("QUANTOS") != 0;

            */
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar desenvolvedor");
        }

        return retorno;
    }

    // Insert:
    public static void incluir (Desenvolvedor desenvolvedor) throws Exception
    {
        if (desenvolvedor==null)
            throw new Exception ("Desenvolvedor nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO DESENVOLVEDORES " +
                    "(IDDESENVOLVEDOR,HORAS,IDADE,NOME,EMPRESA,SALARIO) " +
                    "VALUES " +
                    "(?,?,?,?,?,?)";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt    (1, desenvolvedor.getIdDesenvolvedor());
            BD.COMANDO.setInt    (2, desenvolvedor.getHoras());
            BD.COMANDO.setInt    (3, desenvolvedor.getIdade());
            BD.COMANDO.setString (4, desenvolvedor.getNome());
            BD.COMANDO.setString (5, desenvolvedor.getEmpresa());
            BD.COMANDO.setFloat  (6, desenvolvedor.getSalario());

            BD.COMANDO.executeUpdate ();
            BD.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BD.COMANDO.rollback();
            throw new Exception ("Erro ao inserir desenvolvedor.");
        }
    }

    // Delete:
    public static void excluir (int idDesenvolvedor) throws Exception
    {
        if (!cadastrado (idDesenvolvedor))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM DESENVOLVEDORES " +
                    "WHERE IDDESENVOLVEDOR=?";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt (1, idDesenvolvedor);

            BD.COMANDO.executeUpdate ();
            BD.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BD.COMANDO.rollback();
            throw new Exception ("Erro ao excluir desenvolvedor");
        }
    }

    // Update:
    public static void alterar (Desenvolvedor desenvolvedor) throws Exception
    {
        if (desenvolvedor==null)
            throw new Exception ("Desenvolvedor nao fornecido");

        if (!cadastrado (desenvolvedor.getIdDesenvolvedor()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE DESENVOLVEDORES " +
                    "SET IDDESENVOLVEDOR=? " +
                    "SET HORAS=? " +
                    "SET IDADE=? " +
                    "SET NOME=? " +
                    "SET EMPRESA=? " +
                    "SET SALARIO=? " +
                    "WHERE IDDESENVOLVEDOR = ?";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt    (1, desenvolvedor.getIdDesenvolvedor());
            BD.COMANDO.setInt    (2, desenvolvedor.getHoras());
            BD.COMANDO.setInt    (3, desenvolvedor.getIdade());
            BD.COMANDO.setString (4, desenvolvedor.getNome());
            BD.COMANDO.setString (5, desenvolvedor.getEmpresa());
            BD.COMANDO.setFloat  (6, desenvolvedor.getSalario());
            BD.COMANDO.setInt    (7, desenvolvedor.getIdDesenvolvedor());

            BD.COMANDO.executeUpdate ();
            BD.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BD.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados do desenvolvedor");
        }
    }

    public static Desenvolvedor getDesenvolvedor (int idDesenvolvedor) throws Exception
    {
        Desenvolvedor desenvolvedor = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM DESENVOLVEDORES " +
                    "WHERE IDDESENVOLVEDOR = ?";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt (1, idDesenvolvedor);

            MeuResultSet resultado = (MeuResultSet)BD.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            desenvolvedor = new Desenvolvedor (resultado.getInt   ("IDDESENVOLVEDOR"),
                    resultado.getInt    ("HORAS"),
                    resultado.getInt    ("IDADE"),
                    resultado.getString ("NOME"),
                    resultado.getString ("EMPRESA"),
                    resultado.getFloat  ("SALARIO"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar desenvolvedor");
        }

        return desenvolvedor;
    }

    public static MeuResultSet getDesenvolvedores () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM DESENVOLVEDORES";

            BD.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BD.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar desenvolvedores");
        }

        return resultado;
    }
}

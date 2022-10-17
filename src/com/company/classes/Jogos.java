package com.company.classes;

import java.sql.*;
import com.company.sql.*;

public class Jogos
{

    // Pesquisa:
    public static boolean cadastrado (int idJogo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM JOGOS.JOGO" +
                    "WHERE IDJOGO = ?";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt (1, idJogo);

            MeuResultSet resultado = (MeuResultSet)BD.COMANDO.executeQuery ();

            retorno = resultado.first();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar jogo");
        }

        return retorno;
    }

    // Insert:
    public static void incluir (Jogo jogo) throws Exception
    {
        if (jogo==null)
            throw new Exception ("Jogo nao fornecido");

        try
        {
            String sql;

            sql = "INSERT INTO JOGOS.JOGO" +
                    "(IDJOGO,IDDESENVOLVEDOR,AVALIACAO,VENDAS,NOME,DATALANCAMENTO,PRECO) " +
                    "VALUES " +
                    "(?,?,?,?,?,?,?)";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt    (1, jogo.getIdJogo());
            BD.COMANDO.setInt    (2, jogo.getIdDesenvolvedor());
            BD.COMANDO.setFloat    (3, jogo.getAvaliacao());
            BD.COMANDO.setLong   (4, jogo.getVendas());
            BD.COMANDO.setString (5, jogo.getNome());
            BD.COMANDO.setDate   (6, new java.sql.Date(jogo.getDataLancamento().getTime()));
            BD.COMANDO.setFloat  (7, jogo.getPreco());

            BD.COMANDO.executeUpdate ();
            BD.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BD.COMANDO.rollback();
            throw new Exception (erro.getMessage());
        }
    }

    // Delete:
    public static void excluir (int idJogo) throws Exception
    {
        if (!cadastrado (idJogo))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM JOGOS.JOGO" +
                    "WHERE IDJOGO=?";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt (1, idJogo);

            BD.COMANDO.executeUpdate ();
            BD.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BD.COMANDO.rollback();
            throw new Exception ("Erro ao excluir jogo");
        }
    }

    // Update:
    public static void alterar (Jogo jogo) throws Exception
    {
        if (jogo==null)
            throw new Exception ("Jogo nao fornecido");

        if (!cadastrado (jogo.getIdJogo()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE JOGOS.JOGO " +
                    "SET IDJOGO=? " +
                    "SET IDDESENVOLVEDOR=? " +
                    "SET AVALIACAO=? " +
                    "SET VENDAS=? " +
                    "SET NOME=? " +
                    "SET DATALANCAMENTO=? " +
                    "SET PRECO=? " +
                    "WHERE IDJOGO = ?";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt    (1, jogo.getIdJogo());
            BD.COMANDO.setInt    (2, jogo.getIdDesenvolvedor());
            BD.COMANDO.setFloat    (3, jogo.getAvaliacao());
            BD.COMANDO.setLong   (4, jogo.getVendas());
            BD.COMANDO.setString (5, jogo.getNome());
            BD.COMANDO.setDate   (6, new java.sql.Date(jogo.getDataLancamento().getTime()));
            BD.COMANDO.setFloat  (7, jogo.getPreco());
            BD.COMANDO.setInt    (8, jogo.getIdJogo());

            BD.COMANDO.executeUpdate ();
            BD.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            BD.COMANDO.rollback();
            throw new Exception ("Erro ao atualizar dados do jogo");
        }
    }

    public static Jogo getJogo (int idJogo) throws Exception
    {
        Jogo jogo = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM JOGOS.JOGO " +
                    "WHERE IDJOGO = ?";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt (1, idJogo);

            MeuResultSet resultado = (MeuResultSet)BD.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            jogo = new Jogo (resultado.getInt   ("IDJOGO"),
                    resultado.getInt    ("IDDESENVOLVEDOR"),
                    resultado.getInt    ("AVALIACAO"),
                    resultado.getLong   ("VENDAS"),
                    resultado.getString ("NOME"),
                    resultado.getDate   ("DATALANCAMENTOS"),
                    resultado.getFloat  ("PRECO"));
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar jogo");
        }

        return jogo;
    }

    public static MeuResultSet getJogos () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                    "FROM JOGOS.JOGO";

            BD.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BD.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar jogos");
        }

        return resultado;
    }
}

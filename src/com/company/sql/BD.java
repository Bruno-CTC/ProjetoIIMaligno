package com.company.sql;

public class BD {
    public static final MeuPreparedStatement COMANDO;

    static
    {
        MeuPreparedStatement comando = null;

        try
        {
            comando =
                    new MeuPreparedStatement (
                            "com.microsoft.sqlserver.jdbc.SQLServerDriver",
                            "jdbc:sqlserver://regulus.cotuca.unicamp.br;databasename=BD22123",
                            "BD22123", "x2f9V4A5");
        }
        catch (Exception erro)
        {
            System.out.println(erro);
            System.err.println (erro.getMessage());
            System.exit(0); // aborta o programa
        }

        COMANDO = comando;
    }
}

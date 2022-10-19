package com.company;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

import com.company.classes.*;
import com.company.sql.BD;

public class Main {
    static JFrame janela; //Cria a janela que vai ter as tabelas, os botões, etc.
    static final Dimension tamanho = new Dimension(1000, 750); //Seta o tamanho dessa janela
    static JButton btnCriar, btnSalvar, btnSair, btnDel; //Cria cada botão (com o nome de sua função)
    static JLabel lbDesenvolvedores, lbJogos;
    static JTable tbDesenvolvedores, tbJogos;
    static int mouseX, mouseY;
    static boolean updating;

    public static ArrayList<Jogo> Games = new ArrayList<>();
    public static ArrayList<Desenvolvedor> Devs = new ArrayList<>();
    public static Queue<String> Comandos = new LinkedList<>();

    static int proxJogo = 0;
    static int proxDev = 0;

    public static void salvar()
    {
            for (String comando : Comandos) {
                try {
                    BD.COMANDO.prepareStatement(comando);
                    BD.COMANDO.executeUpdate();
                    BD.COMANDO.commit();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    try {
                        BD.COMANDO.rollback();
                    }
                    catch (SQLException ex2)
                    {
                        JOptionPane.showMessageDialog(null, ex2.getMessage());
                    }
                }
            }
        JOptionPane.showMessageDialog(null, "Alterações salvas com sucesso!");
        Comandos.clear();
    }
    public static void updateTables()
    {
        updating = true;
        DefaultTableModel model = (DefaultTableModel) tbDesenvolvedores.getModel();
        model.setRowCount(0);
        for (Desenvolvedor dev : Devs) {
            model.addRow(new Object[]{dev.getIdDesenvolvedor(), dev.getNome(), dev.getIdade(), dev.getEmpresa(), dev.getSalario(), dev.getHoras()});
        }
        model = (DefaultTableModel) tbJogos.getModel();
        model.setRowCount(0);
        for (Jogo game : Games) {
            model.addRow(new Object[]{game.getIdJogo(), game.getIdDesenvolvedor(), game.getNome(), game.getDataLancamento(), game.getVendas(), game.getPreco(), game.getAvaliacao()});
        }
        updating = false;
    }
    public static void main(String[] args) {
        Vector<String> nomesVarDev = new Vector<>();
        nomesVarDev.add("ID");
        nomesVarDev.add("Nome");
        nomesVarDev.add("Idade");
        nomesVarDev.add("Empresa");
        nomesVarDev.add("Salário");
        nomesVarDev.add("Horas");
        try{
            var result = Desenvolvedores.getDesenvolvedores();
            while (result.next())
            {
                Devs.add(new Desenvolvedor(result.getInt(1), result.getInt(6), result.getInt(3), result.getString(2), result.getString(4), result.getFloat(5)));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

        Vector<String> nomesVarJogos = new Vector<>();
        nomesVarJogos.add("ID");
        nomesVarJogos.add("ID Dev.");
        nomesVarJogos.add("Nome");
        nomesVarJogos.add("Data Lanc.");
        nomesVarJogos.add("Vendas");
        nomesVarJogos.add("Preço");
        nomesVarJogos.add("Avaliação");
        try{
            var result = Jogos.getJogos();
            while (result.next())
            {
                Games.add(new Jogo(result.getInt(1), result.getInt(2), result.getFloat(7), result.getLong(5), result.getString(3), result.getDate(4), result.getFloat(6)));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

        //Algumas configurações de Janela
        janela = new JFrame("Banco de Dados");
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        janela.setLocation(size.width / 2 - tamanho.width / 2, size.height / 2 - tamanho.height / 2);
        janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        janela.setSize(tamanho);
        janela.setUndecorated(true);
        janela.getRootPane().setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
        janela.setResizable(false);

        JPanel painelArrastavel = new JPanel();
        painelArrastavel.setLayout(null);
        painelArrastavel.setBounds(0, 0, tamanho.width, 42);
        painelArrastavel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                janela.setLocation(janela.getX() + e.getX() - mouseX, janela.getY() + e.getY() - mouseY);
            }
        });
        painelArrastavel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        painelArrastavel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));
        painelArrastavel.setBackground(Color.LIGHT_GRAY);

        btnCriar = new JButton("Novo Dev");
        btnCriar.setBounds(10, tamanho.height - 40, 105, 25);
        janela.add(btnCriar);
        btnCriar.addActionListener(e -> {
            proxDev = 0;
            boolean achouId = false;
            while (!achouId)
            {
                for (Desenvolvedor dev : Devs)
                {
                    if (dev.getIdDesenvolvedor() == proxDev)
                    {
                        proxDev++;
                    }
                    else
                    {
                        achouId = true;
                    }
                }
            }
            new JanelaEditarDesenvolvedor(janela, proxDev);
        });

        btnCriar = new JButton("Novo Jogo");
        btnCriar.setBounds(510, tamanho.height - 40, 105, 25);
        janela.add(btnCriar);
        btnCriar.addActionListener(e -> {
            proxJogo = 0;
            boolean achouIdJogo = false;
            while (!achouIdJogo)
            {
                for (Jogo jogo : Games)
                {
                    if (jogo.getIdJogo() == proxJogo)
                    {
                        proxJogo++;
                    }
                    else
                    {
                        achouIdJogo = true;
                        break;
                    }
                }
            }
            new JanelaEditarJogo(janela, proxJogo);
        });

        btnSalvar = new JButton("Salvar Edição");
        btnSalvar.setBounds(5, 5, 115, 30);
        btnSalvar.addActionListener(e -> {
            salvar();
        });
        painelArrastavel.add(btnSalvar);

        btnDel = new JButton("Deletar Dev");
        btnDel.setBounds(120, tamanho.height - 40, 105, 25);
        btnDel.addActionListener(e -> {
            int index = tbDesenvolvedores.getSelectedRow();
            if (index < 0)
            {
                JOptionPane.showMessageDialog(null, "Selecione um desenvolvedor para deletar!");
                return;
            }
            int id = Integer.parseInt(tbDesenvolvedores.getValueAt(index, 0).toString());
            Comandos.add("DELETE FROM JOGOS.DESENVOLVEDOR WHERE IDDESENVOLVEDOR = " + id);
            Devs.remove(index);
            updateTables();
        });

        janela.add(btnDel);

        btnDel = new JButton("Deletar Jogo");
        btnDel.setBounds(620, tamanho.height - 40, 115, 25);
        btnDel.addActionListener(e -> {
            int index = tbJogos.getSelectedRow();
            if (index < 0)
            {
                JOptionPane.showMessageDialog(null, "Selecione um jogo para deletar!");
                return;
            }
            int id = Integer.parseInt(tbJogos.getValueAt(index, 0).toString());
            Comandos.add("DELETE FROM JOGOS.JOGO WHERE IDJOGO = " + id);
            Games.remove(index);
            updateTables();
        });
        janela.add(btnDel);

        btnSair = new JButton("Sair");
        btnSair.setBounds(tamanho.width - 70, 5, 60, 30);
        btnSair.addActionListener(e -> {
            int res = JOptionPane.showConfirmDialog(null, "Você Tem Alterações não salvas, Deseja salvar e sair?", "Aviso", JOptionPane.YES_NO_CANCEL_OPTION);
            if (res == JOptionPane.NO_OPTION)
            {
                // fecha mas não salva
            }
            else if (res == JOptionPane.CANCEL_OPTION)
            {
                // não fecha nem salva
                return;
            }
            else
            {
                salvar();
            }
            janela.dispatchEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSING));
        });
        painelArrastavel.add(btnSair);

        janela.add(painelArrastavel);

        lbDesenvolvedores = new JLabel("Desenvolvedores", SwingConstants.CENTER);
        lbDesenvolvedores.setFont(new Font("Monospaced", Font.PLAIN, 40));
        lbDesenvolvedores.setBounds(10, 35, 450, 60);
        janela.add(lbDesenvolvedores);
        tbDesenvolvedores = new JTable(new Vector<>(), nomesVarDev);
        DefaultTableCellRenderer centerizarTexto = new DefaultTableCellRenderer();
        centerizarTexto.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < tbDesenvolvedores.getColumnCount(); i++) {
            var col = tbDesenvolvedores.getColumnModel().getColumn(i);
            if (i == 0)
            {
                col.setCellEditor(null);
            }
            col.setCellRenderer(centerizarTexto);
            if (i == 0 || i == 2 || i == 5)
                col.setPreferredWidth(10);
            else if (i == 4) {
                col.setPreferredWidth(50);
            }
        }
        tbDesenvolvedores.getModel().addTableModelListener(e -> {
            if (!updating)
            {
                int id = e.getColumn();
                System.out.println(id);
                String nomeVar = null;
                Desenvolvedor dev = Devs.get(e.getFirstRow());
                switch(id)
                {
                    case 0:
                        nomeVar = "IDDESENVOLVEDOR";
                        try {
                            JOptionPane.showMessageDialog(null, "O ID não pode ser mudado manualmente!");
                            updateTables();
                            return;
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                    case 1:
                        nomeVar = "NOME";
                        try {
                            String nome = (String) tbDesenvolvedores.getValueAt(e.getFirstRow(), id);
                            if (nome.length() > 30)
                            {
                                JOptionPane.showMessageDialog(null, "Nome muito grande!");
                                updateTables();
                                return;
                            }
                            else if(nome.trim().isEmpty())
                            {
                                JOptionPane.showMessageDialog(null, "Não pode ter nome vazio!");
                                updateTables();
                                return;
                            }
                            dev.setNome(nome);
                            Devs.set(e.getFirstRow(), dev);
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                    case 2:
                        nomeVar = "IDADE";
                        try {
                            String texto = (String)tbDesenvolvedores.getValueAt(e.getFirstRow(), id);
                            int valor = -1;
                            try {
                                valor = Integer.parseInt(texto);
                            }
                            catch (Exception ex)
                            {
                                JOptionPane.showMessageDialog(null, "Numero inválido!");
                                updateTables();
                                return;
                            }
                            dev.setIdade(valor);
                            Devs.set(e.getFirstRow(), dev);
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                    case 3:
                        nomeVar = "EMPRESA";
                        try {
                            String empresa = (String) tbDesenvolvedores.getValueAt(e.getFirstRow(), id);
                            if (empresa.length() > 30)
                            {
                                JOptionPane.showMessageDialog(null, "Nome muito grande!");
                                updateTables();
                                return;
                            }
                            else if(empresa.trim().isEmpty())
                            {
                                JOptionPane.showMessageDialog(null, "Não pode ter nome vazio!");
                                updateTables();
                                return;
                            }
                            dev.setEmpresa(empresa);
                            Devs.set(e.getFirstRow(), dev);
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                    case 4:
                        nomeVar = "SALARIO";
                        try {
                            String texto = (String) tbDesenvolvedores.getValueAt(e.getFirstRow(), id);
                            Devs.set(e.getFirstRow(), dev);
                            float valor = -1;
                            try {
                                valor = Float.parseFloat(texto);
                                dev.setSalario(valor);
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Numero inválido!");
                                updateTables();
                                return;
                            }
                            dev.setSalario(valor);
                            Devs.set(e.getFirstRow(), dev);
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                    case 5:
                        nomeVar = "HORAS";
                        try {
                            String texto = (String)tbDesenvolvedores.getValueAt(e.getFirstRow(), id);
                            int valor = -1;
                            try {
                                valor = Integer.parseInt(texto);
                            }
                            catch (Exception ex)
                            {
                                JOptionPane.showMessageDialog(null, "Numero inválido!");
                                updateTables();
                                return;
                            }
                            dev.setIdDesenvolvedor(valor);
                            Devs.set(e.getFirstRow(), dev);
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                }
                String sql = "UPDATE JOGOS.DESENVOLVEDOR SET " + nomeVar + " = '" + tbDesenvolvedores.getModel().getValueAt(e.getFirstRow(), e.getColumn()) + "' WHERE IDDESENVOLVEDOR = " + tbDesenvolvedores.getModel().getValueAt(e.getFirstRow(), 0);
                Comandos.add(sql);
            }
        });
        tbDesenvolvedores.setBounds(10, 90, tamanho.width - 35 - tamanho.width / 2, tamanho.height - 130);
        JScrollPane pane = new JScrollPane(tbDesenvolvedores);
        pane.setBounds(tbDesenvolvedores.getBounds());
        janela.add(pane, BorderLayout.CENTER);

        lbJogos = new JLabel("Jogos", SwingConstants.CENTER);
        lbJogos.setFont(new Font("Monospaced", Font.PLAIN, 40));
        lbJogos.setBounds(550, 35, 400, 60);
        janela.add(lbJogos);

        tbJogos = new JTable(new Vector<>(), nomesVarJogos);
        for (int i = 0; i < tbJogos.getColumnCount(); i++) {
            var col = tbJogos.getColumnModel().getColumn(i);
            col.setCellRenderer(centerizarTexto);
            if (i == 0)
                col.setPreferredWidth(15);
            else if (i == 1)
                col.setPreferredWidth(60);
            else if (i == 2)
                col.setPreferredWidth(120);
            else if (i == 4)
                col.setPreferredWidth(50);
            else if (i == 5)
                col.setPreferredWidth(40);
            else if (i == 6)
                col.setPreferredWidth(50);
        }
        tbJogos.setBounds(10 + tamanho.width / 2, 90, tamanho.width - 25 - tamanho.width / 2, tamanho.height - 130);
        JScrollPane pane2 = new JScrollPane(tbJogos);
        pane2.setBounds(tbJogos.getBounds());
        janela.add(pane2, BorderLayout.CENTER);

        tbJogos.getModel().addTableModelListener(e -> {
            if (!updating)
            {
                int id = e.getColumn();
                String nomeVar = null;
                Jogo jogo = Games.get(e.getFirstRow());
                switch(id)
                {
                    case 0:
                        nomeVar = "IDJOGO";
                        try {
                            JOptionPane.showMessageDialog(null, "O ID não pode ser mudado manualmente!");
                            updateTables();
                            return;
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                    case 1:
                        String texto = (String)tbJogos.getValueAt(e.getFirstRow(), id);
                        int idDev = -1;
                        try
                        {
                            idDev = Integer.parseInt(texto);
                        }
                        catch(Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, "Numero invalido!");
                            updateTables();
                            return;
                        }
                        nomeVar = "IDDESENVOLVEDOR";
                        boolean valido = false;
                        for (Desenvolvedor dev : Devs)
                        {
                            if (idDev == dev.getIdDesenvolvedor())
                            {
                                valido = true;
                                break;
                            }
                        }
                        if (!valido)
                        {
                            JOptionPane.showMessageDialog(null, "Numero de desenvolvedor inválido");
                            updateTables();
                            return;
                        }
                        try {
                            jogo.setIdDesenvolvedor(idDev);
                            Games.set(e.getFirstRow(), jogo);
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                    case 2:
                        nomeVar = "NOME";
                        try {
                            String nome = (String)tbJogos.getValueAt(e.getFirstRow(), id);
                            if (nome.length() > 50)
                            {
                                JOptionPane.showMessageDialog(null, "Nome muito grande!");
                                updateTables();
                                return;
                            }
                            else if(nome.trim().isEmpty())
                            {
                                JOptionPane.showMessageDialog(null, "Não pode ter nome vazio!");
                                updateTables();
                                return;
                            }
                            jogo.setNome(nome);
                            Games.set(e.getFirstRow(), jogo);
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                    case 3:
                        nomeVar = "DATALANCAMENTO";
                        try {
                            jogo.setDataLancamento(Date.valueOf((String)tbJogos.getValueAt(e.getFirstRow(), id)));
                            Games.set(e.getFirstRow(), jogo);
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, "Data inválida!");
                            updateTables();
                            return;
                        }
                        break;
                    case 4:
                        nomeVar = "VENDAS";
                        try {
                            String texto2 = (String)tbJogos.getValueAt(e.getFirstRow(), id);
                            long valor = -1;
                            try {
                                valor = Long.parseLong(texto2);
                            }
                            catch (Exception ex)
                            {
                                JOptionPane.showMessageDialog(null, "Numero inválido!");
                                updateTables();
                                return;
                            }
                            jogo.setVendas(valor);
                            Games.set(e.getFirstRow(), jogo);
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                    case 5:
                        nomeVar = "PRECO";
                        try {
                            String texto3 = (String)tbJogos.getValueAt(e.getFirstRow(), id);
                            float valor = -1;
                            try {
                                valor = Float.parseFloat(texto3);
                            }
                            catch (Exception ex)
                            {
                                JOptionPane.showMessageDialog(null, "Numero inválido!");
                                updateTables();
                                return;
                            }
                            jogo.setPreco(valor);
                            Games.set(e.getFirstRow(), jogo);
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                    case 6:
                        nomeVar = "AVALIACAO";
                        try {
                            String texto4 = (String)tbJogos.getValueAt(e.getFirstRow(), id);
                            float valor = -1;
                            try {
                                valor = Float.parseFloat(texto4);
                            }
                            catch (Exception ex)
                            {
                                JOptionPane.showMessageDialog(null, "Numero inválido!");
                                updateTables();
                                return;
                            }
                            jogo.setAvaliacao(valor);
                            Games.set(e.getFirstRow(), jogo);
                        }
                        catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        }
                        break;
                }
                String sql = "UPDATE JOGOS.JOGO SET " + nomeVar + " = '" + tbJogos.getModel().getValueAt(e.getFirstRow(), e.getColumn()) + "' WHERE IDJOGO = " + tbJogos.getModel().getValueAt(e.getFirstRow(), 0);
                Comandos.add(sql);
            }
        });

        updateTables();

        janela.setLayout(null);
        janela.setVisible(true);
        boolean loop = true;
        while (loop)
        {
            try {
                Thread.sleep(1000 / 60);
            }
            catch (Exception ex)
            {
                loop = false;
            }
        }
    }
}

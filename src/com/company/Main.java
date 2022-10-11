package com.company;

import com.company.sql.BD;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.Vector;

import com.company.classes.*;

public class Main {
    static JFrame janela;
    static final Dimension tamanho = new Dimension(1000, 750);
    static JButton btnCriar, btnEditar, btnSalvar, btnSair;
    static JLabel lbDesenvolvedores, lbJogos;
    static JTable tbDesenvolvedores, tbJogos;
    static int mouseX, mouseY;

    public static void main(String[] args) {
        Vector<Vector<Object>> desenvolvedores = new Vector<>();
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
                Vector<Object> vecDev = new Vector<>();
                vecDev.add(result.getInt(1));
                vecDev.add(result.getString(2));
                vecDev.add(result.getInt(3));
                vecDev.add(result.getString(4));
                vecDev.add(result.getDouble(5));
                vecDev.add(result.getInt(6));
                desenvolvedores.add(vecDev);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

        Vector<Vector<Object>> jogos = new Vector<>();
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
                Vector<Object> vecJogo = new Vector<>();
                vecJogo.add(result.getInt(1));
                vecJogo.add(result.getInt(2));
                vecJogo.add(result.getString(3));
                vecJogo.add(result.getDate(4));
                vecJogo.add(result.getLong(5));
                vecJogo.add(result.getFloat(6));
                vecJogo.add(result.getFloat(7));
                jogos.add(vecJogo);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

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

        btnCriar = new JButton("Novo Jogo");
        btnCriar.setBounds(510, tamanho.height - 40, 105, 25);
        janela.add(btnCriar);
        //painelArrastavel.add(btnCriar);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(5, 5, 95, 30);
        painelArrastavel.add(btnEditar);

        btnSair = new JButton("Sair");
        btnSair.setBounds(tamanho.width - 70, 5, 60, 30);
        btnSair.addActionListener(e -> janela.dispatchEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSING)));
        painelArrastavel.add(btnSair);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(110, 5, 95, 30);
        painelArrastavel.add(btnSalvar);

        janela.add(painelArrastavel);

        lbDesenvolvedores = new JLabel("Desenvolvedores", SwingConstants.CENTER);
        lbDesenvolvedores.setFont(new Font("Monospaced", Font.PLAIN, 40));
        lbDesenvolvedores.setBounds(10, 35, 450, 60);
        janela.add(lbDesenvolvedores);
        tbDesenvolvedores = new JTable(desenvolvedores, nomesVarDev);
        DefaultTableCellRenderer centerizarTexto = new DefaultTableCellRenderer();
        centerizarTexto.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tbDesenvolvedores.getColumnCount(); i++) {
            var col = tbDesenvolvedores.getColumnModel().getColumn(i);
            col.setCellRenderer(centerizarTexto);
            if (i == 0 || i == 2 || i == 5)
                col.setPreferredWidth(10);
            else if (i == 4) {
                col.setPreferredWidth(50);
            }
        }
        tbDesenvolvedores.setBounds(10, 90, tamanho.width - 35 - tamanho.width / 2, tamanho.height - 130);
        JScrollPane pane = new JScrollPane(tbDesenvolvedores);
        pane.setBounds(tbDesenvolvedores.getBounds());
        janela.add(pane, BorderLayout.CENTER);

        lbJogos = new JLabel("Jogos", SwingConstants.CENTER);
        lbJogos.setFont(new Font("Monospaced", Font.PLAIN, 40));
        lbJogos.setBounds(550, 35, 400, 60);
        janela.add(lbJogos);

        tbJogos = new JTable(jogos, nomesVarJogos);
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

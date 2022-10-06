package com.company;

import com.company.sql.BD;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    static JFrame janela;
    static final Dimension tamanho = new Dimension(1000, 750);
    static final Object[][] desenvolvedores = new Object[][]{{0, "Zeca Urubu", 69, "CU Corp.", String.format("%.2f", 30000.00), 13},
            {1, "Renato", 34, "Epic Games", String.format("%.2f", 1500.00), 84}},
            jogos = new Object[][]{{0, 0, "Zecacraft", "30/02/2022", "8K", String.format("%.2f", 69.00), 5},
                    {1, 0, "Pica's Adventure", "11/09/2030", 0, String.format("%.2f", 300.00), "Indefinido"},
                    {2, 1, "Enforquenite", "01/09/1923", "1M", String.format("%.2f", 0.01), 4.5},
                    {3, 1, "Blox Fruits", "02/06/2020", "8.3B", String.format("%.2f", 0.00), 4.3}};
    static final String[] nomesVarDev = new String[]{"ID", "Nome", "Idade", "Empresa", "Salário", "Horas"},
            nomesVarJogos = new String[]{"ID", "ID Dev", "Nome", "Lança Em", "Vendas", "Preço", "Avaliação"};
    static JButton btnCriar, btnEditar, btnSalvar, btnSair;
    static JLabel lbDesenvolvedores, lbJogos;
    static JTable tbDesenvolvedores, tbJogos;
    static int mouseX, mouseY;

    public static void main(String[] args) {
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

        btnCriar = new JButton("Novo");
        btnCriar.setBounds(5, 5, 95, 30);
        painelArrastavel.add(btnCriar);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(110, 5, 95, 30);
        painelArrastavel.add(btnEditar);

        btnSair = new JButton("Sair");
        btnSair.setBounds(tamanho.width - 70, 5, 60, 30);
        btnSair.addActionListener(e -> janela.dispatchEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSING)));
        painelArrastavel.add(btnSair);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(215, 5, 95, 30);
        painelArrastavel.add(btnSalvar);

        janela.add(painelArrastavel);

        lbDesenvolvedores = new JLabel("Desenvolvedores", SwingConstants.CENTER);
        lbDesenvolvedores.setFont(new Font("Monospaced", Font.PLAIN, 40));
        lbDesenvolvedores.setBounds(10, 35, 450, 60);
        janela.add(lbDesenvolvedores);

        tbDesenvolvedores = new JTable(desenvolvedores, nomesVarDev);
        tbDesenvolvedores.setDefaultEditor(Object.class, null);
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
        tbJogos.setDefaultEditor(Object.class, null);
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
    }
}

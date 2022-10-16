package com.company;

import javax.swing.*;

public class JanelaEditarJogo extends JFrame {
    public JanelaEditarJogo(JFrame parent) {
        super("Criar");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(parent);
        setLayout(null);
        setResizable(false);
        setVisible(true);

        JLabel lbNome = new JLabel("Nome", SwingConstants.CENTER);
        lbNome.setBounds(10, 10, 465, 30);
        add(lbNome);
        JTextField txtNome = new JTextField("");
        txtNome.setBounds(10, 40, 465, 30);
        add(txtNome);

        JLabel lbIDDev = new JLabel("ID Desenvolvedor", SwingConstants.CENTER);
        lbIDDev.setBounds(10, 80, 465, 30);
        add(lbIDDev);
        JTextField txtIDDev = new JTextField("");
        txtIDDev.setBounds(10, 110, 465, 30);
        add(txtIDDev);

        JLabel lbVendas = new JLabel("Vendas", SwingConstants.CENTER);
        lbVendas.setBounds(10, 150, 465, 30);
        add(lbVendas);
        JTextField txtVendas = new JTextField("");
        txtVendas.setBounds(10, 180, 465, 30);
        add(txtVendas);

        JLabel lbPreco = new JLabel("Preço", SwingConstants.CENTER);
        lbPreco.setBounds(10, 220, 465, 30);
        add(lbPreco);
        JTextField txtPreco = new JTextField("");
        txtPreco.setBounds(10, 250, 465, 30);
        add(txtPreco);

        JLabel lbData = new JLabel("Data de Lançamento", SwingConstants.CENTER);
        lbData.setBounds(10, 290, 465, 30);
        add(lbData);
        JTextField txtData = new JTextField("");
        txtData.setBounds(10, 320, 465, 30);
        add(txtData);

        JLabel lbAvaliacao = new JLabel("Avaliação", SwingConstants.CENTER);
        lbAvaliacao.setBounds(10, 360, 465, 30);
        add(lbAvaliacao);
        JTextField txtAvaliacao = new JTextField("");
        txtAvaliacao.setBounds(10, 390, 465, 30);
        add(txtAvaliacao);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(200, 425, 100, 30);
        add(btnSalvar);
    }
}

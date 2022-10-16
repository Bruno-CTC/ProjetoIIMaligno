package com.company;

import javax.swing.*;

public class JanelaEditarDesenvolvedor extends JFrame {
    public JanelaEditarDesenvolvedor(JFrame parent) {
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

        JLabel lbIdade = new JLabel("Idade", SwingConstants.CENTER);
        lbIdade.setBounds(10, 80, 465, 30);
        add(lbIdade);
        JTextField txtIdade = new JTextField("");
        txtIdade.setBounds(10, 110, 465, 30);
        add(txtIdade);

        JLabel lbEmpresa = new JLabel("Empresa", SwingConstants.CENTER);
        lbEmpresa.setBounds(10, 150, 465, 30);
        add(lbEmpresa);
        JTextField txtEmpresa = new JTextField("");
        txtEmpresa.setBounds(10, 180, 465, 30);
        add(txtEmpresa);

        JLabel lbSalario = new JLabel("Salario", SwingConstants.CENTER);
        lbSalario.setBounds(10, 220, 465, 30);
        add(lbSalario);
        JTextField txtSalario = new JTextField("");
        txtSalario.setBounds(10, 250, 465, 30);
        add(txtSalario);

        JLabel lbHoras = new JLabel("Horas", SwingConstants.CENTER);
        lbHoras.setBounds(10, 290, 465, 30);
        add(lbHoras);
        JTextField txtHoras = new JTextField("");
        txtHoras.setBounds(10, 320, 465, 30);
        add(txtHoras);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(200, 425, 100, 30);
        add(btnSalvar);
    }
}
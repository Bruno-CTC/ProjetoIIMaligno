package com.company;

import com.company.classes.*;
import com.company.sql.BD;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class JanelaEditarDesenvolvedor extends JFrame {
    int horas;
    int idade;
    String nome;
    String empresa;
    float salario;
    public JanelaEditarDesenvolvedor(JFrame parent, int idDev) {
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
        btnSalvar.addActionListener(e -> {
            horas = Integer.parseInt(txtHoras.getText());
            idade = Integer.parseInt(txtIdade.getText());
            nome = txtNome.getText();
            empresa = txtEmpresa.getText();
            salario = Float.parseFloat(txtSalario.getText());
            try {
                var novoDev = new Desenvolvedor(idDev, horas, idade, nome, empresa, salario);
                String sql = "INSERT INTO JOGOS.DESENVOLVEDOR " +
                            "(IDDESENVOLVEDOR,HORAS,IDADE,NOME,EMPRESA,SALARIO) " +
                            "VALUES " +
                            String.format("(%o,%o,%o,'%s','%s',", idDev, horas, idade, nome, empresa) +
                            salario +
                            ");";
                Main.Comandos.add(sql);
                Main.Devs.add(novoDev);
                Main.updateTables();

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            dispose();
        })
    ;}
}

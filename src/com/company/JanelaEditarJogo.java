package com.company;

import javax.swing.*;
import java.sql.Date;
import java.util.concurrent.atomic.AtomicReference;

import com.company.classes.*;

public class JanelaEditarJogo extends JFrame {
    int idDev;
    String nome;
    long vendas;
    float preco;
    float aval;
    public JanelaEditarJogo(JFrame parent, int idJogo) {
        super("Criar");
        if (Main.Devs.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nenhum desenvolvedor cadastrado.");
            return;
        }
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
        JComboBox cbDev = new JComboBox();
        cbDev.setBounds(10, 110, 465, 30);
        for (Desenvolvedor dev : Main.Devs) {
            cbDev.addItem(dev.getNome());
        }
        add(cbDev);

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
        btnSalvar.addActionListener(e -> {
            idDev = cbDev.getSelectedIndex();
            nome = txtNome.getText();
            String[] msg = txtData.getText().split("-");
            Date data = null;
            try{
                data = Date.valueOf(txtData.getText());
                /*data = Date.valueOf(
                        String.format("%s-%s-%s", msg[2], msg[1], msg[0])
                );*/
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
            vendas = Long.parseLong(txtVendas.getText());
            preco = Float.parseFloat(txtPreco.getText());
            aval = Float.parseFloat(txtAvaliacao.getText());
            try{
                var novoJogo = new Jogo(idJogo, idDev, aval, vendas, nome, data, preco);
                String sql = "INSERT INTO JOGOS.JOGO" +
                        "(IDJOGO,IDDESENVOLVEDOR,AVALIACAO,VENDAS,NOME,DATALANCAMENTO,PRECO) " +
                        "VALUES " +
                        String.format("(%o,%o," + aval, idJogo, idDev) +
                        String.format(",%o,'%s','" +
                                data +
                                "'," +
                                preco +
                                ");", vendas, nome);
                Main.Comandos.add(sql);
                Main.Games.add(novoJogo);
                Main.updateTables();
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
            dispose();
        });
    }
}

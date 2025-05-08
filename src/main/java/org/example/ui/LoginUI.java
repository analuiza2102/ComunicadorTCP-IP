package org.example.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame {
    private JTextField nomeField;
    private JTextField ipField;
    private JButton conectarButton;
    private JLabel logoLabel;

    public LoginUI() {
        setTitle("Login - Sistema de Comunicação");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(0, 51, 0)); // Verde escuro similar ao modelo

        // Logo
        logoLabel = new JLabel(new ImageIcon("src/main/resources/Logo_sema.jpg"));
        logoLabel.setBounds(170, 20, 150, 150);
        mainPanel.add(logoLabel);

        // Título
        JLabel titleLabel = new JLabel("Secretaria de Estado do Meio Ambiente");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(90, 180, 350, 30);
        mainPanel.add(titleLabel);

        // Nome do Inspetor
        JLabel nomeLabel = new JLabel("Nome Inspetor");
        nomeLabel.setForeground(Color.WHITE);
        nomeLabel.setBounds(150, 230, 200, 20);
        mainPanel.add(nomeLabel);

        nomeField = new JTextField();
        nomeField.setBounds(150, 250, 200, 30);
        mainPanel.add(nomeField);

        // IP do Servidor
        JLabel ipLabel = new JLabel("Servidor");
        ipLabel.setForeground(Color.WHITE);
        ipLabel.setBounds(150, 290, 200, 20);
        mainPanel.add(ipLabel);

        ipField = new JTextField("localhost");
        ipField.setBounds(150, 310, 200, 30);
        mainPanel.add(ipField);

        // Botão Conectar
        conectarButton = new JButton("Conectar");
        conectarButton.setBackground(new Color(200, 200, 200));
        conectarButton.setBounds(200, 370, 100, 40);
        mainPanel.add(conectarButton);

        add(mainPanel, BorderLayout.CENTER);

        conectarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String ip = ipField.getText();
                if (!nome.isEmpty() && !ip.isEmpty()) {
                    new ChatUI(nome, ip).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginUI login = new LoginUI();
            login.setVisible(true);
        });
    }
}
package org.example.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ChatUI extends JFrame {
    private JTextArea chatArea;
    private JTextField messageField;
    private JButton sendButton;
    private JButton switchUserButton;
    private JButton exitButton;
    private PrintWriter out;
    private JList<String> userList;
    private DefaultListModel<String> userModel;
    private Set<String> users;
    private String nome;

    public ChatUI(String nome, String ip) {
        this.nome = nome;
        setTitle("Chat - " + nome);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Área de Chat
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        // Campo de mensagem
        messageField = new JTextField();
        sendButton = new JButton("Enviar");

        // Botões de acessibilidade
        switchUserButton = new JButton("Trocar Usuário");
        exitButton = new JButton("Sair");

        // Painel de envio
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(messageField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(switchUserButton);
        buttonPanel.add(exitButton);

        // Lista de usuários conectados
        userModel = new DefaultListModel<>();
        userList = new JList<>(userModel);
        users = new HashSet<>();

        // Adiciona o usuário que está logado na lista
        users.add(nome);
        userModel.addElement(nome);

        JPanel sidePanel = new JPanel(new BorderLayout());
        sidePanel.add(new JLabel("Usuários Conectados"), BorderLayout.NORTH);
        sidePanel.add(new JScrollPane(userList), BorderLayout.CENTER);
        sidePanel.add(buttonPanel, BorderLayout.SOUTH);

        add(new JScrollPane(chatArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        add(sidePanel, BorderLayout.EAST);

        // Conectar ao servidor
        try {
            Socket socket = new Socket(ip, 12345);
            out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Enviar nome para identificação
            out.println(nome + " entrou no chat.");

            // Thread para ouvir mensagens do servidor
            new Thread(() -> {
                String mensagemRecebida;
                try {
                    while ((mensagemRecebida = in.readLine()) != null) {
                        // Exibir mensagens recebidas, incluindo as do próprio usuário
                        chatArea.append(mensagemRecebida + "\n");
                        chatArea.setCaretPosition(chatArea.getDocument().getLength());

                        // Verificar se é uma mensagem de entrada de usuário
                        if (mensagemRecebida.contains("entrou no chat")) {
                            String userName = mensagemRecebida.split(" ")[0];
                            if (!users.contains(userName) && !userName.startsWith("[")) {
                                users.add(userName);
                                userModel.addElement(userName);
                            }
                        }
                    }
                } catch (Exception e) {
                    chatArea.append("Conexão encerrada.\n");
                }
            }).start();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao servidor.");
            dispose();
        }

        // Ação do botão enviar
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensagem = messageField.getText();
                if (!mensagem.isEmpty()) {
                    // Adicionar o horário no formato [HH:mm]
                    String formattedMessage = nome + ": " + mensagem;

                    // Enviar para o servidor
                    out.println(formattedMessage);

                    // NÃO EXIBE NA TELA DO PRÓPRIO USUÁRIO (somente retorna do servidor)
                    messageField.setText("");
                }
            }
        });

        // Ação do botão trocar usuário
        switchUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginUI().setVisible(true);
                dispose();
            }
        });

        // Ação do botão sair
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    out.println(nome + " saiu do chat.");
                    dispose();
                } catch (Exception ex) {
                    System.out.println("Erro ao sair do chat: " + ex.getMessage());
                }
            }
        });
    }
}

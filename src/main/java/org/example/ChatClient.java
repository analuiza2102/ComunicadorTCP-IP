package org.example;

import java.io.*;
import java.net.*;

public class ChatClient {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public void iniciar() {
        try (
                Socket socket = new Socket(SERVER_IP, SERVER_PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader console = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.print("Seu nome: ");
            String nome = console.readLine();

            new Thread(() -> {
                String mensagemRecebida;
                try {
                    while ((mensagemRecebida = in.readLine()) != null) {
                        System.out.println(mensagemRecebida);
                    }
                } catch (IOException e) {
                    System.out.println("Conexão encerrada.");
                }
            }).start();

            String mensagem;
            while ((mensagem = console.readLine()) != null) {
                out.println(nome + ": " + mensagem);
            }

        } catch (IOException e) {
            System.err.println("Erro ao conectar com o servidor: " + e.getMessage());
        }
    }
}

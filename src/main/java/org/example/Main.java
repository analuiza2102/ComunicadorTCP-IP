package org.example;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Sistema de Comunicação da Secretaria do Meio Ambiente ===");
        System.out.println("1 - Iniciar Servidor");
        System.out.println("2 - Conectar como Cliente");
        System.out.print("Escolha uma opção: ");

        Scanner scanner = new Scanner(System.in);
        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                ChatServer servidor = new ChatServer();
                servidor.iniciar();
                break;
            case 2:
                ChatClient cliente = new ChatClient();
                cliente.iniciar();
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
}
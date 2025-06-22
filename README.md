# 🛰️ ComunicadorTCPJ-IP

Aplicação Java baseada em TCP/IP que permite comunicação em tempo real entre múltiplos clientes e um servidor. Desenvolvida para auxiliar a Secretaria de Estado do Meio Ambiente no monitoramento de atividades ao longo do Rio Tietê.

---

## 📌 Objetivo

Permitir que inspetores em campo troquem mensagens com a central de comando de forma **segura, eficiente e em tempo real**.

---

## 🛠️ Tecnologias Utilizadas

- **Java 8+**
- **Sockets (TCP/IP)** – `java.net.Socket` e `java.net.ServerSocket`
- **Java Swing** – Interface gráfica
- **Multithreading** – Suporte a múltiplos clientes simultaneamente

---


---

## 🚀 Como Executar

### 🔧 Pré-requisitos

- JDK 8 ou superior instalado
- IDE como VSCode, IntelliJ ou NetBeans (ou terminal)

### 🔹 Rodar o Servidor
javac src/servidor/main.java

### 🔹 Rodar o Cliente
javac src/cliente/ChatUI.java

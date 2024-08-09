# PDF Merge Application

## Descrição

A **PDF Merge Application** é uma aplicação web que permite aos usuários mesclar vários arquivos PDF em um único arquivo. A aplicação oferece funcionalidades como nomear o arquivo de saída, e manter um histórico das mesclagens realizadas, associando cada uma ao e-mail do usuário.

## Tecnologias Utilizadas

- **Backend:**
  - [Java Spring Boot](https://spring.io/projects/spring-boot)
  - [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
  - [MySQL](https://www.mysql.com/)
  
- **Frontend:**
  - [React](https://reactjs.org/)
  - [Vite.js](https://vitejs.dev/)
  
- **Outras Dependências:**
  - [Axios](https://axios-http.com/) para requisições HTTP no frontend
  - [Lombok](https://projectlombok.org/) para reduzir boilerplate no código Java

## Funcionalidades

- **Upload de PDFs:** Permite o upload de múltiplos arquivos PDF.
- **Nomeação de Arquivo:** O usuário pode definir um nome personalizado para o arquivo PDF mesclado.
- **Histórico de Mesclagem:** O sistema armazena o histórico de mesclagens associando o e-mail do usuário e o nome do arquivo.

## Pré-requisitos

- **Java 17**
- **Maven 3.8+**
- **Node.js 18+**
- **MySQL Server**

## Configuração do Backend

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/pdf-merge-application.git
    cd pdf-merge-application
    ```

2. Configure o banco de dados MySQL:
    ```sql
    CREATE DATABASE pdf_merge;
    ```

3. Configure as credenciais do banco de dados no arquivo `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/pdf_merge
    spring.datasource.username=seu-usuario
    spring.datasource.password=sua-senha
    ```
4. Configure a URL do CorsConfig
    ```
    .allowedOrigins("http://localhost:Porta Gerada no 'vite run dev' ") // URL do frontend
    ```
5. Compile e execute a aplicação Spring Boot:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## Configuração do Frontend

1. Navegue até a pasta do frontend:
    ```bash
    cd pdf-merge-frontend
    ```

2. Instale as dependências:
    ```bash
    npm install
    ```

3. Execute a aplicação React:
    ```bash
    npm run dev
    ```

4. Acesse a aplicação no navegador em `http://localhost:portageradanoVite`.

## Uso

1. **Upload de PDFs:** Na interface principal, clique no botão de upload e selecione os arquivos PDF que deseja mesclar.
2. **Nomeação:** Insira um nome para o arquivo mesclado.
3. **E-mail:** Forneça um endereço de e-mail para associar a mesclagem.
4. **Mesclar:** Clique em "Merge PDFs" para mesclar os arquivos. Após a mesclagem, um link para download será exibido.
5. **Histórico:** Acesse a seção de histórico para visualizar as mesclagens anteriores.

## Estrutura do Projeto

- **backend/**
  - Contém todo o código do servidor, incluindo os controladores, serviços, e entidades.
  
- **frontend/**
  - Contém o código React para a interface do usuário.

## Contribuindo

Sinta-se à vontade para abrir issues e pull requests para melhorias e correções.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

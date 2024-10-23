
# DAC API Car - Projeto Acadêmico

## Descrição

Este projeto é uma API REST desenvolvida para a disciplina de **Desenvolvimento de Aplicações Corporativas (DAC)**, utilizando **Spring Boot** e **PostgreSQL**. O foco principal é o **CRUD de usuários e carros**, além de outras funcionalidades como autenticação com JWT, envio de e-mail, geração de relatórios e QR Codes.

O projeto segue uma arquitetura **MVC + Service + Repository**, e foi versionado utilizando **Git**, mantendo uma organização clara das branches para cada sprint e feature desenvolvida.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **PostgreSQL**
- **Spring Data JPA**
- **Spring Security com JWT**
- **Flyway (para migrações de banco de dados)**
- **Swagger (para documentação da API)**
- **QRCode Generator**
- **Maven**

## Estrutura do Projeto

A estrutura do projeto foi organizada com base em sprints e features, seguindo o fluxo de **Git Flow**. Abaixo está a explicação das principais branches:

### Branches Principais

- **`main`**: Contém a versão estável e final do projeto.
- **`develop`**: Branch de desenvolvimento contínuo. Todas as sprints e features são eventualmente mescladas aqui.

### Sprints e Features

#### Sprint 1
- **`sprint1`**: Foco na configuração inicial do projeto e nas funcionalidades de CRUD de usuários e carros, além de autenticação básica.
   - **`feature-s1-project-setup`**: Configuração inicial do projeto Spring Boot e PostgreSQL.
   - **`feature-s1-car-management`**: CRUD de Carros (criação, edição, listagem e exclusão de carros).
   - **`feature-s1-user-management`**: CRUD de Usuários (criação, edição, listagem e exclusão de usuários).
   - **`feature-s1-authentication`**: Implementação da autenticação via JWT (JSON Web Token) e proteção de endpoints.

#### Sprint 2
- **`sprint2`**: Foco nas funcionalidades avançadas, como envio de e-mails, geração de relatórios e QR Codes.
   - **`feature-s2-email-notification`**: Envio de e-mails de notificação ao cadastro de usuários.
   - **`feature-s2-password-encryption`**: Criptografia de senhas usando BCrypt.
   - **`feature-s2-report-generation`**: Geração de relatórios em PDF/CSV com informações dos carros.
   - **`feature-s2-qrcode-generation`**: Geração de QR Codes com informações de carros e usuários.

## Como Executar o Projeto

### 1. Clonar o Repositório
```bash
git clone https://github.com/seu-usuario/dac-api-car.git
cd dac-api-car
```

### 2. Configurar o Banco de Dados
Certifique-se de que o **PostgreSQL** está rodando e que os **schemas** `desenvolvimento` e `homologacao` estão criados. As configurações do banco de dados variam conforme o perfil ativo, e podem ser ajustadas nos arquivos `application.properties` e `application-homolog.properties`.

Exemplo de configuração do arquivo `application-homolog.properties`:
```properties
# Configurações do Banco de Dados - Homologação
spring.datasource.url=jdbc:postgresql://localhost:5432/dac-api-car
spring.datasource.username=dac
spring.datasource.password=123456
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.default_schema=homologacao

# Flyway Configurações
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true
spring.flyway.locations=classpath:db/migration
spring.flyway.schemas=homologacao
```

### 3. Rodar a Aplicação

Para rodar em **desenvolvimento**:
```bash
mvn spring-boot:run
```

Para rodar em **homologação**:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=homolog
```

### 4. Acessar a Documentação da API (Swagger)
Após iniciar o servidor, acesse o Swagger para visualizar e testar os endpoints da API:

```bash
http://localhost:8080/swagger-ui.html
```

## Testando a Autenticação JWT

### 1. Fazer Login com um Usuário Existente

Para acessar os endpoints protegidos da API, é necessário realizar autenticação. Um exemplo de usuário existente no banco de dados pode ser:

```json
{
  "email": "carlos.cardoso@example.com",
  "password": "Rafi!@#!"
}
```

Use este JSON para fazer login no endpoint `/api/auth/login` e receber o token JWT.

#### Exemplo de Requisição (Login):
Endpoint: `/api/auth/login`  
Método: `POST`

```json
{
  "email": "carlos.cardoso@example.com",
  "password": "Rafi!@#!"
}
```

### 2. Receber o Token JWT

Após o login, você receberá um token JWT na resposta. Exemplo de resposta:

```json
{
  "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjYXJsb3MuY2FyZG9zbyIsImV4cCI6MTYzNzUyMjYwMH0.MfpjDbXmdKmftqzFIuzovcjk0o1SaEV1n94RQNu2bgc"
}
```

### 3. Autorizar no Swagger

No Swagger, clique no botão **Authorize** e cole o token JWT no campo de autorização, com o formato:

```
Bearer <seu-token-aqui>
```

### 4. Criar um Novo Usuário

Agora, com o token de **Carlos Cardoso** (ou outro usuário com permissão de **PRIVILEGE**), você pode testar a criação de um novo usuário, enviando uma requisição para o endpoint `/api/users`:

#### Exemplo de JSON para criar um novo usuário:
```json
{
  "name": "João Souza",
  "email": "joao.souza@example.com",
  "password": "SenhaSegura!23",
  "phoneNumber": "(11)99999-1234",
  "birthDate": "1990-05-10",
  "cpf": "12345678900",
  "typeUser": "PRIVILEGE"
}
```

## Organização do Controle de Versão

O projeto foi versionado utilizando **Git Flow**, e todas as branches de feature foram mantidas no repositório para fins de documentação e acompanhamento do desenvolvimento. As branches principais e suas respectivas features seguem o seguinte fluxo:

- Cada funcionalidade foi desenvolvida em uma **feature branch**.
- Após finalização, a **feature** foi mesclada com a branch de **sprint**.
- As **sprints** foram mescladas com a branch **develop**.

Nenhuma branch foi deletada para que todo o histórico de desenvolvimento estivesse disponível.

## Como Contribuir

Este é um projeto acadêmico, portanto, contribuições externas não são esperadas. Entretanto, qualquer feedback ou sugestão será bem-vindo!

## Licença

Este projeto é para fins acadêmicos e não está licenciado para uso comercial.

---

Agora, o `README.md` contém todas as instruções detalhadas sobre como rodar a aplicação, configurar o banco de dados, acessar o Swagger, realizar login com JWT, e usar o token para acessar endpoints protegidos. Boa sorte com o seu projeto! 👍
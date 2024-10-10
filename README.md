Claro! Vamos criar um **README.md** bem estruturado para o seu projeto, explicando a organização das **branches**, o fluxo de trabalho, e um resumo sobre as funcionalidades implementadas.

### README.md

```markdown
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

1. **Clonar o Repositório**:
   ```bash
   git clone https://github.com/seu-usuario/dac-api-car.git
   ```

2. **Configurar o Banco de Dados**:
   Certifique-se de que o PostgreSQL está rodando e que os schemas `desenvolvimento` e `homologacao` estão criados. As configurações do banco estão no arquivo `application.properties` e variam conforme o perfil ativo.

3. **Rodar a Aplicação**:
   Para rodar em **desenvolvimento**:
   ```bash
   mvn spring-boot:run
   ```

   Para rodar em **homologação**:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=homolog
   ```

4. **Acessar a Documentação da API** (Swagger):
   Após iniciar o servidor, acesse o **Swagger** para visualizar os endpoints da API:
   ```
   http://localhost:8080/swagger-ui.html
   ```

## Organização do Controle de Versão

O projeto foi versionado utilizando **Git Flow**, e todas as branches de feature foram mantidas no repositório para fins de documentação e acompanhamento do desenvolvimento. As branches principais e suas respectivas features seguem o seguinte fluxo:

1. Cada funcionalidade foi desenvolvida em uma **feature branch**.
2. Após finalização, a feature foi mesclada com a branch de **sprint**.
3. As sprints, por sua vez, foram mescladas com a branch **develop**.

Nenhuma branch foi deletada para que todo o histórico de desenvolvimento estivesse disponível.

## Como Contribuir

Este é um projeto acadêmico, portanto, contribuições externas não são esperadas. Entretanto, qualquer feedback ou sugestão será bem-vindo!

## Licença

Este projeto é para fins acadêmicos e não está licenciado para uso comercial.
```

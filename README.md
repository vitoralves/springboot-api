[![Build Status](https://travis-ci.org/vitoralves/springboot-api.svg?branch=master)](https://travis-ci.org/vitoralves/springboot-api)

# Springboot RESTful API
API responsável por manipular clientes com os atributos nome e idade e através do IP do usuário que fizer uma requisição para inserir um novo cliente salvar a temperatura máxima e mínima da localização mais próxima do usuário, utilizando as api's abertas https://www.ipvigilante.com/ e https://www.metaweather.com/api/

### Detalhes do projeto
O projeto possui as seguintes características:
* Projeto criado com Spring Boot e Java 8
* Banco de dados Postgres com JPA e Spring Data JPA
* Versionamento de banco de dados com Flyway
* Testes com JUnit e Mockito com banco H2 em memória
* Caching com EhCache
* Nuvem do Heroku
* Integração contínua com TravisCI
* Project Lombok
* Documentação dos endpoints com Swagger
* Segurança da API com autenticação via tokens JWT
### Como executar a aplicação
Certifique-se de ter o Maven instalado e adicionado ao PATH de seu sistema operacional, assim como o Git, crie um banco de dados no postgres e altere o arquivo application.properties informando as credenciais para o aplicação acessar a base de dados, não se preocupe com a criação das tabelas, o flyway se encarregará dessa função.
```
git clone https://github.com/vitoralves/springboot-api.git
cd SpringbootApi
mvn spring-boot:run
Acesse os endpoints através da url http://localhost:8080
```

Também é possível compilar o projeto para executar em um ambiente de produção, para isso execute o seguinte comando na raiz do projeto

```
mvn clean install
```

O pacote será gerado dentro da pasta target, basta executá-lo com o comando abaixo, não esquecendo de configurar qual o profile e a porta que a aplicação deverá utilizar.

```
java -jar -Dspring.profiles.active=prod -Dserver.port=443 SpringbootApi-0.0.1-SNAPSHOT.jar
```
### Como autenticar na API
Antes de fazer requisições nos endpoint você deve estar autenticado, para esse projeto não foi criado nenhum controle de usuário, já existe um usuário de testes previamente cadastrado.
Para autenticar faça uma requisição POST na rota /auth com os dados:

```
{
  "usuario": "admin",
  "senha": "123"
}
```

Você receberá um token do tipo Bearer que deve ser enviado no cabeçalho das requisições.
### Como executar os testes
Os testes podem ser executados com o seguinte comando:

```
mvn test
```
### Documentação
Utilize a interface do Swagger para ter acesso a documentação dos endpoints, ela está disponível na url http://localhost:8080/swagger-ui.html
### Heroku
A aplicação está disponível no heroku através da url https://apispringboot.herokuapp.com/

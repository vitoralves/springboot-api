# Springboot RESTful API
API responsável por manipular clientes com os atributos nome e idade e através do IP do usuário que fizer uma requisição para inserir um novo cliente salvar a temperatura máxima e mínima da localização mais próxima do usuário, utilizando as api's abertas https://www.ipvigilante.com/ e https://www.metaweather.com/api/

### Detalhes do projeto
O projeto possui as seguintes características:
* Projeto criado com Spring Boot e Java 8
* Banco de dados Postgres com JPA e Spring Data JPA
* Versionamento de banco de dados com Flyway
* Testes com JUnit e Mockito
* Caching com EhCache
* Nuvem do Heroku
* Integração contínua com TravisCI
### Como executar a aplicação
Certifique-se de ter o Maven instalado e adicionado ao PATH de seu sistema operacional, assim como o Git.
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
### Documentação
Utilize a interface do Swagger para ter acesso a documentação dos endpoints, ela está disponível na url http://localhost:8080/swagger-ui.html
### Heroku
A aplicação está disponível no heroku através da url https://apispringboot.herokuapp.com/

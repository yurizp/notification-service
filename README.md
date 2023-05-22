# notification-service

### Consulte a Wiki deste projeto para mais informações.

## [Visitar](https://git.vibbra.com.br/yuri-1684287759/notification-service/-/wikis/home)


## Tecnologias utilizadas

- Java 19
- Spring Boot 3.0.6
- Gradle
- Lombok
- JUnit 5
- spotless
- Flyway

## Documentação dos contratos de API`s 
O projeto conta com um Swagger, então a documentação das respostas e requests estão lá.
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Executando o projeto

#### Docker
* Você deve ter instalado em sua maquina o docker compose.
* Caso você tenha um mysql server instalado em sua maquina você deve parar o serviço pois ele ira conflitar com as portas do container do mysq.

Na raiz do projeto você deve executar ```docker-compose up```.  Assim que estiver de pé o projeto ele estara disponivel no endereço [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

#### Outras formas
* Caso queira executar o projeto por algum editor você deve ter um banco de dados Mysql para o qual apontar.
* O projeto esta com as seguintes configurações padrões:
> host: jdbc:mysql://127.0.0.1:3306/notification?createDatabaseIfNotExist=true <p>
> user: root <p>
> pass: password

É possivel sobreescrever essas configurações alterando direto no arquivo `application.yaml` ou setando as variaveis de ambiente:

> DB_NAME = O host do banco <p>  
> DB_USER = user do banco <p>  
> DB_PASS = senha do banco  

#### Banco de dados

O projeto esta configurado para sempre que iniciar executar as Migrations, ou seja, sempra que o projeto for executado ele ira executar os scripts dentro de src/main/resources/db/migration/. 

Esses scripts precisam estar no padrão "versão"+"__"+"titulo" exemplo V002__add_table_address.sql

O banco escolhido foi o MySQL, devido a versão do Spring o banco deve ser posterior a versão 8.0.0
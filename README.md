
# Avaliador de Crédito
  ⚠️ Projeto em andamento. Ainda esta sendo implementado os testes de unidade, possiveis melhorias e porteriormente dockerizado e disponibilizado a execução do mesmo. ⚠️
## Sobre o projeto
Este projeto será implementado por meio da arquitetura de microsserviços, visando simular o processo de avaliação de crédito realizado por instituições bancárias, bem como a emissão de cartões de crédito com base nos resultados dessa avaliação. Como um projeto voltado para fins de estudos, seu principal objetivo é aplicar e consolidar os conhecimentos adquiridos nas seguintes áreas : <br><br>
✔️ Testes unitarios com Junit <br>
✔️ Mensageria com RabbitMQ <br>
✔️ Arquitetura de microsserviços <br>
✔️ Refatoração de código <br>
✔️ Versionamento do repositorio com github <br>
✔️ Desenvolvimento com Sprinboot <br>
✔️ Docker <br>
✔️ Empacotar e gerar imagens<br>
✔️ Parametrizar dados<br>
<br><br>

# Ilustração da arquitetura do projeto rodando em containers docker

![image](https://github.com/im2back/Project-MicroserviceArchitecture/assets/117541466/32164ea1-41f8-49dc-bb3a-14c403376a1d)
<br><br><br>
# Arquitetura sendo executada em containers docker
![image](https://github.com/im2back/Project-MicroserviceArchitecture/assets/117541466/5906ab58-b1bf-4360-8b1d-30fa39ba77d2)
<br><br><br>
# Servidor Eureka (Discovery server & Load balance)
![image](https://github.com/im2back/Project-MicroserviceArchitecture/assets/117541466/04781203-0485-4401-acdf-a3f50c74887f)
<br><br><br>
# Imagens geradas apartir das API'S
![image](https://github.com/im2back/Project-MicroserviceArchitecture/assets/117541466/0881a384-85f6-44d4-a03a-a32dc6466e28)




# Tecnologias utilizadas
## Back end
- Linguagem : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Java-blue.svg?style=flat&logo=coffeescript&logoColor=white" target="_blank"></a>
- Framework : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/SpringBoot-white.svg?style=flat&logo=springboot&logoColor=green" target="_blank"></a>
- Gerenciador de dependências : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Maven-white.svg?style=flat&logo=apachemaven&logoColor=darkgreen" target="_blank"></a>
- Orquestração em container : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Docker-white.svg?style=flat&logo=docker&logoColor=blue" target="_blank"></a>
- Segurança : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/Keycloak-gray.svg?style=flat&logo=springsecurity&logoColor=blue" target="_blank"></a>

## Front end (Testar Requisições)
- <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/PostMan-white.svg?style=flat&logo=postman&logoColor=red" target="_blank"></a>

## Persistencia de dados : 
- Banco de dados : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/MySQL-blue.svg?style=flat&logo=mysql&logoColor=white" target="_blank"></a>
- Mapeamento ORM : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/JPA-Hibernate-darkgreen.svg?style=flat&logo=hibernate&logoColor=white" target="_blank"></a>
- Versionamento do banco de dados : <a href="" target="_blank"><img loading="lazy" src="https://img.shields.io/badge/FlyWay-white.svg?style=flat&logo=flyway&logoColor=red" target="_blank"></a>

# Como executar o projeto (EM BREVE)
Pré-requisitos: <br> 
```Pré-requisitos:
✔️ Java 17 
✔️ My-Sql CE : 8.0.31 
✔️ Docker 
✔️ Postman ou Insomnia 
✔️ IDE STS 4 (ou outra de sua preferência) 
```
<br><br><br>

 ### Criar o banco de dados:
```database


✅ Criar uma instância do MySql Através do docker
👉 Poweshell:
docker run --name creddit-database -p 3306:3306 --network creditappraiser-network -e MYSQL_ROOT_PASSWORD=Rtyfghvbn1* = -d mysql:8.0.31

#Feito isso, um usuário com as seguintes credenciais será criado:
👤 Login: root
🔑 Senha: Rtyfghvbn1*

Obs 1 : Essas credênciais são compativeis com as credênciais configuradas no arquivo application.properties do projeto.
Obs 2 : Verificar se a porta 3306 está disponivel par a que não haja conflito.


✅ Agora vamos criar as databases
Logando no banco de dados:
👉 Poweshell:
mysql -u root -p
Digitar a senha : Rtyfghvbn1*

#Após logar basta executar os comandos abaixo e as databases serão criadas
CREATE DATABASE  clientes_ms;
CREATE DATABASE  cards_ms;
```
<br><br>

 ### Criar a instancia do RabbitMq:
```rabbitmq
✅ Criar uma instância do RabbitMq Através do docker
👉 Poweshell:
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management

# O usário e senhas padrão do RabbitMq já estão configurados no nosso projeto.
# O serviço responsavel por enviar a menssagem já está configurado para criar a fila.
```

 ### Criar a instancia do KeyCloak:
```rabbitmq
✅ Criar uma instância do KeyCloak Através do docker
👉 Poweshell:
docker run -p 8085:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:23.0.6 start-dev

#Feito isso, um usuário com as seguintes credenciais será criado:
👤 Login: admin
🔑 Senha: admin

#Acessar a url http://localhost:8085/ e logar.

✅ Criando o Realm 
Baixar o realm : https://drive.google.com/file/d/18ENDGxyyLMPgfQ0vgcM1RYj6RUhKlpy1/view?usp=sharing

- Acessar o dashboard do keycloak utilizando o login e senha.
- Após entrar no dash board, clicar na aba de realms onde estará selecionado o realm "master" [master ] 🔻
- Importar o arquivo Realm.json que foi baixado anteriormente  e clicar em [CREATE]

```

 ### Clonar o repositório e executar o projeto:
```project 
✅ Clonar repositório

✅ Executar o projeto
Entrar na pasta do projeto clonado
👉 Poweshell:
cd "PATH_DA_PASTA_RAIZ_PROJETO"

./mvnw spring-boot:run
```



# Autor
Jefferson Richards Sena de Souza

https://www.linkedin.com/in/jefferson-richards-sena-de-souza-4110a3222/

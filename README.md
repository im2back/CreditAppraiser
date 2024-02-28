
# Avaliador de Crédito
   🏁 O projeto ja foi finalizado, porém ainda esta sujeito a possiveis melhorias no código. Obrigado pela atenção !  👋😁👍
## Sobre o projeto
Este projeto será implementado por meio da arquitetura de microsserviços, visando simular o processo de avaliação de crédito realizado por instituições bancárias, bem como a emissão de cartões de crédito com base nos resultados dessa avaliação. Como um projeto voltado para fins de estudos, seu principal objetivo é aplicar e consolidar os conhecimentos adquiridos nas seguintes áreas : <br><br>

1. **Arquitetura de Microsserviços**: Detalhamento da estrutura de microsserviços adotada no projeto.<br><br>
2. **Testes**: <br>
   ▪️ **Testes Unitários**: Implementação e execução de testes unitários. <br> 
   ▪️ **Testes Automatizados**: Desenvolvimento de testes automatizados para garantir a qualidade do software.<br><br>
3. **Comunicação Assíncrona**: Uso do RabbitMQ para gerenciar a comunicação assíncrona entre os serviços.<br><br>
4. **Configuração dos Perfis de Ambiente**: Definição e configuração dos perfis de ambiente, como desenvolvimento e produção.<br><br>
5. **Versionamento do Banco de Dados**: Utilização do Flyway para o controle de versão do banco de dados.<br><br>
6. **Documentação da API**: Criação da documentação da API para facilitar o entendimento e uso por desenvolvedores externos.<br><br>
7. **Versionamento do Repositório Remoto**: Uso do GitHub para versionamento e colaboração no código do projeto.<br><br>
8. **Dockerização do Projeto**:<br>
   ▪️ **Build do Projeto Utilizando Dockerfile**: Construção do projeto com Docker para facilitar a implantação e execução em diferentes ambientes.<br>
   ▪️ **Geração de Imagem Docker**: Processo de criação de uma imagem Docker do projeto.<br>
   ▪️ **Criação do Container**: Instruções para criar um container a partir da imagem Docker gerada.<br>
   ▪️ **Orquestração de Containers**: Uso de ferramentas de orquestração para gerenciar múltiplos containers, melhorando a escalabilidade e a disponibilidade.<br>
   ▪️ **Comunicação entre Containers**: Configuração da rede para permitir a comunicação entre os containers.<br><br>
9. **Parametrização de Dados**: Técnicas utilizadas para parametrizar dados, facilitando a customização e configuração do projeto.<br><br>
10. **Segurança da API**: Implementação de medidas de segurança para a API, utilizando o Keycloak para autenticação e autorização.<br><br>
11. **Refatoração de Código**: Processo contínuo de melhoria do código para aumentar a legibilidade e a manutenção.<br><br>
12. **Banco de Dados**: Especificações e configurações do banco de dados utilizado no projeto.<br><br>
13. **Tratamento de Exceções**:<br>
    ▪️ **Lançamento de Exceções Personalizadas**: Criação e uso de exceções personalizadas para um melhor controle de erros.

#Esta estrutura oferece uma visão clara e abrangente dos tópicos abordados no projeto.

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
# Imagens das API'S
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

<br><br><br>

# Como executar o projeto
Pré-requisitos: <br> 
```Pré-requisitos:
✔️ Java 17 
✔️ My-Sql CE : 8.0.31 
✔️ Docker 
✔️ Postman 
✔️ IDE STS 4 (ou outra de sua preferência) 
```
### Links úteis :
- Documentação : https://drive.google.com/drive/folders/1oiZqfiQwWUTxqzzlT8svdhSSuGQ_h79H?usp=sharing
- Realm : https://drive.google.com/file/d/18ENDGxyyLMPgfQ0vgcM1RYj6RUhKlpy1/view?usp=sharing

<br><br>

 ### Criar o banco de dados:
```database


✅ Criar uma instância do MySql Através do docker
👉 Poweshell:
docker run --name creddit-database -p 3306:3306 --network creditappraiser-network -e MYSQL_ROOT_PASSWORD=Rtyfghvbn1* -d mysql:8.0.31

#Feito isso, um usuário com as seguintes credenciais será criado:
👤 Login: root
🔑 Senha: Rtyfghvbn1*

Obs➀ : Essas credênciais são compativeis com as credênciais configuradas no arquivo application.properties do projeto.
Obs➁ : Verificar se a porta 3306 está disponivel par a que não haja conflito.


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

 ### Criar a instância do RabbitMq:
```rabbitmq
✅ Criar uma instância do RabbitMq Através do docker
👉 Poweshell:
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management


# O usário e senhas padrão do RabbitMq já estão configurados no nosso projeto.
# O serviço responsavel por enviar a menssagem já está configurado para criar a fila.
```
<br><br>

 ### Criar a instância do KeyCloak:
```keycloak
✅ Criar uma instância do KeyCloak Através do docker
👉 Poweshell:
docker run -p 8085:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:23.0.6 start-dev

#Feito isso, um usuário com as seguintes credenciais será criado:
👤 Login: admin
🔑 Senha: admin

#Acessar a url http://localhost:8085/ e logar.

✅ Criando o Realm 
Baixar o realm : Acessar os links úteis e baixar o arquivo json contendo o realm.

- Acessar o dashboard do keycloak utilizando o login e senha.
- Após entrar no dash board, clicar na aba de realms onde estará selecionado o realm "master" [master ] 🔻
- Importar o arquivo Realm.json que foi baixado anteriormente  e clicar em [CREATE]

```
<br><br>

 ### Clonar o repositório e executar o projeto:
```project 
✅ Clonar repositório

✅ Executar o projeto
Entrar na pasta do projeto clonado
👉 Poweshell:
cd "PATH_DA_PASTA_RAIZ_PROJETO"

./mvnw spring-boot:run
```
<br><br>

 ### Disparando requisições com o postman
```project

✅ Obter o "Client Secret" do seu keycloak
- Fazer login e acessar o DashBoard do keycloak
- Selecionar o seu Realm [msbankrealm ] 🔻

Agora para obter seu "secret", siga as etapas abaixo:

1. Primeiramente, clique na aba "Clients".
2. Em seguida, na coluna "Client ID", localize e clique em "mscredit".
3. Após isso, selecione a aba "Credentials".
4. Procure por "Client Secret" e clique em "REGENERATE".
5. Por fim, copie o código que foi gerado. Ele será usado posteriormente para obter o token, quando estivermos utilizando o postman.

🏁 Agora basta  baixar a documentação disponibilizada no link acima. Depois de baixar é só importar no postman, gerar um novo token e passa-lo nas requisições :

```

# Executando o projeto em containers
```
✅Criar a Network Docker
Crie a rede Docker chamada creditappraiser-network para facilitar a comunicação entre os contêineres.

bash
Copy code
docker network create creditappraiser-network

✅Construir as Imagens dos Microserviços
Navegue até a pasta de cada microserviço. Cada microserviço está configurado com seu próprio Dockerfile, então você precisa construir as imagens Docker correspondentes. Execute o comando abaixo em cada pasta, substituindo NOME_DA_IMAGEM pelo nome do microserviço. Use os seguintes nomes para as imagens, que são consistentes com os comandos subsequentes:

ms-creditappraiser
ms-cards
gateway
clients-ms
eureka

bash
Copy code
docker build --build-arg JAR_FILE=target/*.jar -t NOME_DA_IMAGEM/image:ultimate .

Observação: Verifique se todas as portas no localhost necessárias para expor os contêineres estão disponíveis.

✅Criar os Contêineres
OBS: Criar o keycloak de acordo com a explicação anterior sobre o mesmo e adicionar o seu keycloak a network -->  docker network connect creditappraiser-network keycloak 


✔️Crie o contêiner RabbitMQ para gestão de mensagens:

bash
Copy code
docker run -it --rm --name rabbitmq --network creditappraiser-network -p 5672:5672 -p 15672:15672 rabbitmq:3.12-management


✔️Crie o contêiner MySQL para o banco de dados, substituindo a senha conforme necessário:

bash
Copy code
docker run --name creddit-database -p 3306:3306 --network creditappraiser-network -e MYSQL_ROOT_PASSWORD=Rtyfghvbn1* -d mysql:8.0.31


✔️Crie os contêineres para cada microserviço usando os comandos abaixo. 

bash
Copy code
docker run --name ms-creditappraiser --network creditappraiser-network -e EUREKA_SERVER=eureka -e RABBIT_MQ=rabbitmq -d ms-creditappraiser/image:ultimate

docker run --name ms-cards --network creditappraiser-network -e EUREKA_SERVER=eureka -e DATABASE_SERVER=creddit-database -e RABBIT_MQ=rabbitmq -d ms-cards/image:ultimate

docker run --name gateway -p 8080:8080 --network creditappraiser-network -e EUREKA_SERVER=eureka -e KEYCLOAK_SERVER=keycloak -e KEYCLOAK_PORT=8080 -d gateway/image:ultimate

docker run --name ms-clients --network creditappraiser-network -e EUREKA_SERVER=eureka -e DATABASE_SERVER=creddit-database -d clients-ms/image:ultimate

docker run --name eureka -p 8081:8081 --network creditappraiser-network -e EUREKA_SERVER=eureka -d eureka/image:ultimate

###Lembre-se de que todos os contêineres devem fazer parte da mesma rede (creditappraiser-network).

✔️Configurar a Front-URL do Keycloak
Atualize a front-url do Keycloak para apontar para o endereço do contêiner, garantindo a correta integração de autenticação:
Basta acessar o dashboard do keycloak e alterar a fronturl

http://keycloak:8080
´´´





# Autor
Jefferson Richards Sena de Souza

https://www.linkedin.com/in/jefferson-richards-sena-de-souza-4110a3222/

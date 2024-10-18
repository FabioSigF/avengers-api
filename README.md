
# API Avengers - Arquitetura Hexagonal

Esse é um projeto desenvolvido inicialmente durante o curso de **Arquitetura Hexagonal** da Digital Inovation One, com o objetivo de *colocar em prática os conhecimentos sobre o assunto*.

A aplicação possui 3 camadas:

- **Application**: onde ficam as configurações do projeto, controllers, dtos, errors handlers, etc. É a camada que vai ter acesso à tecnologia.
- **Domain**: onde ficam as entidades de domínio, regras de negócio, portas e services.
- **Resource**: implementa interfaces, se tornando um adaptador (proxy). É onde fica a JPA Repository e entidades mapeadas com anotações de acordo com tecnologia de banco de dados escolhida.

A partir da API, é possível criar, editar e remover Avengers.


## Tecnologias utilizadas

- Java 21
- Spring Framework
    - JPA
    - Hibernate
    - Lombok
    - Validation
- FlyWay (Migrations)
- PostgreSQL (DB)
- Docker (Container)
- Swagger (Documentação)
## Instalação

1. Clone o repositório na sua máquina local:

`https://github.com/FabioSigF/avengers-api`

2. É necessário ter o Docker instalado para criação de containers do pgAdmin e postgres.

https://www.docker.com/products/docker-desktop/

3. Faça alterações no arquivo docker/.env para colocar as configurações de preferência do banco de dados:

```.env
DB_USER=postgres
DB_PASSWORD=admin
DB_NAME=avengers

PGADMIN_DEFAULT_EMAIL=avengers@email.com
PGADMIN_DEFAULT_PASSWORD=123456
```

4. Inicialize o .yaml na pasta do docker com os seguinte comando:

```bash
cd docker
docker-compose -f .\avenger-api-resources.yaml up -d
```

5. Configure as variáveis de ambiente no IntelliJ. 

Em Run, na barra superior da ferramenta, clique em *Edit Configurations* e *Environment Variables*.

Você deve adicionar os mesmos valores de configuração do docker/.env:

```
DB_USER=postgres
DB_PASSWORD=admin
DB_NAME=avengers
```

6. Agora, basta rodar a aplicação.


## Documentação da API

#### Retorna todos os Avengers

```http
  GET /avengers/api
```

#### Retorna um Avenger por ID

```http
  GET /avengers/api/${id}/detail
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. O ID do Avenger que você quer |

#### Cria um novo Avenger

```http
  POST /avengers/api
```

Cada instância de Avenger deve possui um nome único. Nomes (nick) repetidos não são aceitos na API. Deve ser enviado um body com os dados do Avenger.

Exemplo de Body:
```json
{
    "nick": "Homem de Ferro",
    "person": "Tony Stark",
    "description": "É o homem de ferro",
    "history": "Herdou o império de tecnologia do seu pai. Desde cedo, era extremamente inteligente e, a pós ser sequestrado, construiu a primeira versão do que seria mais tarde conhecida como a armadura do Homem de Ferro."
}
```
**Obrigatório**: "nick" e "person".

#### Atualiza um Avenger por ID

```http
  PUT /avengers/api/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. O ID do Avenger que você quer atualizar |

Precisa de um Body com pelo menos um campo que será atualizado.

Exemplo:
```json
{
    "nick": "Homem de Ferro",
}
```
Se o "nick" já existir em outro personagem, a requisição será negada.


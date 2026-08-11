# Concessionária API

## Tecnologias usadas

- Java 21
- Spring Boot 4.0.7
- Spring Data JPA (Hibernate)
- MySQL
- Lombok (pra não precisar escrever getter/setter na mão)
- springdoc-openapi (gera a documentação Swagger automaticamente)
- Maven

## Estrutura do projeto

```
src/main/java/com/concessionaria
 config/       -> configuração do Swagger/OpenAPI
 controller/   -> endpoints da API (Carro, Cliente, Negociação)
 model/        -> entidades JPA (Carro, Cliente, Condicao, Disponibilidade)
 repository/   -> interfaces JpaRepository de acesso ao banco
```

## Como rodar o projeto

### Pré-requisitos
- Java 21 instalado
- MySQL rodando na sua máquina (ou em container)

### Passo a passo

1. O projeto cria o banco de dados sozinho, então n precisa se preocupar
2. Verifique o nome e senha do banco (por padrão está `root` / `root`).
3. Rode o projeto com o Maven Wrapper, direto na pasta do projeto:
```bash
./mvnw spring-boot:run
```

4. acessar: `http://localhost:8080`.
5. Swagger: `http://localhost:8080/swagger-ui.html`

## Endpoints principais

### Carro (`/carro`)
- /carro/cadastrar - cadastra carro novo (post)
- /carro/todos - lista todos os carros (get)
- /carro/{id} - busca pelo id (get)
- /carro/{id} - atualiza o carro (put)
- /carro/{id} - exclui carro (delete)

### Cliente (`/cliente`)
- /cliente/cadastrar - cadastra cliente novo (post)
- /cliente/todos - lista todos os clientes (get)
- /cliente/{id} - busca pelo id (get)
- /cliente/{id} - atualiza o cliente (put)
- /cliente/{id} - exclui cliente (delete)

# Decisões

`cliente`
Telefone, email, cpf precisam ser um por cliente (Unique), para não haver conflito na hora do contato. (Nenhum pode ser nulo (Null)).\
`carro` Chassi e placa são únicos (Unique). Quilometragem e placa são os únicos que podem ser nulos (Null), já que o carro chega sem placa e pode ser um carro zero, sem nenhum km rodado.

## Tipos de variável
### Carro
`id` é aumentado automaticamente com `GenerationType.IDENTITY`\
`condicao` e `disponibilidade` são `Enums`, já que são opções fixas da loja.\
`anoModelo` e `anoFabrica` são `Integers`, já que só precisamos do ano e não de uma data completa.\
`preco` é `Big Decimal`, tipo padrão para números quebrados.\
`marca`, `modelo`, `cor`, `placa` e `chassi` são `Strings` pois são definidos muitas vezes pelas fabricantes, então podem variar. Já o chassi, como não é necessário nenhum calculo com ele, mantive como String para facilitar.

### Cliente
`id` é aumentado automaticamente com `GenerationType.IDENTITY`\
`nome`, `telefone` e `email` são Strings.
# Validador de Senha
Este projeto foi desenvolvido baseado na descrição presente no link : https://github.com/itidigital/backend-challenge.

## Solução
### Stack
Este projeto foi desenvolvido utilizando:
* Kotlin;
* Spring Boot 2.5.3;
* WebFlux;
* Mockito;

### Estrutura do Projeto

```
...
└── challenge
    ├── ChallengeApplication.kt
    ├── controllers
    │   ├── dto
    │   │   └── PasswordValidationDTO.kt
    │   └── SecurityController.kt
    └── services
        ├── ports
        │   ├── PasswordValidationService.kt
        │   └── SecurityService.kt
        ├── PasswordValidationServiceImpl.kt
        └── SecurityServiceImpl.kt
```
Foi escolhido separar as camadas de "lógica de segurança", e validação da senha, para que, caso seja necessário modificar a estratégia de validação de senha, ou integrar diferentes estratégias, consigamos atender as necessidades, sem que seja necessário revisitar a solução como um todo. Também optamos por utilizar interfaces entre as camadas de services e controller, e entre os próprios services, para que a real implementação do código não seja levada em consideração durante a execução, sendo possível abstrairmos o "como", e levarmos em consideração somente o contrato estabelecido.

### Execução
#### Executando o projeto
Para executar o projeto:
1. Baixe o projeto;
2. Entre na raiz do projeto;
3. Execute ```./gradlew bootRun```;
4. Espere até que a mensagem ```Netty started on port 8080``` apareça;

#### Exemplos de chamadas
Uma vez finalizada a etapa de execução do projeto, é possível interagir com o serviço desenvolvido com chamadas no formato:

```curl -i --request POST --location 'http://localhost:8080/security/password/validate' \```
```--data-raw '{"password":"AbTp9!fok"}' -H 'Content-Type: application/json'```

Finalizada a chamada, deve ser visualizada a resposta como ```true``` ou ```false```, os quais indicarão caso a senha fornecida está de acordo com a regex
```^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{9,}$```.

## Próximos passsos
* Incluir ```@RefreshScope```, para que possamos modificar a regex utilizada durante runtime;
* Incluir Swagger para clareza sobre os endpoints disponíveis;
* Possível definição de um response no formato JSON, para facilitar a integração de clients;
* Explorar os tratamentos de cenários de exceção;

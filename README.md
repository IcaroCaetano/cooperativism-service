# Introdução 

Este micro-serviço tem por finalidade simular uma sessao de votação de pautas.
Onde primeiro são cadastrados os Associados;
Depois são cadastradas as Pautas. 
No processo deve ser aberta uma sessão onde deve ser informado uma das pautas criadas.
Com a sessão aberta, onde tem um tempo de duração especificado no momento da criacao da sessão. Se não for informado
o tempo padrão será de 1 minuto.

Então para vota dever ser informado a Sessão e SIM ou NAO para Votação.

Os votos srão contabilizados por Pauta. E exibidos em um dos endpoints.

# Acessando a Aplicação

Esta aplicação foi disponibilzada na nuvem no endereço:

[Cooperativism-Service](http://my-project-cooperativismo.ue.r.appspot.com/swagger-ui/index.html) - https://my-project-cooperativismo.ue.r.appspot.com/swagger-ui/index.html


[CADASTRAR ASSOCIADO](http://my-project-cooperativismo.ue.r.appspot.com/swagger-ui/index.html#/Associado/create_1) - https://my-project-cooperativismo.ue.r.appspot.com/swagger-ui/index.html#/Associado/create_1

[CADASTRAR PAUTA](http://my-project-cooperativismo.ue.r.appspot.com/swagger-ui/index.html#/Pauta/create_2) - https://my-project-cooperativismo.ue.r.appspot.com/swagger-ui/index.html#/Pauta/create_2

[ABRIR SESSÃO](http://my-project-cooperativismo.ue.r.appspot.com/swagger-ui/index.html#/Sess%C3%A3o/create) - https://my-project-cooperativismo.ue.r.appspot.com/swagger-ui/index.html#/Sess%C3%A3o/create

[VOTAR](http://my-project-cooperativismo.ue.r.appspot.com/swagger-ui/index.html#/Vota%C3%A7%C3%A3o/makeVote) - https://my-project-cooperativismo.ue.r.appspot.com/swagger-ui/index.html#/Vota%C3%A7%C3%A3o/makeVote


# Para dar Inicio

<h4> Configurações Locais </h4>

Para o funciomento do micro-serviço é necessário a instalação do banco postgresl.
Pode ser baixado no site https://www.postgresql.org/download/

Após baixado basta instalar seguindo a documentação para o Sistema Operacional de uso.

Deve-se criar uma conexão Host:localhost, database: postgres, user name: postgres, password: postgres e port: 54331

## Observação

Deve ser  instalado o Maven e o Java .
Para execuação da aplicação

# Build and Test

Para rodar a aplicacao entra na pasta raíz do projeto e executar o comando:


```bash

mvn clean package

```

Com isso vai ser gerado um arquivo jar na pasta /target

cooperativism-service-0.0.1-SNAPSHOT.jar

E então para execução da aplicação:

```bash

cd /target

java -jar cooperativism-service-0.0.1-SNAPSHOT.jar


```

Acessar  a documentação no endereço:

http://localhost:8080/swagger-ui/index.html

Seguir com a definição do fluxo acima.

# Referencias:

- [Java](https://www.oracle.com/br/java/technologies/downloads/) - https://www.oracle.com/br/java/technologies/downloads/
- [Git](https://git-scm.com/downloads) - https://git-scm.com/downloads
- [Postgres](https://www.postgresql.org/download) - https://www.postgresql.org/download
- [Maven](https://maven.apache.org/download.cgi) - https://maven.apache.org/download.cgi)



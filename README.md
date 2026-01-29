# Subpay - Arquitetura Hexagonal

Projeto demonstrativo de arquitetura hexagonal (Ports & Adapters) com módulo core agnóstico de framework e adaptadores para Spring Boot e Quarkus.

## Estrutura do Projeto

```
subpay/
├── core/                   # Módulo core agnóstico (Java 21)
│   └── src/main/java/br/com/a4a/subpay/
│       ├── domain/         # Entidades e Value Objects
│       ├── application/    # Use Cases e Ports
│       └── shared/         # Componentes compartilhados
├── sboot-adapter/          # Adaptador Spring Boot 4.0.1
│   └── src/main/java/br/com/a4a/sbootadapter/
│       └── web/            # Controllers REST
├── quarkus-adapter/        # Adaptador Quarkus 3.30.6
│   └── src/main/java/br/com/a4a/web/
│       └── AccountController.java
└── Makefile                # Automação de tarefas
```

## Início Rápido

### Pré-requisitos
- Java 21
- Maven 3.8+
- Make (geralmente já instalado no macOS/Linux)

### Comandos Principais

```bash
# Ver todos os comandos disponíveis
make help

# Compilar e instalar o core
make install-core

# Executar Spring Boot
make run-spring

# Executar Quarkus
make run-quarkus

# Comandos rápidos (compila core + executa)
make quick-start-spring
make quick-start-quarkus
```

## Comandos do Makefile

### Compilação

| Comando | Descrição |
|---------|-----------|
| `make install-core` | Compila e instala o módulo core |
| `make build-spring` | Compila o adaptador Spring Boot |
| `make build-quarkus` | Compila o adaptador Quarkus |
| `make build-all` | Compila todos os módulos |
| `make clean` | Limpa todos os builds |

### Execução

| Comando | Descrição |
|---------|-----------|
| `make run-spring` | Executa Spring Boot (porta 8080) |
| `make run-spring-port PORT=9090` | Executa Spring Boot em porta customizada |
| `make run-quarkus` | Executa Quarkus em modo dev (porta 8080) |
| `make run-quarkus-port PORT=9090` | Executa Quarkus em porta customizada |
| `make quick-start-spring` | Compila core e inicia Spring Boot |
| `make quick-start-quarkus` | Compila core e inicia Quarkus |

### Testes

| Comando | Descrição |
|---------|-----------|
| `make test-spring` | Executa testes do Spring Boot |
| `make test-quarkus` | Executa testes do Quarkus |
| `make test-all` | Executa todos os testes |
| `make curl-test` | Testa o endpoint (servidor deve estar rodando) |

### Utilitários

| Comando | Descrição |
|---------|-----------|
| `make help` | Mostra todos os comandos disponíveis |
| `make info` | Exibe informações do projeto |
| `make verify` | Verifica dependências do projeto |
| `make package-spring` | Cria JAR do Spring Boot |
| `make package-quarkus` | Cria JAR do Quarkus |

## Endpoints

Ambos os adaptadores expõem o mesmo endpoint:

```
GET http://localhost:8080/api/v1/account
```

**Resposta:**
```json
{
  "amount": 200,
  "negative": false,
  "positive": true,
  "positiveOrZero": true
}
```

### Testando com curl

```bash
# Testar endpoint
curl http://localhost:8080/api/v1/account

# Testar com formatação JSON (requer jq)
curl http://localhost:8080/api/v1/account | jq '.'

# Ou usar o comando make
make curl-test
```

## Arquitetura Hexagonal

### Princípios

1. **Core Agnóstico**: O módulo `core` não depende de nenhum framework
2. **Inversão de Dependências**: Adaptadores dependem do core, não o contrário
3. **Portas e Adaptadores**: Interfaces (ports) no core, implementações (adapters) nos módulos externos
4. **Testabilidade**: Regras de negócio podem ser testadas independentemente

### Benefícios Demonstrados

- Mesmo código de domínio (`Money.java`) usado por ambos os frameworks
- Possibilidade de trocar framework sem alterar regras de negócio
- Testes independentes de framework
- Facilita manutenção e evolução do código

## Desenvolvimento

### Executando em Portas Customizadas

Você pode executar os adaptadores em portas diferentes da padrão (8080):

```bash
# Spring Boot na porta 9090
make run-spring-port PORT=9090

# Quarkus na porta 9090
make run-quarkus-port PORT=9090

# Qualquer porta disponível
make run-spring-port PORT=3000
make run-quarkus-port PORT=8081
```

Após iniciar em uma porta customizada, acesse:
- **Endpoint:** `http://localhost:PORT/api/v1/account`
- **Quarkus Dev UI:** `http://localhost:PORT/q/dev` (apenas Quarkus)
### Modo Desenvolvimento Spring Boot

```bash
make dev-spring
```

### Modo Desenvolvimento Quarkus (com hot reload)

```bash
make dev-quarkus
# ou
make run-quarkus
```

O Quarkus em modo dev oferece:
- Hot reload automático
- Dev UI em http://localhost:8080/q/dev
- Debug remoto na porta 5005

## Empacotamento

### Gerar JARs executáveis

```bash
# Spring Boot
make package-spring
# JAR em: sboot-adapter/target/sboot-adapter-0.0.1-SNAPSHOT.jar

# Quarkus
make package-quarkus
# JAR em: quarkus-adapter/target/quarkus-adapter-1.0-SNAPSHOT-runner.jar

# Ambos
make package-all
```

### Executar JARs

```bash
# Spring Boot
java -jar sboot-adapter/target/sboot-adapter-0.0.1-SNAPSHOT.jar

# Quarkus
java -jar quarkus-adapter/target/quarkus-adapter-1.0-SNAPSHOT-runner.jar
```

## Testes

```bash
# Testar Spring Boot
make test-spring

# Testar Quarkus
make test-quarkus

# Testar tudo
make test-all
```

## Tecnologias

- **Java 21** - Linguagem base
- **Maven** - Gerenciamento de dependências
- **Spring Boot 4.0.1** - Framework web (adaptador)
- **Quarkus 3.30.6** - Framework cloud-native (adaptador)
- **Jakarta Validation API** - Validações
- **Hibernate Validator** - Implementação de validações

## Casos de Uso

Este projeto demonstra como:
- Implementar arquitetura hexagonal em Java
- Criar um core reutilizável entre diferentes frameworks
- Usar o mesmo domínio com Spring Boot e Quarkus
- Estruturar projeto multi-módulo com Maven
- Automatizar tarefas com Makefile

## Licença

Este é um projeto demonstrativo para fins educacionais.

---

**Desenvolvido como demonstração de Arquitetura Hexagonal**


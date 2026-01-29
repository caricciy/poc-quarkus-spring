.PHONY: help clean install-core build-all run-spring run-quarkus test-spring test-quarkus stop

# Cores para output
GREEN  := \033[0;32m
YELLOW := \033[0;33m
BLUE   := \033[0;34m
NC     := \033[0m # No Color

help: ## Mostra esta mensagem de ajuda
	@echo "$(BLUE)════════════════════════════════════════════════════════════$(NC)"
	@echo "$(GREEN)  Subpay - Arquitetura Hexagonal$(NC)"
	@echo "$(BLUE)════════════════════════════════════════════════════════════$(NC)"
	@echo ""
	@echo "$(YELLOW)Comandos disponiveis:$(NC)"
	@echo ""
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "  $(GREEN)%-20s$(NC) %s\n", $$1, $$2}'
	@echo ""

clean: ## Limpa todos os builds (core, spring-boot e quarkus)
	@echo "$(YELLOW)Limpando todos os projetos...$(NC)"
	@mvn clean
	@cd sboot-adapter && mvn clean
	@cd quarkus-adapter && mvn clean
	@echo "$(GREEN)Limpeza concluida!$(NC)"

install-core: ## Compila e instala o módulo core
	@echo "$(YELLOW)Compilando e instalando o modulo core...$(NC)"
	@mvn clean install
	@echo "$(GREEN)Core instalado com sucesso!$(NC)"

build-spring: install-core ## Compila o adaptador Spring Boot
	@echo "$(YELLOW)Compilando Spring Boot adapter...$(NC)"
	@cd sboot-adapter && mvn clean package
	@echo "$(GREEN)Spring Boot compilado com sucesso!$(NC)"

build-quarkus: install-core ## Compila o adaptador Quarkus
	@echo "$(YELLOW)Compilando Quarkus adapter...$(NC)"
	@cd quarkus-adapter && mvn clean package
	@echo "$(GREEN)Quarkus compilado com sucesso!$(NC)"

build-all: install-core build-spring build-quarkus ## Compila todos os módulos

run-spring: install-core ## Executa o Spring Boot (porta 8080)
	@echo "$(YELLOW)Iniciando Spring Boot adapter...$(NC)"
	@echo "$(BLUE)Servidor disponivel em: http://localhost:8080$(NC)"
	@echo "$(BLUE)Endpoint: http://localhost:8080/api/v1/account$(NC)"
	@cd sboot-adapter && mvn spring-boot:run

run-spring-port: install-core ## Executa o Spring Boot em porta customizada (uso: make run-spring-port PORT=9090)
	@if [ -z "$(PORT)" ]; then \
		echo "$(YELLOW)Erro: Especifique a porta com PORT=XXXX$(NC)"; \
		echo "$(YELLOW)Exemplo: make run-spring-port PORT=9090$(NC)"; \
		exit 1; \
	fi
	@echo "$(YELLOW)Iniciando Spring Boot adapter na porta $(PORT)...$(NC)"
	@echo "$(BLUE)Servidor disponivel em: http://localhost:$(PORT)$(NC)"
	@echo "$(BLUE)Endpoint: http://localhost:$(PORT)/api/v1/account$(NC)"
	@cd sboot-adapter && mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=$(PORT)

run-quarkus: install-core ## Executa o Quarkus em modo dev (porta 8080)
	@echo "$(YELLOW)Iniciando Quarkus adapter...$(NC)"
	@echo "$(BLUE)Servidor disponivel em: http://localhost:8080$(NC)"
	@echo "$(BLUE)Endpoint: http://localhost:8080/api/v1/account$(NC)"
	@echo "$(BLUE)Dev UI: http://localhost:8080/q/dev$(NC)"
	@cd quarkus-adapter && mvn quarkus:dev

run-quarkus-port: install-core ## Executa o Quarkus em porta customizada (uso: make run-quarkus-port PORT=9090)
	@if [ -z "$(PORT)" ]; then \
		echo "$(YELLOW)Erro: Especifique a porta com PORT=XXXX$(NC)"; \
		echo "$(YELLOW)Exemplo: make run-quarkus-port PORT=9090$(NC)"; \
		exit 1; \
	fi
	@echo "$(YELLOW)Iniciando Quarkus adapter na porta $(PORT)...$(NC)"
	@echo "$(BLUE)Servidor disponivel em: http://localhost:$(PORT)$(NC)"
	@echo "$(BLUE)Endpoint: http://localhost:$(PORT)/api/v1/account$(NC)"
	@echo "$(BLUE)Dev UI: http://localhost:$(PORT)/q/dev$(NC)"
	@cd quarkus-adapter && mvn quarkus:dev -Dquarkus.http.port=$(PORT)

test-spring: install-core ## Executa os testes do Spring Boot
	@echo "$(YELLOW)Executando testes do Spring Boot...$(NC)"
	@cd sboot-adapter && mvn test
	@echo "$(GREEN)Testes do Spring Boot concluidos!$(NC)"

test-quarkus: install-core ## Executa os testes do Quarkus
	@echo "$(YELLOW)Executando testes do Quarkus...$(NC)"
	@cd quarkus-adapter && mvn test
	@echo "$(GREEN)Testes do Quarkus concluidos!$(NC)"

test-all: test-spring test-quarkus ## Executa todos os testes

curl-test: ## Testa o endpoint (requer servidor rodando)
	@echo "$(YELLOW)Testando endpoint...$(NC)"
	@curl -s http://localhost:8080/api/v1/account | jq '.' || curl http://localhost:8080/api/v1/account
	@echo ""
	@echo "$(GREEN)Teste concluido!$(NC)"

package-spring: build-spring ## Cria o pacote JAR do Spring Boot
	@echo "$(GREEN)JAR criado em: sboot-adapter/target/sboot-adapter-0.0.1-SNAPSHOT.jar$(NC)"

package-quarkus: build-quarkus ## Cria o pacote JAR do Quarkus
	@echo "$(GREEN)JAR criado em: quarkus-adapter/target/quarkus-adapter-1.0-SNAPSHOT-runner.jar$(NC)"

package-all: package-spring package-quarkus ## Cria todos os pacotes

info: ## Exibe informações do projeto
	@echo "$(BLUE)════════════════════════════════════════════════════════════$(NC)"
	@echo "$(GREEN)  Informacoes do Projeto$(NC)"
	@echo "$(BLUE)════════════════════════════════════════════════════════════$(NC)"
	@echo ""
	@echo "$(YELLOW)Modulos:$(NC)"
	@echo "  - core           - Dominio e regras de negocio (Java 21)"
	@echo "  - sboot-adapter  - Adaptador Spring Boot 4.0.1"
	@echo "  - quarkus-adapter - Adaptador Quarkus 3.30.6"
	@echo ""
	@echo "$(YELLOW)Endpoints:$(NC)"
	@echo "  - GET http://localhost:8080/api/v1/account"
	@echo ""
	@echo "$(YELLOW)Arquitetura:$(NC)"
	@echo "  - Hexagonal (Ports & Adapters)"
	@echo "  - Core agnostico de framework"
	@echo ""

# Comandos úteis para desenvolvimento
dev-spring: ## Modo desenvolvimento com Spring Boot (auto-reload)
	@echo "$(YELLOW)Iniciando Spring Boot em modo dev...$(NC)"
	@cd sboot-adapter && mvn spring-boot:run -Dspring-boot.run.fork=false

dev-quarkus: run-quarkus ## Alias para run-quarkus (já tem hot reload)

# Verificação de dependências
verify: ## Verifica as dependências do projeto
	@echo "$(YELLOW)Verificando dependencias...$(NC)"
	@mvn dependency:tree
	@echo "$(GREEN)Verificacao concluida!$(NC)"

# Lista de comandos rápidos
quick-start-spring: ## [RAPIDO] Compila core e inicia Spring Boot
	@$(MAKE) install-core
	@$(MAKE) run-spring

quick-start-quarkus: ## [RAPIDO] Compila core e inicia Quarkus
	@$(MAKE) install-core
	@$(MAKE) run-quarkus


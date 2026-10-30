<<<<<<< HEAD
# Test ADRIA ENSET 2027 — Microservices bancaires

Projet Maven multimodule (Java 21, Spring Boot 4.1.2, Spring Cloud 2025.1.4, Spring AI 2.0.2).

## Modules

| Module | Rôle | Port |
|---|---|---|
| discovery-service | Annuaire Eureka | 8761 |
| config-service | Configuration centralisée (profil native) | 9999 |
| gateway-service | Spring Cloud Gateway | 8888 |
| account-service | Microservice métier : comptes | 8081 |
| transaction-service | Microservice métier : transactions | 8082 |
| event-consumer-service | Consommateur Kafka (journalisation) | — |
| chatbot-service | Agent Spring AI (client MCP) | 8085 |

## Compilation

```bash
mvn clean package -DskipTests
```

## État

Étape 1 : squelette multimodule et classes de démarrage. Aucune fonctionnalité métier.
=======
# Samy-Ayoub-test-adria-pfe-27
>>>>>>> 538e8ceea9057cf79169e4e2acd73a2cd64224e6

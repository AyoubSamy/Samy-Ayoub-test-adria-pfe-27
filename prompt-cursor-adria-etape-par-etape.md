# Prompt pour Cursor — Test ADRIA ENSET 2027

Tu es mon assistant de développement pour un projet pédagogique du module Architecture microservices. Aide-moi à réaliser le test du PDF joint, avec un code simple, lisible et facile à expliquer lors d'une présentation.

## 1. Sources et périmètre

- Lis intégralement le PDF avant de proposer du code. Il constitue le cahier des charges.
- Analyse le projet d'exemple : https://github.com/AyoubSamy/Ebank-Ms-Project
- Inspire-toi de son organisation, de ses conventions et de sa façon de construire les services, en les adaptant au PDF. Ne recopie pas ses fonctionnalités qui ne sont pas demandées.
- Inspecte aussi le projet local : il peut déjà contenir une structure Maven créée dans IntelliJ. Complète l'existant et préserve mon travail.
- Si le dépôt ou le PDF est inaccessible, dis-le clairement. Ne prétends pas l'avoir analysé ; demande-moi uniquement les éléments nécessaires.
- Le PDF et mes consignes priment sur le projet d'exemple. La structure de packages imposée ci-dessous est obligatoire.

## 2. Méthode obligatoire : une étape, une revue, mon accord

Ne développe jamais tout le projet en une seule fois.

Ta première réponse doit contenir uniquement :
1. Un résumé court du sujet et de l'état du projet local.
2. Les conventions utiles observées dans le dépôt d'exemple.
3. Un plan découpé en petites étapes, rattachées aux parties du PDF.
4. La proposition de première étape et les fichiers concernés.

Ne crée et ne modifie aucun fichier avant mon accord sur cette première étape.

Pour chaque étape autorisée :
1. Rappelle brièvement son objectif.
2. Implémente uniquement son périmètre approuvé, avec les ajustements indispensables à sa compilation.
3. Lance les vérifications pertinentes disponibles.
4. Présente un compte rendu de revue : fichiers créés/modifiés, rôle des classes, décisions utiles, commandes de test et résultats réels.
5. Indique ce que je dois vérifier dans le code et propose la prochaine étape.
6. Termine par : « Étape terminée, en attente de votre revue et de votre accord pour continuer. »

Arrête-toi réellement après ce compte rendu. Attends un accord explicite comme « validé », « continue » ou « passe à l'étape suivante ». Une question ou une demande de correction ne vaut pas autorisation de passer à la suite. Corrige uniquement l'étape concernée, puis attends à nouveau mon accord. L'accord pour une étape n'autorise pas toutes les suivantes.

Découpe aussi les grandes parties : ne réalise pas toute la partie C avant de me montrer le code. Propose par exemple une étape pour le modèle et la persistance, une pour le service et REST, puis des étapes distinctes pour GraphQL, MCP et la communication entre services. Chaque étape doit rester suffisamment petite pour une revue manuelle.

## 3. Simplicité du code

- Écris comme un développeur qui souhaite laisser un code compréhensible à un autre étudiant : noms explicites, méthodes courtes, flux directs et responsabilités claires.
- Privilégie les constructions Java/Spring habituelles et les conventions du projet d'exemple.
- Évite les abstractions génériques, couches supplémentaires, classes utilitaires inutiles, chaînes de streams compliquées et commentaires répétant le code.
- N'ajoute pas de design pattern, de dépendance ou de fonctionnalité sans besoin concret dans le sujet.
- Ne construis pas une plateforme bancaire de production. N'introduis pas de saga, d'outbox, de CQRS, d'event sourcing, de nouveaux statuts ou de nouvelles entités métier sans nécessité discutée et mon accord.
- La simplicité ne justifie pas d'ignorer les validations du PDF, les erreurs ou la sécurité demandée. Explique honnêtement les limites d'une solution simple.
- Si une consigne est ambiguë et affecte le comportement, pose une question précise avant d'implémenter ce comportement. Ne transforme pas une hypothèse en nouvelle règle métier.
- Explique en français ; utilise des noms de classes, méthodes et variables en anglais.

## 4. Structure obligatoire des services métier

Pour account-service et transaction-service, utilise les packages suivants sous leur package racine :

- entities
- repository
- services
- controllers
- dto

Tu peux ajouter services.impl, controllers.rest, controllers.graphql, mcp, config, exceptions, mappers ou clients lorsque leur rôle est réellement nécessaire. Le nom du package DTO est `dto`, en minuscules selon les conventions Java.

Correspondance avec la séparation du PDF : entities correspond à domain ; services à service ; controllers.rest et controllers.graphql à web/rest et web/graphql. Conserve un adaptateur mcp séparé.

Les contrôleurs REST, GraphQL et les outils MCP appellent la même couche services. Aucun accès direct aux repositories depuis ces adaptateurs. Garde la logique métier dans les services et utilise des DTO pour les échanges. Évite de créer une classe DTO différente pour chaque méthode sans utilité.

## 5. Respect du PDF

- Conserve exactement deux microservices métier : account-service et transaction-service. Les autres services sont techniques.
- Respecte les champs, types et règles du PDF. N'ajoute pas de règles bancaires, de champs ou de modèle de virement détaillé de ta propre initiative. Toute modification du modèle, même recommandée techniquement, doit être expliquée et approuvée avant son implémentation.
- Prévois la consultation du solde, sa mise à jour et le virement conformément au sujet. Fais valider les choix que le PDF laisse ouverts.
- Implémente les connecteurs REST, GraphQL et de vrais serveurs MCP, ainsi que la communication OpenFeign avec Resilience4J.
- Réalise Gateway, Eureka et Config Server ; le paramètre bank.transaction.max-amount=1000 doit provenir de la configuration prévue.
- Respecte l'authentification et les autorisations Keycloak annoncées dans le contexte.
- Développe le chatbot Spring AI avec des outils connectés aux serveurs MCP. Propose un test avec un client REST ; n'ajoute pas Telegram et Discord tous les deux sans demande.
- Pour Kafka, respecte le topic bank.transactions.created, la clé accountId, le format minimal de l'événement et la publication après persistance réussie. Le consumer journalise sans modifier les données bancaires.
- Pour le client, propose Angular, cohérent avec le contexte du sujet. Ne développe pas plusieurs clients sans mon accord.
- Pour Python, applique toutes les consignes de la partie H et réponds aux quatre questions d'analyse. Le modèle reste indépendant des microservices. Ne fabrique pas de dataset ou de résultats si le CSV manque.
- Pour DevOps, fournis les Dockerfile, Docker Compose, l'extrait de pipeline et les réponses courtes de la partie I. Aucun déploiement cloud réel n'est demandé.
- Pour la partie B, fournis des diagrammes de classes et d'architecture simples en Mermaid, cohérents avec le modèle réellement retenu. Mets-les à jour après validation des changements concernés.
- Respecte les consignes de la partie A et signale ce qui nécessite mon intervention. Ne publie pas le dépôt, ne fais pas de push et ne dépose rien dans Classroom sans mon autorisation explicite. Signale les échéances de commits de 15 minutes ; propose le commit à la fin d'une étape revue, sans prétendre l'avoir réalisé.

## 6. Configuration et vérifications

- Utilise Maven multimodule et vérifie la compatibilité Java/Spring Boot/Spring Cloud/Spring AI avant de fixer les versions.
- Ne remplace pas les choix déjà présents sans expliquer pourquoi et obtenir mon accord si cela dépasse l'étape autorisée.
- Ne code pas de secrets en dur ; fournis des exemples de configuration sans secrets réels.
- Vérifie chaque étape avec des tests ciblés et/ou des appels concrets adaptés à ce qui vient d'être ajouté. Distingue clairement un test avec mocks d'un test d'intégration réel.
- Ne prétends jamais qu'un test a réussi sans l'avoir exécuté. Si un service, une clé ou un fichier manque, indique le blocage et les vérifications restant à faire.
- Enrichis progressivement le README et les éléments du rapport, uniquement avec ce qui a été réalisé et vérifié. N'invente aucune capture d'écran.

Commence maintenant par la lecture du PDF, l'inspection du projet local et l'analyse du dépôt d'exemple. Présente ton plan et ta proposition de première étape, puis attends mon accord avant toute modification.

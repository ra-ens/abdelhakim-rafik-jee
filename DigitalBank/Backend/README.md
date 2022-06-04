# Digital Banking

Digital Banking est un projet développer sous Spring Framework, et MySQL comme base de données, il consiste à gérer les différentes opérations bancaires à savoir les retraites, les transferts d’argents etc…  
Ce projet est composé de deux parties, une partie backend, et une frontend développer avec Angular Framework et Tailwind CSS.

## Conditions préalables

- [JAVA](https://www.oracle.com/java/technologies/downloads/)
- [MySQL](https://dev.mysql.com/downloads/installer/)
- [Node.js](https://nodejs.org/en/)

## Technologies

- **Backend**: Spring Boot, Spring Security, Spring Data, Lombok, Spring web.
- **Frontend**: HTML5, CSS3, Tailwind, TypeScript, Angular
- **Database**: MySQL

## Déploiement

Pour exécuter ce projet, lancez tout d'abord votre mysql puis lancez le serveur backend.  
Deuxièmement, exécutez l'application frontale en exécutant la commande suivante

```bash
  npm run start
```

En suite vous pouvez accéder à l’application on visite le lien suivant : localhost:4200

## Architecteur de projet

Dans cette partie nous allons voir l’architecteur global de notre système comme montre la figure ci-dessous.

![img.png](doc/img.png)

## Conception

Notre système se compose de cinq classes, le diagramme suivant représente les classes indispensables pour remplir les besoins fonctionnels du système.

![img.png](doc/class_diagram.png)

Comme notre système doit être sécurisé et nous voulons avoir la stratégie des rôles, le diagramme suivant représente les classes nécessaires pour répondre à ces contraints

![img_1.png](doc/img_1.png)

Notre base de données qui contient tous les entités nécessaires est modélisé comme suite

![img_2.png](doc/img_2.png)

## Réalisation
Après la conception, cette partie concerne la réalisation de notre système. Notre système
est développé avec Spring Framework comme Backend, et une application web avec Angular
et application mobile avec Flutter comme frontend.  
Pour accéder à notre système nous devons en premier lieu s’authentifier puisque nous
utilisons un système de sécurité base sur le JWT.

![img_3.png](doc/img_3.png)

Apres l’authentification au système nous pouvons donc accéder aux différents services

![img_4.png](doc/img_4.png)

Le Frontend web est développer avec le Framework Angular. La première page qui s’affiche
est la page d’authentification dans laquelle l’utilisateur peut s’authentifier.

![img_5.png](doc/img_5.png)

Ou être rediriger vers une autre page pour créer son compte.

![img_6.png](doc/img_6.png)

Apres l’authentification si l’utilisateur est un ADMIN il sera redirigé vers la page des comptes
dans laquelle il peut consulter tous les comptes et effectuer les différentes opérations
proposées par le système.

![img_7.png](doc/img_7.png)

Dans cette page l’administrateur peut rechercher un compte par son identificateur, puis
voire tous l’historique des opérations.  
L’administrateur peut également effectuer les opérations de débit, crédit et transfert on
utilise le formulaire à droite.  
Une page de clients est mise à la disposition de l’administrateur pour consulter tous les
clients qui son inscrit dans le système comme présenter dans la figure suivante.

![img_8.png](doc/img_8.png)
# Stage 1 Report - Portfolio Project

## 1. Team Formation Overview

### Membres
- Julian Mitchell

### Rôles
- **Développeur principal** : Julian Mitchell
- **Chef de projet** : Julian Mitchell
- **Responsable communication** : Julian Mitchell

### Stratégie de collaboration

**Outils utilisés** :
- Google Agenda : organisation du temps et des tâches
- GitHub : portail du projet, README files...
- Google Drive : pour l'eventuel stockage de documents supplementaires

**Normes de communication établies** :
- Mise à jour hebdomadaire des documents sur Google Drive
- Partage du code sur le repo GitHub

## 2. Ideas Explored

| Idée | Description | Forces | Faiblesses | Raison de rejet |
|------|-------------|--------|-----------|-----------------|
| Recettes de cuisine | DB des recettes de cuisine maison. Filtrage par ingredient (eviter le gaspillage) ou nombre d'ingredient (budget). Filtrage par contrainte ? Limiter les sucres, limiter le sel, limiter le gras… | Simple, mais peut permettre de cocher toutes les cases pour un demo-day / RNCP | Tres simple, donc a soigner ? | |
| Appli de calcul de performance pour la simulation aerienne | Appli pour obtenir les configurations de decollage pour les avions de ligne (jeux de simulation). | DB importante et disponible pour les aeroports, pistes etc… | Modelisation des calculs chronophage. D'autant plus difficile de construire un modele sans connaitre le profil aerodynamique des ailes des avions (donnees non publiques) | Trop complexe pour le temps disponible pour le projet |
| Appli de saisie de notes de frais open source pour le milieu associatif | Saisie d'une organisation, profil admin ou user, tableau de bord admin, gestion des droits | Permet d'aborder de nombreuses notions, de cocher les cases pour demo/day RNCP | Tres complexe, et necessite une appli mobile pour constituer une vraie solution, un module ou IA pour du image to text, la creation d'une gestion de la categorisation qui peut etre specifique a chaque organisation…. | Projet interessant, mais delais sans doute trop courts pour un tel niveau de complexite et le RNCP niveau 5 |
| HBnB REvolution | Pousser plus loin le concept du HBnB en approfondissant les fonctionnalites proposees (front et back) | Permet de partir d'une base existante et d'aller vraiment plus loin : server-side filtering, filtrage des requetes en fonctions de criteres comme la geolocalisation de l'utilisateur, gestion du cache et des cookies, pourquoi pas introduire du Nginx (reverse proxy + load balancing), ajouter un message broker (gestion des mails de reservation et d'activation), prise en charge de plusieurs langues (en fonction des parametres du navigateur de l'utilisateur)... | En partant d'une base existante, ne permet pas de refaire un back de A a Z. Risque de complexite et de couts d'utilisation de certains outils (ou utilisation gratuite tres limitee). | A conserver pour plus tard ? |
| Application de couverture assurance | Avant de voyager, comprendre ce qui est couvert ou non par ses assurances (moyens de paiement, mutuelle, assurance) pour savoir a quel type de prestation souscrire | Je ne crois pas qu'il y ait une alternative existante | La diversite des produits d'assurance existante est enorme, et traiter cette donnee necessite des API qui n'existent pas forcement, ou de l'IA pour faire le traitement de donnees publiques => complexite | Complexite techniques, probleme de faisabilite ? |
| Cookstagram | Reseau social de partage de photos de repas et recettes de cuisine | Peut etre original et presenter un interet a l'heure ou les preoccupations alimentaires font de plus en plus partie du quotidien des gens, tout comme la mecanique de partage numerique sur les reseaux sociaux. | Necessite la realisation d'un algorithme pour identifier les interets des utilisateurs : complexe. | Inadapte en terme de complexite et de format pour le RNCP / demo day ? |

## 3. Selected MVP Concept

### Nom du projet : Bon app eat it

### Résumé du projet
Création d'une application web de stockage des recettes de cuisine, avec la possibilité de filtrer rapidement ses recettes par ingrédient (eviter le gaspillage), par nombre d'ingrédients (eviter les recettes qui multiplient les ingrédients et reviennent cher en fin de compte), ou par contrainte alimentaire (limiter le sucre, limiter le sel…), type de préparation (mijote, pâtes, soupes, potages, salades…).

### Le problème résolu
Identifier rapidement et facilement les recettes réalisables (après les courses) ou pertinentes (avant les courses).

### Raisons de la sélection
- **Adequation avec les attendus RNCP 5** : Coche les cases pour un titre RNCP :
  - Développer la partie front-end d'une application Web ou Web mobile sécurisée (Installer et configurer son environnement de travail en fonction du projet Web ou Web mobile, Maquetter des interfaces utilisateur Web ou Web mobile, Réaliser des interfaces utilisateur statiques Web ou Web mobile, Développer la partie dynamique des interfaces utilisateur Web ou Web mobile)
  - Développer la partie back-end d'une application Web ou Web mobile sécurisée (Mettre en place une base de données relationnelle, Développer des composants d'accès aux données SQL et NoSQL, Développer des composants métier côté serveur, Documenter le déploiement d'une application dynamique Web ou Web mobile).
- **Réalisable dans le temps imparti**: Permet de revoir les notions acquises pendant la formation tout en étant prêt pour le demo day et le RNCP 5.
- **Intérêt personnel** : Répond à un besoin de la vie de tous les jours
- **Scalabilité** : Possibilité d'ajouter des fonctionnalités si l'utilisation de l'application est intéressante (image to text, speech to text, AI...)

### Challenges potentiels
- Front intuitif et attractif, moderne.
- Contraintes techniques liées à l'utilisation de nouvelles technologies.

### Opportunités
- Public cible potentiellement très large
- Opportunité technique de progression avec un outil utilisable régulièrement qui pourrait être amené à évoluer…
- …vers une appli mobile par exemple

### Public cible
- Tous ceux qui mangent…
- …font ou souhaitent faire attention à ce qu'ils mangent.
- Utilisateurs du numériques => plutôt les jeunes

## 4. Idea Development Documentation

### Processus suivi
- Brainstorming individuel
- Évaluation des idées selon :
  - Adéquation avec les critères d'évaluation du RNCP 5.
  - Faisabilité technique dans des délais courts
- Sélection finale basée sur un projet réaliste en termes de temps et de complexité, au regard des attendus du RNCP 5.

### Documentation utilisée
- Google Agenda (planification)
- Google Drive (documentation du projet, archivage)
- GitHub

## 5. URL du dossier de travail

https://github.com/jmitchell35/holbertonschool-portfolio_project
https://drive.google.com/drive/u/0/folders/1U5-7wyJ-mJm8FeU5Lcxvmal-DHaiC5SS

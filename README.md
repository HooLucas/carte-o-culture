![Build Status](https://img.shields.io/gitlab/pipeline-status/Roofne/carte-o-culture?branch=main&style=plastic)
![Coverage Status](https://coveralls.io/repos/gitlab/Roofne/carte-o-culture/badge.svg?branch=main)
![Licence](https://img.shields.io/gitlab/license/40541214)
![Release](https://img.shields.io/gitlab/v/release/40541214?date_order_by=created_at&sort=date)
![Issue](https://img.shields.io/gitlab/issues/open-raw/Roofne/carte-o-culture?gitlab_url=https%3A%2F%2Fgitlab.com)

<div align="center">

   # Carte-O-Culture

   ![image](https://i.ibb.co/FxwtCmk/Capture-d-cran-2022-09-20-20-42-50.png)

   # La meilleure plateforme pour une immersion dans la culture

</div>


## Description

Le principe de notre application Carte'O'Culture est de mettre en immersion nos utilisateurs dans les différents lieux et références de leurs œuvres favorites, 
en particulier notre application pourrait leur proposer un parcours qui retracerait une œuvre.

## Prerequis
   ```sh
   Java 17
   Maven 5
   ```

## Installation

1. Cloner le répertoire
   ```sh
   git clone https://gitlab.com/Roofne/carte-o-culture.git
   ```

2. Executer les commandes dans un terminal
   ```sh
   mysql -u root -p < ./docs/sql/init.sql
   mvn clean install
   mvn spring-boot:run
   ```
3. Ouvrir le navigateur à l'adresse 127.0.0.1:8080

## Usage

Une fois la page ouverte, vous pouvez explorer les différents lieux cités dans les oeuvres française.

image.png

Vous pouvez cliquer sur les bouton pour faire appàraitre ou disparaitres les marqueurs de position.
Chaque marqueurs correspond à une ville cité dans un livre.

## Roadmap
- [x] Création d'un répertoire de dépot
- [x] Détection des lieux dans un fichier livre (epub)
- [x] Création d'une carte intérractive
- [x] Ajout de nouveaux livres dans le dataset
- [ ] Affichage des lieux communs entre des livres

Aller à cette [adresse](https://gitlab.com/Roofne/carte-o-culture/-/issues) pour voir toute les issues et les prochaines features.

## Contribution

## Auteurs

* **Toine  VINTEL** - [@Roofne](https://gitlab.com/Roofne) 
* **Yohann SIMO**   - [@SimoYo](https://github.com/SimoYo)
* **Lucas  HO**     - [@HooLucas](https://github.com/HooLucas)
* **Jylan  PAYET**  - [@jylanpayet](https://github.com/jylanpayet)

## Licence

Ce projet est sous une licence GNU Public Licence 3

## Status

Projet réalisé durant les années de Master MIAGE à Paris Nanterre.


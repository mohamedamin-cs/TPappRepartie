# TP 4 : Sockets UDP en Java (Service de Chat Client/Serveur)

Ce dépôt contient le code du TP4 pour le cours de Développement d'applications réparties (LSI 3) à la Faculté des Sciences de Sfax. Il présente la mise en place d'une communication réseau en mode non-connecté avec le protocole UDP.

---

## Objectifs du TP

- Manipulation des sockets UDP (DatagramSocket et DatagramPacket) en Java.
- Envoi et réception de messages texte entre un client et un serveur.

---

## Fichiers du projet

- ServeurUDP.java : Le serveur qui écoute les datagrammes entrants.
- ClientUDP.java : Le client permettant de saisir un pseudo et d'envoyer des messages.

---

## Procédure d'exécution

1. Compilation des fichiers :
   javac ServeurUDP.java ClientUDP.java

2. Lancement du serveur :
   java ServeurUDP

3. Lancement du client :
   java ClientUDP

Instructions client :
- Saisir votre pseudo au démarrage.
- Taper vos messages et valider avec la touche Entrée.
- Entrer exit pour fermer l'application client.

---

## Synthèse de l'Étape 2 (Chat Unidirectionnel)

- Serveur : Écoute les paquets UDP sur le port 1234 via InetSocketAddress. Il extrait le texte et affiche l'adresse IP ainsi que le port de l'expéditeur.
- Client : Récupère le nom d'utilisateur puis formate chaque message au format [Pseudo]: Message avant l'envoi.

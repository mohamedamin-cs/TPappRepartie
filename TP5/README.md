# Compte Rendu TP 5 : Invocation des méthodes sur des objets distants avec Java RMI & JNDI

* **Établissement :** Faculté des Sciences de Sfax (FSS)
* **Département :** Informatique et des Communications
* **Filière :** LSI 3 (Année Universitaire 2026-2027)
* **Matière :** Développement d'Applications Réparties
* **Responsable du cours :** Mme. Amal ABID
* **Enseignante des TPs :** Mme. Zeineb DAOUD

---

## Introduction Générale
Ce travail pratique a pour objectif la mise en œuvre d'architectures distribuées en Java en utilisant l'API **RMI (Remote Method Invocation)**. Il s'articule autour de deux activités progressives permettant d'aborder le cycle de vie d'un objet distant, le transfert d'objets métiers par sérialisation, ainsi que le découplage de la couche de nommage grâce à **JNDI (Java Naming and Directory Interface)**.

---

## Activité 5-1 : Service de conversion de devise (Java RMI)

### 1. Objectif
Concevoir une application client/serveur basique permettant à un client d'invoquer à distance une méthode de conversion de devises (Euros vers Dinars Tunisiens) hébergée sur un serveur RMI.

### 2. Architecture et Conception
L'application est divisée en deux projets distincts :

* **Projet Serveur (`RMIConversion_Server`) :**
  * `rmiService.IConversion` : Interface distante héritant de `java.rmi.Remote` et déclarant la méthode `convertirMontant(double mt)` (levant `RemoteException`).
  * `rmiService.ConversionImpl` : Classe d'implémentation étendant `UnicastRemoteObject` et contenant la logique métier de conversion.
  * `rmiServer.ConversionServer` : Classe principale qui démarre l'annuaire interne via `LocateRegistry.createRegistry(1099)` et publie l'instance du service sous le nom `"CONVERSION"` avec `Naming.rebind()`.

* **Projet Client (`RMIConversion_Client`) :**
  * Dépendance externe : Intégration de l'archive `conversionClient.jar` (contenant uniquement l'interface `IConversion`) dans le *Classpath*.
  * `rmiClient.ConversionClient` : Récupère la référence du *Stub* distant via `Naming.lookup()` et invoque la méthode de conversion de manière transparente.

### 3. Résultats et Captures d'écran
> *(Insérer ici les captures d'écran de l'exécution)*
> * **Capture 1 :** Démarrage du serveur `ConversionServer` dans la console.
> * **Capture 2 :** Exécution de `ConversionClient` affichant la conversion de 500 EUR.

### 4. Synthèse de l'activité 5-1
Cette première étape a permis de comprendre le mécanisme fondamental de Java RMI : la séparation stricte entre le contrat (interface partagée via un JAR) et l'implémentation (conservée uniquement sur le serveur). La communication réseau est entièrement gérée par les couches *Stub* et *Skeleton*.

---

## Activité 5-2 : Service de banque avec RMI et JNDI

### 1. Objectif
Développer un système centralisé de gestion de comptes bancaires permettant la création et la consultation de comptes à distance, tout en remplaçant l'API de nommage native (`Naming`) par l'interface standard **JNDI**.

### 2. Architecture et Conception
* **Projet Serveur (`RMIBanque_Server`) :**
  * `metier.Compte` : Entité métier caractérisée par un `code`, un `solde` et une `dateCreation`. Elle implémente l'interface `java.io.Serializable` afin de permettre le transfert de ses instances sur le réseau.
  * `rmiService.IBanque` : Interface distante exposant `creerCompte(Compte c)` et `getInfoCompte(int code)`.
  * `rmiService.BanqueImpl` : Implémentation du service gérant les comptes en mémoire via une structure `HashMap` synchronisée (évitant les doublons de codes).
  * `src/jndi.properties` : Fichier de configuration spécifiant la fabrique de contexte (`RegistryContextFactory`) et l'URL du fournisseur (`rmi://localhost:1099`).
  * `rmiServer.BanqueServer` : Initialise le registre RMI et enregistre le service auprès de l'annuaire JNDI via `InitialContext.rebind("banqueService", banque)`.

* **Projet Client (`RMIBanque_Client`) :**
  * Dépendance externe : Importation de `banqueClient.jar` (contenant `IBanque` et `Compte`).
  * `src/jndi.properties` : Configuration identique au serveur permettant à `InitialContext` de localiser l'annuaire sans coder en dur l'adresse réseau.
  * `rmiClient.BanqueClient` : Localise le service via `ctx.lookup("banqueService")`, crée des instances de `Compte` et teste les opérations de création (y compris la gestion d'erreur en cas de code existant) et de consultation.

### 3. Résultats et Captures d'écran
> *(Insérer ici les captures d'écran de l'exécution)*
> * **Capture 1 :** Arborescence des projets et configuration du `Build Path` avec `banqueClient.jar`.
> * **Capture 2 :** Lancement de `BanqueServer`.
> * **Capture 3 :** Console de `BanqueClient` montrant la création des comptes, le rejet des doublons et l'affichage des détails des comptes.

### 4. Synthèse de l'activité 5-2
L'intégration de JNDI apporte une couche d'abstraction essentielle : l'application cliente et l'application serveur sont désormais découplées de l'adresse physique du registre RMI, celle-ci étant externalisée dans le fichier `jndi.properties`. Par ailleurs, la manipulation de la classe `Compte` a mis en évidence le rôle indispensable de la sérialisation pour le passage d'objets complexes par valeur dans un environnement distribué.

---

## Conclusion
Ce TP a permis de maîtriser la conception d'applications réparties en Java. Le passage d'une implémentation RMI brute (Activité 5-1) à une architecture utilisant la sérialisation d'objets métiers et l'annuaire JNDI (Activité 5-2) illustre les bonnes pratiques d'ingénierie logicielle pour concevoir des systèmes distribués modulaires, maintenables et évolutifs.
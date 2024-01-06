# Projet de gestion d'hôtel

Cette application console a pour but de gérer les réservations et commandes d'un hôtel grâce à un menu interactif où l'utilisateur saisit ce qu'il veut faire en tant que réceptionniste.

## Fonctionnalités obligatoires
1. Afficher les détails des chambres (4 types de chambres max).
2. Afficher la disponibilité des chambres.
4. Permettre les tâches de gestion des réservations des chambres : réservation, modification, annulation et suppression d’une réservation.
4. Commander un repas.
5. Enregistrer la facture lors de départ du client. Cette facture regroupe le prix de la chambre et la quantité des prestations consommées pendant le séjour.
6. Sérialiser les données dans un fichier pour stocker l’état actuel de l’hôtel (coordonnées du client, chambres réservées, repas commandés) une fois le programme terminé.

Des fonctionnalités ont été ajoutées telles que :
- Afficher les commandes d'un client
- Afficher les réservations annulées
- Afficher les factures

## Notions abordées
- Classes et objets, héritage et redéfinition de méthodes
- Gestion des fichiers avec des objets
- Les collections
- Les interfaces 
- La gestion des exceptions

## Quelques précisions
- Chaque action, concernant les réservations, les commandes de repas et l'enregistrement de factures, nécessitent de préciser le client pour lequel nous voulons réaliser ces opérations.
- La sérialisation nécessite l'utilisation de l'interface Serializable. Celle-ci est à implémenter dans toutes les classes sérialisables.
- Sur VM, il faut rajouter `@SupressWarnings("unchecked")` pour éviter d'avoir des warnings dû à des méthodes unchecked ou unsafe
- Il faut également ajouter un `private static final long serialVersionUID = 1L` dans la classe principale

## Pour lancer le programme sur Linux

### Installation de Java sur la VM
- Au préalable, il faut avoir installé un Java JDK (et éventuellement un JRE) grâce aux commandes suivantes :\
`sudo apt-get install default-jdk`\
`sudo apt-get install default-jre`
- Vérifier l'installation : `java -version`
- Configurer la variable d'environnement : `sudo nano /etc/environment`
- Coller ceci à la fin du fichier : `JAVA_HOME="/lib/jvm/java-11-openjdk-amd64/bin/java"`
- Recharger la configuration : `source /etc/environment`
- Essayer d'afficher la variable JAVA_HOME : `echo $JAVA_HOME`

### Exécution du programme
- Créer un dossier dans lequel mettre les fichiers source (packages contenant les classes, HotelApp.java ainsi que donneesHotel.ser)
- Sur un terminal, se placer dans ce dossier
- Compiler avec la commande `javac HotelApp.java`
- Exécuter avec la commande `java HotelApp`

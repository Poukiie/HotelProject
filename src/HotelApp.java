import chambres.*;
import commande.CommandeRepas;
import hotel.Client;
import commande.Plat;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class HotelApp {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedList<Chambre> chambres = createChambres();
        LinkedList<Client> clients = createClients();
        LinkedList<Plat> plats = createPlats();
//        File file = new File("donneesHotel.txt");

        // TODO : pour plus tard, lire le fichier et créer les chambres dans la méthode createChambres()
        // TODO : pareil pour les clients dans la méthode createClients()

        System.out.println("Bienvenue à l'hôtel Goethe.");
        afficherMenu();
        String choice = "";

        while (!choice.equals("9")) {
            System.out.print("> ");
            choice = scan.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Liste des chambres :");
                    for (Chambre chambre : chambres) {
                        System.out.println(chambre.toString());
                    }
                    break;
                case "2":
                    System.out.println("Liste des chambres disponibles :");
                    for (Chambre chambre : chambres) {
                        if (!chambre.getEstAttribuee()) {
                            System.out.println(chambre.toString());
                        }
                    }
                    break;
                case "3":
                    System.out.println("Liste des clients :");
                    for (Client client : clients) {
                        System.out.println(client);
                    }
                    break;
                case "4":
                    System.out.println("Sélectionnez le client qui souhaite réserver :");
                    afficherClients(clients);
                    String clientChoisi = scan.nextLine();
                    System.out.println("Quelle chambre souhaitez-vous réserver ?");
                    afficherChambres(chambres);
                    String chambreChoisie = scan.nextLine();
                    // TODO: demander les dates de début et de fin de la réservation
                    // TODO : vérifier que la chambre est disponible
                    // TODO : créer la réservation

                    break;
                case "5":
                case "6":
                    System.out.println("Sélectionnez le client qui souhaite commander un repas :");
                    afficherClients(clients);
                    clientChoisi = scan.nextLine();
                    Client clientChoisiRepas = trouverClientParNom(clients, clientChoisi);
                    Plat platChoisi = null;
                    if (clientChoisiRepas != null) {
                        System.out.println("Liste des plats disponibles :");
                        afficherPlatsDisponibles(plats);

                        System.out.println("Quel plat souhaitez-vous commander ?");
                        String nomPlat = scan.nextLine().trim(); //trim ici pour enlever les espaces et éviter confusion
                        platChoisi = trouverPlatParNom(plats, nomPlat);
                    }
                    // demander le nom du plat et la quantité
                    if (platChoisi != null) {
                        System.out.println("Quantité : ");
                        int quantitePlat = scan.nextInt();
                        // créer la commande
                        clientChoisiRepas.commanderRepas(platChoisi, quantitePlat);
                    } else {
                        System.out.println("Plat introuvable.");
                    }
                    break;
                case "7":
                    System.out.println("Choisissez le client dont vous voulez afficher les commandes :");
                    afficherClients(clients);
                    String clientChoisiPourCommandes = scan.nextLine();
                    Client clientCommandes = trouverClientParNom(clients, clientChoisiPourCommandes);
                    if (clientCommandes != null) {
                        LinkedList<CommandeRepas> commandes = clientCommandes.getCommandes();
                        if (!commandes.isEmpty()) {
                            System.out.println("Commandes passées pour le client " + clientCommandes.getNom() + " :");
                            for (CommandeRepas commande : commandes) {
                                System.out.println(commande);
                            }
                        } else {
                            System.out.println("Le client n'a aucune commande passée.");
                        }
                    }
                    break;
                case "8":
                    System.out.println("Pour quel client souhaitez-vous enregistrer la facture ?");
                    afficherClients(clients);
                    clientChoisi = scan.nextLine();
                    // TODO : enregistrer la facture du client
                    break;
                case "9":
                    System.out.println("Au revoir !");
                    // TODO: Sauvegarder les données
                    return;
                default:
                    System.out.println("Commande inconnue, veuillez réessayer.");
                    break;
            }
            afficherMenu();
        }
    }

    private static LinkedList<Chambre> createChambres() {
        LinkedList<Chambre> chambres = new LinkedList<>();
        ChambreLuxeDouble chamLuxeD1 = new ChambreLuxeDouble(201);
        ChambreLuxeDouble chamLuxeD2 = new ChambreLuxeDouble(202);
        ChambreLuxeSimple chamLuxeS1 = new ChambreLuxeSimple(203);
        ChambreLuxeSimple chamLuxeS2 = new ChambreLuxeSimple(204);
        ChambreNormaleDouble chamNormD1 = new ChambreNormaleDouble(101);
        ChambreNormaleDouble chamNormD2 = new ChambreNormaleDouble(102);
        ChambreNormaleSimple chamNormS1 = new ChambreNormaleSimple(103);
        ChambreNormaleSimple chamNormS2 = new ChambreNormaleSimple(104);
        chambres.add(chamLuxeD1);
        chambres.add(chamLuxeD2);
        chambres.add(chamLuxeS1);
        chambres.add(chamLuxeS2);
        chambres.add(chamNormD1);
        chambres.add(chamNormD2);
        chambres.add(chamNormS1);
        chambres.add(chamNormS2);
        return chambres;
    }

    private static LinkedList<Client> createClients() {
        LinkedList<Client> clients = new LinkedList<>();
        Client client1 = new Client("Yaya", null);
        Client client2 = new Client("Mimi", null);
        Client client3 = new Client("Nana", null);
        Client client4 = new Client("Gaga", null);
        Client client5 = new Client("Ryu", null);
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        clients.add(client4);
        clients.add(client5);
        return clients;
    }

    private static LinkedList<Plat> createPlats() {
        LinkedList<Plat> plats = new LinkedList<>();
        Plat plat1 = new Plat("Pizza", 12.5);
        Plat plat2 = new Plat("Burger", 10.5);
        Plat plat3 = new Plat("Salade", 6.5);
        plats.add(plat1);
        plats.add(plat2);
        plats.add(plat3);
        return plats;
    }

    private static Client trouverClientParNom(LinkedList<Client> clients, String nomClient) {
        for (Client client : clients) {
            if (client.getNom().equalsIgnoreCase(nomClient)) {
                return client;
            }
        }
        return null;
    }

    private static Plat trouverPlatParNom(LinkedList<Plat> plats, String nomPlat) {
        for (Plat plat : plats) {
            if (plat.getNomPlat().equalsIgnoreCase(nomPlat)) {
                return plat;
            }
        }
        return null;
    }

    private static void afficherClients(LinkedList<Client> clients) {
        for (Client client : clients) {
            System.out.println(client.getNom());
        }
    }

    private static void afficherChambres(LinkedList<Chambre> chambres) {
        for (Chambre chambre : chambres) {
            System.out.println(chambre);
        }
    }

    private static void afficherPlatsDisponibles(LinkedList<Plat> plats) {
        for (Plat plat : plats) {
            System.out.println(plat);
        }
    }

    private static void afficherMenu() {
        System.out.println("Que voulez-vous faire ? (tapez le numéro de votre choix");
        System.out.println("1. Afficher les chambres");
        System.out.println("2. Afficher les chambres disponibles");
        System.out.println("3. Afficher les clients");
        System.out.println("4. Réserver une chambre");
        System.out.println("5. Gérer une réservation"); // modifier, annuler et supprimer une réservation
        System.out.println("6. Commander un repas");
        System.out.println("7. Afficher les commandes d'un client");
        System.out.println("8. Enregistrer la facture d'un client");
        System.out.println("9. Quitter l'application");
    }
}
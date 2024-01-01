import chambres.*;
import hotel.Client;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class HotelApp {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedList<Chambre> chambres = createChambres();
        LinkedList<Client> clients = createClients();
//        File file = new File("donneesHotel.txt");

        // TODO : pour plus tard, lire le fichier et créer les chambres dans la méthode createChambres()
        // TODO : pareil pour les clients dans la méthode createClients()

        System.out.println("Bienvenue à l'hôtel Goethe. Que voulez-vous faire ? (tapez le numéro de votre choix)");
        System.out.println("1. Afficher les chambres");
        System.out.println("2. Afficher les chambres disponibles");
        System.out.println("3. Afficher les clients");
        System.out.println("4. Réserver une chambre");
        // TODO : rajouter des options pour modifier, annuler et supprimer une réservation
        System.out.println("5. Commander un repas");
        System.out.println("6. Afficher les commandes d'un client");
        System.out.println("7. Enregistrer la facture d'un client");
        System.out.println("8. Quitter l'application");
        String choice = "";

        while (!choice.equals("8")) {
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
                    afficherClients(clients);
                    break;
                case "4":
                    System.out.println("Sélectionnez le client qui souhaite réserver :");
                    afficherClients(clients);
                    String clientChoisi = scan.nextLine();
                    // TODO : vérifier que le client n'a pas déjà une réservation
                    System.out.println("Quelle chambre souhaitez-vous réserver ?");
                    afficherChambres(chambres);
                    String chambreChoisie = scan.nextLine();
                    // TODO: demander les dates de début et de fin de la réservation
                    // TODO : vérifier que la chambre est disponible
                    // TODO : créer la réservation

                    break;
                case "5":
                    System.out.println("Sélectionnez le client qui souhaite commander un repas :");
                    afficherClients(clients);
                    clientChoisi = scan.nextLine();
                    // TODO : demander le nom du plat et la quantité
                    // TODO : créer la commande
                    break;
                case "6":
                    System.out.println("Choisissez le client dont vous voulez afficher les commandes :");
                    afficherClients(clients);
                    clientChoisi = scan.nextLine();
                    // TODO : afficher les commandes du client
                    break;
                case "7":
                    System.out.println("Pour quel client souhaitez-vous enregistrer la facture ?");
                    afficherClients(clients);
                    clientChoisi = scan.nextLine();
                    // TODO : enregistrer la facture du client
                    break;
                case "8":
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Commande inconnue, veuillez réessayer.");
                    break;
            }
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

    private static void afficherClients(LinkedList<Client> clients) {
        for (Client client : clients) {
            System.out.println(client.toString());
        }
    }

    private static void afficherChambres(LinkedList<Chambre> chambres) {
        for (Chambre chambre : chambres) {
            System.out.println(chambre.toString());
        }
    }
}
import chambres.*;
import commande.CommandeRepas;
import exception.ChambreNonDisponible;
import hotel.Client;
import commande.Plat;
import hotel.Facture;
import hotel.Reservation;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Scanner;


public class HotelApp implements Serializable {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedList<Chambre> chambres;
        LinkedList<Client> clients;
        LinkedList<Plat> plats;
        LinkedList<Facture> factures;
        LinkedList<Reservation> reservationsAnnulees;

        try {
            FileInputStream file = new FileInputStream("donneesHotel.ser");
            ObjectInputStream ois = new ObjectInputStream(file);

            clients = (LinkedList<Client>) ois.readObject();
            chambres = (LinkedList<Chambre>) ois.readObject();
            plats = (LinkedList<Plat>) ois.readObject();
            factures = (LinkedList<Facture>) ois.readObject();
            reservationsAnnulees = (LinkedList<Reservation>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            // En cas d'erreur, initialiser les listes normalement
            clients = createClients();
            chambres = createChambres();
            plats = createPlats();
            factures = new LinkedList<>();
            reservationsAnnulees = new LinkedList<>();
        }

        System.out.println("Bienvenue à l'hôtel Goethe.");
        afficherMenu();
        String choice = "";

        while (!choice.equals("10")) {
            System.out.print("> ");
            choice = scan.nextLine();
            switch (choice) {
                // Afficher les chambres
                case "1":
                    System.out.println("Liste des chambres :");
                    for (Chambre chambre : chambres) {
                        System.out.println(chambre);
                    }
                    break;
                // Afficher les chambres disponibles
                case "2":
                    System.out.println("Liste des chambres disponibles :");
                    for (Chambre chambre : chambres) {
                        if (!chambre.getEstAttribuee()) {
                            System.out.println(chambre);
                        }
                    }
                    break;
                // Afficher les clients
                case "3":
                    System.out.println("Liste des clients :");
                    for (Client client : clients) {
                        System.out.println(client);
                    }
                    break;
                // Réserver une chambre
                case "4":
                    System.out.println("Sélectionnez le client qui souhaite réserver :");
                    Client clientChoisi = choisirClient(clients, scan);

                    while (clientChoisi == null || clientChoisi.getReservation() != null) {
                        System.out.println("Client introuvable ou réservation déjà existante. Veuillez réessayer.");
                        clientChoisi = choisirClient(clients, scan);
                    }

                    Chambre chambreReservee = verifierChambreReservation(chambres, scan);
                    creerReservation(clientChoisi, chambreReservee, scan);
                    break;
                // Gérer une réservation
                case "5":
                    System.out.println("Gérer une réservation pour quel client ?");
                    clientChoisi = choisirClient(clients, scan);

                    if (clientChoisi != null && clientChoisi.getReservation() != null) {
                        System.out.println("Réservation actuelle du client :");
                        System.out.println(clientChoisi.getReservation());

                        System.out.println("Que voulez-vous faire ?");
                        System.out.println("1. Modifier la réservation");
                        System.out.println("2. Annuler la réservation");
                        System.out.println("3. Supprimer la réservation");
                        System.out.print("> ");
                        String choixGestion = scan.nextLine();

                        switch (choixGestion) {
                            case "1":
                                LocalDate dateDebutModification = null;
                                LocalDate dateFinModification = null;

                                System.out.println("Réservation actuelle :");
                                System.out.println(clientChoisi.getReservation());

                                afficherChambres(chambres);
                                System.out.println("Entrez le numéro de la nouvelle chambre :");
                                Chambre nouvelleChambre = choisirChambre(chambres, scan);
                                while (nouvelleChambre == null) {
                                    System.out.println("Chambre introuvable. Veuillez réessayer.");
                                    nouvelleChambre = choisirChambre(chambres, scan);
                                }

                                System.out.println("Entrez la nouvelle date de début de la réservation (format jj/MM/yyyy) :");
                                System.out.print("> ");
                                String nouvelleDebut = scan.nextLine();
                                dateDebutModification = parseDate(dateDebutModification, nouvelleDebut, scan);

                                System.out.println("Entrez la nouvelle date de fin de la réservation (format jj/MM/yyyy) :");
                                System.out.print("> ");
                                String nouvelleFin = scan.nextLine();
                                dateFinModification = parseDate(dateFinModification, nouvelleFin, scan);
                                while (dateDebutModification.isAfter(dateFinModification)) {
                                    System.out.println("La date de début doit être avant la date de fin. Veuillez réessayer.");
                                    System.out.print("> ");
                                    nouvelleFin = scan.nextLine();
                                    dateFinModification = parseDate(dateFinModification, nouvelleFin, scan);
                                }

                                try {
                                    clientChoisi.modifierReservation(dateDebutModification, dateFinModification, nouvelleChambre);
                                } catch (ChambreNonDisponible e) {
                                    System.out.println(e.getMessage());
                                }
                                break;
                            case "2": // annuler : état annulé + trace dans le fichier avant de la supprimer
                                clientChoisi.annulerReservation();
                                reservationsAnnulees.add(clientChoisi.getReservation());

                                try {
                                    FileOutputStream fileOutput = new FileOutputStream("donneesHotel.ser");
                                    ObjectOutputStream oos = new ObjectOutputStream(fileOutput);

                                    oos.writeObject(reservationsAnnulees);
                                    oos.close();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }

                                clientChoisi.supprimerReservation();
                                System.out.println("Réservation annulée avec succès.");
                                break;
                            case "3": // supprimer : cette réservation n'apparait pas dans le fichier
                                clientChoisi.supprimerReservation();
                                System.out.println("Réservation supprimée avec succès.");
                                break;
                        }
                    } else {
                        System.out.println("Client introuvable ou aucune réservation existante.");
                    }
                    break;
                // Commander un repas
                case "6":
                    System.out.println("Sélectionnez le client qui souhaite commander un repas :");
                    clientChoisi = choisirClient(clients, scan);
                    Plat platChoisi;

                    while (clientChoisi == null) {
                        System.out.println("Client introuvable. Veuillez réessayer.");
                        clientChoisi = choisirClient(clients, scan);
                    }

                    System.out.println("Liste des plats disponibles :");
                    afficherPlatsDisponibles(plats);
                    System.out.println("Quel plat souhaitez-vous commander ?");
                    platChoisi = choisirPlat(plats, scan);

                    while (platChoisi == null) {
                        System.out.println("Plat introuvable. Veuillez réessayer.");
                        platChoisi = choisirPlat(plats, scan);
                    }

                    System.out.println("Quantité : ");
                    System.out.print("> ");
                    int quantitePlat = scan.nextInt();
                    scan.nextLine();
                    clientChoisi.commanderRepas(platChoisi, quantitePlat);
                    break;
                // Afficher les commandes d'un client
                case "7":
                    System.out.println("Choisissez le client dont vous voulez afficher les commandes :");
                    clientChoisi = choisirClient(clients, scan);
                    while (clientChoisi == null) {
                        System.out.println("Client introuvable. Veuillez réessayer.");
                        clientChoisi = choisirClient(clients, scan);
                    }
                    LinkedList<CommandeRepas> commandes = clientChoisi.getCommandes();
                    if (!commandes.isEmpty()) {
                        System.out.println("Commandes passées pour le client " + clientChoisi.getNom() + " :");
                        for (CommandeRepas commande : commandes) {
                            System.out.println(commande);
                        }
                    } else {
                        System.out.println("Le client n'a aucune commande passée.");
                    }
                    break;
                case "8":
                    System.out.println("Liste des réservations annulées :");
                    for (Reservation reservation : reservationsAnnulees) {
                        System.out.println(reservation);
                    }
                    break;
                // Enregistrer la facture d'un client
                case "9":
                    System.out.println("Pour quel client souhaitez-vous enregistrer la facture ?");
                    clientChoisi = choisirClient(clients, scan);
                    while (clientChoisi == null) {
                        System.out.println("Client introuvable. Veuillez réessayer.");
                        clientChoisi = choisirClient(clients, scan);
                    }
                    if (clientChoisi.getReservation() != null) {
                        Facture facture = new Facture(clientChoisi);
                        factures.add(facture);
                        System.out.println(facture);
                        clientChoisi.supprimerReservation(); // il part
                        break;
                    }
                    else {
                        System.out.println("Le client n'a pas de réservation.");
                        break;
                    }
                case "10":
                    System.out.println("Au revoir !");
                    // Sauvegarder les données
                    serialiser(clients, chambres, plats, factures, reservationsAnnulees);
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
        Plat plat1 = new Plat("Pizza", 10.0);
        Plat plat2 = new Plat("Burger", 8.5);
        Plat plat3 = new Plat("Salade", 3.5);
        Plat plat4 = new Plat("Fraisier", 5.5);
        Plat plat5 = new Plat("Café + croissant", 4.0);
        plats.add(plat1);
        plats.add(plat2);
        plats.add(plat3);
        plats.add(plat4);
        plats.add(plat5);
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

    private static LocalDate parseDate(LocalDate date, String dateToParse, Scanner scan) {
        try {
            date = LocalDate.parse(dateToParse, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Format de date invalide. Veuillez réessayer.");
            System.out.print("> ");
            dateToParse = scan.nextLine();
            date = parseDate(date, dateToParse, scan);
        }
        return date;
    }

    private static Chambre verifierChambreReservation(LinkedList<Chambre> chambres, Scanner scan) {
        afficherChambres(chambres);
        System.out.println("Quelle chambre souhaitez-vous réserver ?");
        Chambre chambreReservee = choisirChambre(chambres, scan);

        while (chambreReservee == null) {
            System.out.println("Chambre introuvable. Veuillez réessayer.");
            chambreReservee = choisirChambre(chambres, scan);
        }
        return chambreReservee;
    }

    private static void creerReservation(Client clientChoisi, Chambre chambreReservee, Scanner scan) {
        LocalDate dateDebut = null;
        LocalDate dateFin = null;
        System.out.println("Entrez la date de début de la réservation (format jj/MM/yyyy) :");
        System.out.print("> ");
        String debut = scan.nextLine();
        dateDebut = parseDate(dateDebut, debut, scan); // essayer de parser la date pour voir si c'est correct

        System.out.println("Entrez la date de fin de la réservation (format jj/MM/yyyy) :");
        System.out.print("> ");
        String fin = scan.nextLine();
        dateFin = parseDate(dateFin, fin, scan);
        while (dateDebut.isAfter(dateFin)) {
            System.out.println("La date de début doit être avant la date de fin. Veuillez réessayer.");
            System.out.print("> ");
            fin = scan.nextLine();
            dateFin = parseDate(dateFin, fin, scan);
        }

        try {
            clientChoisi.reserver(chambreReservee, dateDebut, dateFin);
        } catch (ChambreNonDisponible e) {
            System.out.println(e.getMessage());
        }
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

    private static Client choisirClient(LinkedList<Client> clients, Scanner scan) {
        afficherClients(clients);
        System.out.print("> ");
        String clientChoisiNom = scan.nextLine();
        return trouverClientParNom(clients, clientChoisiNom);
    }

    private static Chambre choisirChambre(LinkedList<Chambre> chambres, Scanner scan) {
        System.out.print("> ");
        String chambreChoisie = scan.nextLine();
        return trouverChambreParNumero(chambres, chambreChoisie);
    }

    private static Plat choisirPlat(LinkedList<Plat> plats, Scanner scan) {
        System.out.print("> ");
        String platChoisi = scan.nextLine();
        return trouverPlatParNom(plats, platChoisi);
    }

    private static Chambre trouverChambreParNumero(LinkedList<Chambre> chambres, String numeroChambre) {
        for (Chambre chambre : chambres) {
            if (String.valueOf(chambre.getNumero()).equals(numeroChambre)) {
                return chambre;
            }
        }
        return null;
    }

    private static void afficherMenu() {
        System.out.println("Que voulez-vous faire ? (tapez le numéro de votre choix)");
        System.out.println("1. Afficher les chambres");
        System.out.println("2. Afficher les chambres disponibles");
        System.out.println("3. Afficher les clients");
        System.out.println("4. Réserver une chambre");
        System.out.println("5. Gérer une réservation"); // modifier, annuler et supprimer une réservation
        System.out.println("6. Commander un repas");
        System.out.println("7. Afficher les commandes d'un client");
        System.out.println("8. Afficher les réservations annulées");
        System.out.println("9. Enregistrer la facture d'un client");
        System.out.println("10. Quitter l'application");
    }

    private static void serialiser(LinkedList<Client> clients, LinkedList<Chambre> chambres,
                                           LinkedList<Plat> plats, LinkedList<Facture> factures,
                                           LinkedList<Reservation> reservationsAnnulees) {
        try {
            FileOutputStream file = new FileOutputStream("donneesHotel.ser");
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(clients);
            oos.writeObject(chambres);
            oos.writeObject(plats);
            oos.writeObject(factures);
            oos.writeObject(reservationsAnnulees);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
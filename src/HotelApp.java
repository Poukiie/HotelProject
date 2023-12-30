import chambres.Chambre;
import hotel.Client;

import java.util.LinkedList;
import java.util.Scanner;

public class HotelApp {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedList<Chambre> chambres = new LinkedList<>();
        Client client = new Client(1, "test", null);
        System.out.println("Bienvenue à l'hôtel");
    }
}
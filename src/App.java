import model.*;
import model.service.*;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GebruikerService gebruikerService = new GebruikerService();
        Gebruiker actieveGebruiker = null;

        // registreren en inloggen gedeelte
        while (actieveGebruiker == null) {
            System.out.println("Welkom bij FactuurRouter");
            System.out.println("[1] Registreren");
            System.out.println("[2] Inloggen");
            System.out.print("Maak je keuze: ");
            int keuze = scanner.nextInt();
            scanner.nextLine();

            if (keuze == 1) {
                System.out.print("Naam: ");
                String naam = scanner.nextLine();
                System.out.print("E-mailadres: ");
                String email = scanner.nextLine();
                System.out.print("Wachtwoord: ");
                String wachtwoord = scanner.nextLine();
                gebruikerService.registreerGebruiker(naam, email, wachtwoord);
                System.out.println("Registratie gelukt! Je kunt nu inloggen.");
            } else if (keuze == 2) {
                List<Gebruiker> gebruikers = gebruikerService.haalAlleGebruikers();
                InlogService inlogService = new InlogService(gebruikers);

                System.out.print("E-mail: ");
                String email = scanner.nextLine();
                System.out.print("Wachtwoord: ");
                String wachtwoord = scanner.nextLine();

                actieveGebruiker = inlogService.login(email, wachtwoord);

                if (actieveGebruiker == null) {
                    System.out.println("Foutieve inloggegevens! Probeer opnieuw.");
                } else {
                    System.out.println("Welkom, " + actieveGebruiker.getNaam() + "!");
                }
            } else {
                System.out.println("Ongeldige keuze.");
            }
        }

        // dashboard gedeelte
        boolean ingelogd = true;
        while (ingelogd) {
            System.out.println("\n--- Dashboard ---");
            System.out.println("[1] Centrale Inbox");
            System.out.println("[2] XML-facturen");
            System.out.println("[3] PDF-facturen");
            System.out.println("[4] Leveranciers");
            System.out.println("[5] Logboek");
            System.out.println("[6] Zoek facturen op type");
            System.out.println("[7] Zoek leveranciers op trefwoord");
            System.out.println("[0] Uitloggen");
            System.out.print("Maak je keuze: ");
            int keuze = scanner.nextInt();
            scanner.nextLine(); // enter opvangen

            switch (keuze) {
                case 1 -> toonInboxMetTerug(scanner, actieveGebruiker);
                case 2 -> toonFacturenMetTerug(scanner, "XML");
                case 3 -> toonFacturenMetTerug(scanner, "PDF");
                case 4 -> toonLeveranciersMetTerug(scanner);
                case 5 -> toonLogboekMetTerug(scanner);
                case 6 -> zoekFacturenOpType(scanner);
                case 7 -> zoekLeveranciersOpTrefwoord(scanner);
                case 0 -> {
                    System.out.println("Uitgelogd. Tot ziens!");
                    ingelogd = false;
                }
                default -> System.out.println("Ongeldige keuze. Probeer opnieuw.");
            }
        }
    }

    // Centrale inbox
    public static void toonInboxMetTerug(Scanner scanner, Gebruiker gebruiker) {
        System.out.println("\n--- Centrale Inbox ---");
        gebruiker.getInbox().toonInbox();
        wachtOpTerug(scanner);
    }

    // Facturen
    public static void toonFacturenMetTerug(Scanner scanner, String type) {
        FactuurLoader loader = new FactuurLoader();
        List<Factuur> facturen = loader.laadTestFacturen();

        System.out.println("\n--- " + type + "-Facturen ---");
        for (Factuur f : facturen) {
            if (f.getType().equalsIgnoreCase(type)) {
                f.printFactuur();
                System.out.println("----");
            }
        }
        wachtOpTerug(scanner);
    }

    // Leveranciers laten zien
    public static void toonLeveranciersMetTerug(Scanner scanner) {
        List<Leverancier> leveranciers = getVasteLeveranciers();
        System.out.println("\n--- Leveranciers ---");
        for (Leverancier l : leveranciers) {
            System.out.println("- " + l.getInfo());
        }
        wachtOpTerug(scanner);
    }

    // Logboek inhoud tonen
    public static void toonLogboekMetTerug(Scanner scanner) {
        List<Factuur> facturen = new FactuurLoader().laadTestFacturen();
        FactuurVerwerker verwerker = new FactuurVerwerker();
        List<Logboek> logs = verwerker.verwerkFacturen(facturen);

        System.out.println("\n--- Logboek ---");
        for (Logboek log : logs) {
            System.out.println(log.getInfo());
        }
        wachtOpTerug(scanner);
    }

    // Zoek facturen
    public static void zoekFacturenOpType(Scanner scanner) {
        System.out.print("Voer factuurtype in (bijv. PDF of XML): ");
        String type = scanner.nextLine();

        FactuurLoader loader = new FactuurLoader();
        List<Factuur> facturen = loader.laadTestFacturen();

        System.out.println("\n--- Zoekresultaat voor: " + type + " ---");
        for (Factuur f : facturen) {
            if (f.getType().equalsIgnoreCase(type)) {
                f.printFactuur();
                System.out.println("----");
            }
        }
        wachtOpTerug(scanner);
    }

    // Zoek leveranciers
    public static void zoekLeveranciersOpTrefwoord(Scanner scanner) {
        System.out.print("Voer trefwoord in (bijv. naam of format): ");
        String trefwoord = scanner.nextLine().toLowerCase();

        List<Leverancier> leveranciers = getVasteLeveranciers();
        boolean resultaatGevonden = false;

        System.out.println("\n--- Zoekresultaten ---");
        for (Leverancier l : leveranciers) {
            if (l.getNaam().toLowerCase().contains(trefwoord) ||
                    l.getFormat().toString().toLowerCase().contains(trefwoord)) {
                System.out.println("- " + l.getInfo());
                resultaatGevonden = true;
            }
        }

        if (!resultaatGevonden) {
            System.out.println("Geen resultaten gevonden voor: " + trefwoord);
        }

        wachtOpTerug(scanner);
    }

    // Vaste leverancierslijst
    public static List<Leverancier> getVasteLeveranciers() {
        return List.of(
                new Leverancier("ATAG", FactuurFormat.PDF),
                new Leverancier("NOBILIA", FactuurFormat.XML),
                new Leverancier("WANBOUND", FactuurFormat.PDF),
                new Leverancier("SAP", FactuurFormat.PDF),
                new Leverancier("Oracle", FactuurFormat.XML),
                new Leverancier("Salesforce", FactuurFormat.PDF),
                new Leverancier("Workday", FactuurFormat.XML),
                new Leverancier("Stripe", FactuurFormat.PDF),
                new Leverancier("Adyen", FactuurFormat.PDF),
                new Leverancier("Xero", FactuurFormat.PDF)
        );
    }

    // Terugknop
    public static void wachtOpTerug(Scanner scanner) {
        System.out.println("\n[Druk op enter om terug te keren naar dashboard]");
        scanner.nextLine();
    }
}

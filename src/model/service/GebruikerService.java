package model.service;

import model.Gebruiker; // mensen in het systeem
import model.Email;// om emails te koppelen
import utils.DatabaseVerbinding; // om  te praten met de database

import java.sql.*; // om met sql te kunnen werken
import java.util.ArrayList;
import java.util.List;


public class GebruikerService {

    public List<Gebruiker> haalAlleGebruikers() {

        List<Gebruiker> gebruikers = new ArrayList<>();


        String sql = "SELECT * FROM gebruiker";

        try (Connection conn = DatabaseVerbinding.getConnection();// databse verbinding
             Statement stmt = conn.createStatement();// sql query uit te voeren
             ResultSet rs = stmt.executeQuery(sql)) {


            // voor elk rij in het resultaat dus voor elke gebruiker
            // haal de gegevens van die gebruiker op
            while (rs.next()) {
                String naam = rs.getString("naam");
                String emailadres = rs.getString("emailadres");
                String wachtwoord = rs.getString("wachtwoord");


                // gebruiker object aanmaken
                Gebruiker g = new Gebruiker(naam, emailadres, wachtwoord);

                // automatisch de inbox vullen
                vulEmailsVoorGebruiker(g);
                gebruikers.add(g);
            }


            // foutmelding als er iets misgaat
        } catch (SQLException e) {
            System.out.println("Fout bij ophalen gebruikers: " + e.getMessage());
        }


        return gebruikers;
    }


    private void vulEmailsVoorGebruiker(Gebruiker gebruiker) {
        // zoek de emails die gebruiker id overeenkomt met emailadres
        String sql = "SELECT * FROM email WHERE gebruiker_id = (SELECT id FROM gebruiker WHERE emailadres = ?)";

        try (Connection conn = DatabaseVerbinding.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            //zet het email adres in de query en dan uitvoeren
            stmt.setString(1, gebruiker.getEmailadres());
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                // 1-haal de gegevens op van de email
                String onderwerp = rs.getString("onderwerp");
                String inhoud = rs.getString("inhoud");
                String afzender = rs.getString("afzender");

                // 2- maak een email object aan
                Email email = new Email(onderwerp, inhoud, afzender, gebruiker);
                //3- toevoegen aan de inbox van de gebruiker
                gebruiker.getInbox().voegEmailToe(email);
            }


            //foutmelding
        } catch (SQLException e) {
            System.out.println("Fout bij ophalen e-mails voor " + gebruiker.getNaam() + ": " + e.getMessage());
        }
    }

    public void registreerGebruiker(String naam, String email, String wachtwoord) {
        String sql = "INSERT INTO gebruiker (naam, emailadres, wachtwoord) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseVerbinding.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, naam);
            stmt.setString(2, email);
            stmt.setString(3, wachtwoord);
            stmt.executeUpdate();

            System.out.println("U bent succesvol geregistreerd.");

        } catch (SQLException e) {
            System.out.println("Fout bij registreren: " + e.getMessage());
        }
    }



}

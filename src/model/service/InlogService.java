package model.service;

import model.Gebruiker;
import java.util.List;


public class InlogService {
    private List<Gebruiker> gebruikers;// een lijst van gebruikers die mogen inloggen


    public InlogService(List<Gebruiker> gebruikers) {
        this.gebruikers = gebruikers;
    }

    // Loginmethode
    public Gebruiker login(String email, String wachtwoord) {
        for (Gebruiker gebruiker : gebruikers) {
            //voorwaarde
            if (gebruiker.getEmailadres().equalsIgnoreCase(email) &&
                    gebruiker.getWachtwoord().equals(wachtwoord)) {
                return gebruiker;
            }
        }
        return null; // Geen match
    }
}


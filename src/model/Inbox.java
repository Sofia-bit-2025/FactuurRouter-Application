package model;

import java.util.ArrayList;
import java.util.List;

 . dit is postvak
public class Inbox {
    private String naam;
    private List<Email> emails; /
    //een lijst van email objecten die in deze inbox worden verzameld

    // constructor voor object inbox aanmaken
    public Inbox(String naam) {
        this.naam = naam;
        this.emails = new ArrayList<>();//lege lijst zodat emails aan toegevoed kunnen worden
    }

    public List<Email> getEmails() {
        return emails;
    }


    public String getNaam() {
        return naam;
    }

    //gedrag email toevoegen -> hier stop je new mail in de inbox
    public void voegEmailToe(Email email) {
        emails.add(email);
    }

    // de printfunctie -> print de naam van de inbox plus inhoud ervan
    public void toonInbox() {
        System.out.println("Inbox " + naam);
        if (emails.isEmpty()) {
            System.out.println("Je inbox is leeg.");
        } else {
            for (Email email : emails) {


                System.out.println("- " + email.getInfo());
            }
        }
    }

}



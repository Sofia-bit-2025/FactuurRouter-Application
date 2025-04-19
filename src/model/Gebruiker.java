package model;


//de blauwdruk voor een gebruiker object
public class Gebruiker {
    private String naam;
    private String emailadres;
    private String wachtwoord;
    private Inbox inbox;//compositie -> elke gerbuiker heeft eigen inbox

    //object Gebruiker aanmaken
    public Gebruiker(String naam, String emailadres, String wachtwoord) {
        this.naam = naam;
        this.emailadres = emailadres;
        this.wachtwoord = wachtwoord;
        this.inbox = new Inbox("Inbox van " + naam); // automatich wordt er een inbox aangemaakt
    }

    //getters
    public String getNaam() {
        return naam;
    }

    public String getEmailadres() {
        return emailadres;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    //een overzicht van de gebruiler tonen
    public String getInfo() {
        return naam + " - " + emailadres;
    }

//getter om togeang te krijgen tot de inbox van de gebruiker
    public Inbox getInbox() {
        return inbox;
    }
}

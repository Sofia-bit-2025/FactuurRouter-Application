package model;

//de blauwdruk voor een emailObject
public class Email {
    private String onderwerp;
    private String inhoud;//
    private String afzender;//
    private Gebruiker gebruiker;//


   //constructor hier maak ik email object aan
    public Email(String onderwerp, String inhoud, String afzender, Gebruiker gebruiker) {
        this.onderwerp = onderwerp;
        this.inhoud = inhoud;
        this.afzender = afzender;
        this.gebruiker = gebruiker;
    }

    //getter het onderwerp van de mail opvragen
    public String getOnderwerp() {
        return onderwerp;
    }

    //getter de inhoud/tekst van de email opvragen
    public String getInhoud() {
        return inhoud;
    }

    //getter vragen wie het bericht heeft verstuurd
    public String getAfzender() {
        return afzender;
    }

    //getter vragen wie het bericht heeft ontvangen
    public Gebruiker getGebruiker() {
        return gebruiker;
    }


    // alle info in een overzicht tonnen
    public String getInfo() {
        return "E-mail van " + afzender + " aan " + gebruiker.getNaam() + ": " + onderwerp;
    }
}

package model;

//de blauwdruk van object bestand
public class Bestand {
    private String bestandsnaam; //eigenschap attribuut slaat de naam van het bestand op
    private String type; // type van het bestand -> XML of pdf
    private int grootteKb;//om te tonen hoe zwaar het bestand is.


    //Constructor, heir maak ik bestand object aan
    public Bestand(String bestandsnaam, String type, int grootteKb) {
        this.bestandsnaam = bestandsnaam;
        this.type = type;
        this.grootteKb = grootteKb;
    }

    // getter om de naam van het bestand op te vragen
    public String getBestandsnaam() {
        return bestandsnaam;
    }

    //getter om het type van het bestand op te vragen
    public String getType() {
        return type;
    }

    // getter om de grootte van het bestand op te vragen
    public int getGrootteKb() {
        return grootteKb;
    }

    // extra functie om alle bovenste info op een regel te printen
    public String getInfo() {
        return bestandsnaam + " (" + type + ", " + grootteKb + " KB)";
    }
}

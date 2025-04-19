package model;


public class Leverancier {
    private String naam; // heeft een naam
    private FactuurFormat format;// werkt met een format xml of pdf /interface


    //construtor voor leverancier object
    public Leverancier(String naam, FactuurFormat format) {
        this.naam = naam;
        this.format = format;
    }


    public String getNaam() {
        return naam;
    }


    public FactuurFormat getFormat() {
        return format;
    }



    public String getInfo() {
        return naam + " gebruikt factuurformaat: " + format;
    }
}

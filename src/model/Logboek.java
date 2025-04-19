package model;

import java.time.LocalDateTime; //geeft ecxacte tijd

//klasse maken voor logboek om een actie vast te leggen
public class Logboek {
    private String actie;// vertelt in tekst wat er gebeurd is
    private LocalDateTime tijdstip;
    private Factuur factuur;
    private Gebruiker gebruiker;


    //constructor
    public Logboek(String actie, Factuur factuur) {
        this.actie = actie;
        this.factuur = factuur;
        this.tijdstip = LocalDateTime.now(); // automatisch huidige tijd
    }

    // geeft een beschrijving van wat er allemaal in het logboek staat
    public String getInfo() {
        return "[" + tijdstip + "] " + actie + " -> Factuurnummer: " + factuur.getFactuurnummer();
    }
}

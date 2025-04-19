package model;

import java.time.LocalDate; //de klasse local date ophalen


public abstract class Factuur implements VerwerkbareFactuur{
    private String factuurnummer;
    private LocalDate datum;//
    private Leverancier leverancier;/
    private Bestand bestand;/

   //constructor  object factuur
    public Factuur(String factuurnummer, LocalDate datum, Leverancier leverancier, Bestand bestand) {
        // hier stop ik de waarden in juiste variabelen
        this.factuurnummer = factuurnummer;
        this.datum = datum;
        this.leverancier = leverancier; //
        this.bestand = bestand;//
    }

    //getter om factuurnummer op te vragen
    public String getFactuurnummer() {
        return factuurnummer;
    }

    //getter de datum van de factuur opvragen
    public LocalDate getDatum() {
        return datum;
    }

    // getter ->van welke leverancier deze factuur komt?
    public Leverancier getLeverancier() {
        return leverancier;
    }

// XML of pdf van de factuur opvragen
    public Bestand getBestand() {
        return bestand;
    }

    // alle info van de factuut op het scherm te tonen
    public void printFactuur() {
        System.out.println("Factuurnummer: " + factuurnummer);
        System.out.println("Datum: " + datum);
        System.out.println("Leverancier: " + leverancier.getInfo());
        System.out.println("Bestand: " + bestand.getInfo());
    }

    // controle is het bestandtype van de factuur hetzelfde als die van leverancier
        @Override
        public boolean isGeldig() {
            return bestand.getType().equalsIgnoreCase(leverancier.getFormat().toString());

        }

        // laat het type van het bestand van factuur zien ->XMl of pdf?
        @Override
        public String getType() {
            return bestand.getType();
        }
    }



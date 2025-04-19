package model;

import java.time.LocalDate;

//erft alles van de Factuur klasse
public class XMLFactuur extends Factuur {

    //constructor
    public XMLFactuur(String nummer, LocalDate datum, Leverancier l, Bestand b) {

        //roept de constructor van Factuue klasse aan
        super(nummer, datum, l, b);
    }


    //extra gedrag toevoegen
    @Override
    public String getType() {
        return "XML";
    }
}

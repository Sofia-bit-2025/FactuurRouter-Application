package model.service; // zit in service laag, doet iets  met gegevens

import model.*; // haalt alle klasse op uit model package
import java.time.LocalDate; // om de datum van de factuur in te setellen
import java.util.ArrayList;
import java.util.List;

// klasse om facturen  te laden  puur voor test
public class FactuurLoader {


    public List<Factuur> laadTestFacturen() {


        List<Factuur> facturen = new ArrayList<>();


        Leverancier atag = new Leverancier("ATAG", FactuurFormat.XML);
        Leverancier wanbound = new Leverancier("WANBOUND", FactuurFormat.PDF);


        facturen.add(new XMLFactuur("INV001", LocalDate.of(2023, 11, 10), atag, new Bestand("atag.xml", "XML", 400)));
        facturen.add(new PDFFactuur("INV002", LocalDate.of(2023, 11, 11), wanbound, new Bestand("wanbound.pdf", "PDF", 350)));

        //test voor isGeldig() methode die false teruggeeft
        facturen.add(new XMLFactuur("INV003", LocalDate.of(2023, 11, 12), wanbound, new Bestand("wrongtype.xml", "XML", 350)));

      //stuurt de hele lijst terug
        return facturen;
    }
}

package model;
import java.time.LocalDate;

//polymorfisme
//overerving
//PDFFactuur klasse erft alles van abstract kalsse factuur
public class PDFFactuur extends Factuur {
    public PDFFactuur(String nummer, LocalDate datum, Leverancier l, Bestand b) {
        //roept de constructor aan van Factuur klasse (super klasse) aan
        super(nummer, datum, l, b);
    }

    //type is altijd pdf
    @Override
    public String getType() {
        return "PDF";
    }
}
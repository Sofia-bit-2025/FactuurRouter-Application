package model.service;
import model.Factuur;// om een factuur te verwerken
import model.Logboek;// om alles bij te houden
import java.util.ArrayList;
import java.util.List;


// een klasse die controleert en logt of de facturen goed zijn
public class FactuurVerwerker {


    //validatieservice object aanmaken
    private ValidateService validator = new ValidateService();

    // methode die een factuur aanneemt en controleert
    public Logboek verwerkFactuur(Factuur factuur) {
        boolean geldig = validator.isBestandstypeGeldig(factuur);
        boolean compleet = validator.isFactuurCompleet(factuur);


       //controle functie
        if (geldig && compleet) {
            return new Logboek("Factuur succesvol verwerkt", factuur);
        } else {
            return new Logboek("Factuur NIET verwerkt - Niet akkord", factuur);
        }
    }

    //meerdere facturen tegelijk  verwerken
    public List<Logboek> verwerkFacturen(List<Factuur> facturen) {

        //een lijst om alle logboekresultaten  in op te slaan
        List<Logboek> logs = new ArrayList<>();
        for (Factuur f : facturen) {
            logs.add(verwerkFactuur(f)); // per stuk verwerken
        }


        return logs;
    }

}

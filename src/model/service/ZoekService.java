package model.service;
import model.Factuur;
import model.Leverancier;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


//een service klasse
//doel een lijst van facturen door te zoeken
public class ZoekService {

    public List<Factuur> zoekFacturenVanLeverancier(List<Factuur> facturen, Leverancier leverancier) {
        return facturen.stream()

                //filter facturen met hetzelfde naam van de leverancier
                .filter(f -> f.getLeverancier().getNaam().equals(leverancier.getNaam()))
                .collect(Collectors.toList());
    }

    // filter facturen op basis van format
    public List<Factuur> zoekFacturenOpFormaat(List<Factuur> facturen, String type) {
        return facturen.stream()
                .filter(f -> f.getBestand().getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
}

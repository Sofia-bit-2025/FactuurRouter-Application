package model.service;

import model.*;


public class ValidateService {

    // Check of factuurformaat overeenkomt met bestandstype
    public boolean isBestandstypeGeldig(Factuur factuur) {

        String typeVanBestand = factuur.getBestand().getType().toUpperCase();

        String formaatVanLeverancier = factuur.getLeverancier().getFormat().name(); // Enum als string

        return typeVanBestand.equals(formaatVanLeverancier);
    }

    // Controleer of de factuur een XMLformaat heeft zodat die later apart gezet kunnen worden
    public boolean isXMLFactuur(Factuur factuur) {
        return factuur.getBestand().getType().equalsIgnoreCase("XML");
    }

    // Controleer of factuur compleet is
    public boolean isFactuurCompleet(Factuur factuur) {
        return factuur.getFactuurnummer() != null &&
                factuur.getLeverancier() != null &&
                factuur.getBestand() != null &&
                factuur.getDatum() != null;
    }
}

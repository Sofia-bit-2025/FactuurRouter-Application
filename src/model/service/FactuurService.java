package model.service;
import model.Factuur;
import utils.DatabaseVerbinding; //hulpfunctie om met database te verbinden

// om sql code uit te voeren in java
import java.sql.Connection;//datbase verbinding
import java.sql.PreparedStatement;// sql opdrachten sturen met invoervelden
import java.sql.SQLException;// foutmelding bij problemen met database

//doel:factuur opslaan in database

public class FactuurService {


    public void slaFactuurOp(Factuur factuur) {

        //sql code met ? als placeholder
        String sql = "INSERT INTO factuur (factuurnummer, datum, type, leverancier_id, bestand_id) VALUES (?, ?, ?, ?, ?)";



        try (Connection conn = DatabaseVerbinding.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

           //1e vraagteken is factuurnummer, invullen
            stmt.setString(1, factuur.getFactuurnummer());

            //2e vraagteken datum
            stmt.setDate(2, java.sql.Date.valueOf(factuur.getDatum()));

            //3e vraagteken type
            stmt.setString(3, factuur.getType());

            //4e vraagteken, gebruik id 1 voor leverancier/ later koppelen met echte bestaande tabelen
            stmt.setInt(4, 1); // vereenvoudigd: leverancier_id

            //5e vraagteken
            stmt.setInt(5, 1); // vereenvoudigd: bestand_id


            // het uitvoeren van de sql opdracht en opslaan in database
            stmt.executeUpdate();
            System.out.println("Factuur opgeslagen in database.");


            // foutmelding als er iets misgaat
        } catch (SQLException e) {
            System.out.println("Fout bij opslaan factuur: " + e.getMessage());
        }
    }
}

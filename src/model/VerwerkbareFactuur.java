package model;

//interface->contract
public interface VerwerkbareFactuur {
    boolean isGeldig(); //elke factuur moet kunnen zegeen of het geldig is
    String getType();//elke factuur moet zijn type terug kunne geven
}

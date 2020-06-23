package ch.ibw.appl.bimap.server.bauart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Bauart {
    public int idbauart;
    public String bauart;


    public static Bauart create(int idbauart, String bauart) {
        Bauart ba = new Bauart();
        ba.idbauart = idbauart;
        ba.bauart = bauart;
        return ba;
    }

    @Override
    public String toString() {
        return "Bauart{" +
                "bauart='" + bauart + '\'' +
                ", idbauart=" + idbauart +
                '}';
    }
}

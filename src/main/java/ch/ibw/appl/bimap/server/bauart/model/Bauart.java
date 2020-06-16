package ch.ibw.appl.bimap.server.bauart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Bauart {
    public String bauart;

    @Id
    @GeneratedValue

    public int idbauart;

    public static Bauart create(String bauartdef) {
        Bauart bauart = new Bauart();
        bauart.bauart = bauartdef;
        return bauart;
    }

    @Override
    public String toString() {
        return "Bauart{" +
                "bauartdef='" + bauart + '\'' +
                ", id=" + idbauart +
                '}';
    }
}

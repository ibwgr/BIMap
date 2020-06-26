package ch.ibw.appl.bimap.server.bauart.model;

import ch.ibw.appl.bimap.server.bauaherr.model.Bauherr;

import javax.persistence.Entity;


@Entity
public class Bauart {
    public int idbauart;
    public String bauart;

    public static Bauart create(int idbauart, String bauartdef) {
        Bauart bauart = new Bauart();
        bauart.bauart = bauartdef;
        bauart.idbauart = idbauart;
        return bauart;
    }

    @Override
    public String toString() {
        return "Bauart{" +
                "bauart='" + bauart + '\'' +
                ", idbauart=" + idbauart +
                '}';
    }
}

package ch.ibw.appl.bimap.server.leistung.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Leistung {
    public int idleistung;
    public String leistungen;

    public static Leistung create(String leistungdef) {
        Leistung lp = new Leistung();
        lp.leistungen = leistungdef;
        return lp;
    }

    @Override
    public String toString() {
        return "Leistung{" +
                "leistung='" + leistungen + '\'' +
                ", id=" + idleistung +
                '}';
    }
}
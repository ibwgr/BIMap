package ch.ibw.appl.apiedi.server.leistung.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Leistung {
    public String leistungen;

    @Id
    @GeneratedValue
    public int idleistung;

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
package ch.ibw.appl.apiedi.server.leistung.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Leistung {
    public int leistungid;
    public int bauartid;

    @Id
    @GeneratedValue
    public Long id;

    public static Leistung create(int leistungid, int bauartid) {
        Leistung lp = new Leistung();
        lp.leistungid = leistungid;
        lp.bauartid = bauartid;
        return lp;
    }

    @Override
    public String toString() {
        return "Leistungprojekt{" +
                "leistungid='" + leistungid + '\'' +
                ", projektid=" + bauartid +
                ", id=" + id +
                '}';
    }
}
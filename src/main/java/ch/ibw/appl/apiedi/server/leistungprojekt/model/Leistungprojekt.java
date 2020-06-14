package ch.ibw.appl.apiedi.server.leistungprojekt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Leistungprojekt {
    public int leistungid;
    public int projektid;

    @Id
    @GeneratedValue
    public Long id;

    public static Leistungprojekt create(int leistungid, int projektid) {
        Leistungprojekt lp = new Leistungprojekt();
        lp.leistungid = leistungid;
        lp.projektid = projektid;
        return lp;
    }

    @Override
    public String toString() {
        return "Leistungprojekt{" +
                "leistungid='" + leistungid + '\'' +
                ", projektid=" + projektid +
                ", id=" + id +
                '}';
    }
}

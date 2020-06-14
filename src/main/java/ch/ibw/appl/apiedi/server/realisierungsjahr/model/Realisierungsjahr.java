package ch.ibw.appl.apiedi.server.realisierungsjahr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Realisierungsjahr {
    public int realJahr;

    @Id
    @GeneratedValue
    public Long id;

    public static Realisierungsjahr create(int realJahr) {
        Realisierungsjahr rl = new Realisierungsjahr();
        rl.realJahr = realJahr;
        return rl;
    }

    @Override
    public String toString() {
        return "realisierungsjahr{" +
                "realisierungsjahr='" + realJahr + '\'' +
                ", id=" + id +
                '}';
    }
}

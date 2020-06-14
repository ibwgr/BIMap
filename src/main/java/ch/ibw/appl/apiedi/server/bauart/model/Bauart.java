package ch.ibw.appl.apiedi.server.bauart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Bauart {
    public String bauartdef;

    @Id
    @GeneratedValue

    public int id;

    public static Bauart create(String bauartdef) {
        Bauart bauart = new Bauart();
        bauart.bauartdef = bauartdef;
        return bauart;
    }

    @Override
    public String toString() {
        return "Bauart{" +
                "bauartdef='" + bauartdef + '\'' +
                ", id=" + id +
                '}';
    }
}

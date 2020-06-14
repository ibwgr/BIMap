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
        Bauart bauart = new ch.ibw.appl.apiedi.server.bauart.model.Bauart();
        bauart.bauartdef = bauartdef;
        return bauart;
    }

    @Override
    public String toString() {
        return "Angebot{" +
                "behart='" + bauartdef + '\'' +
                ", id=" + id +
                '}';
    }
}

package ch.ibw.appl.apiedi.server.bauherr.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Bauherr {
    public String bauherrdef;

    @Id
    @GeneratedValue

    public int id;

    public static Bauherr create(String bauartdef) {
        Bauherr bauart = new Bauherr();
        bauart.bauherrdef = bauartdef;
        return bauart;
    }

    @Override
    public String toString() {
        return "Angebot{" +
                "behart='" + bauherrdef + '\'' +
                ", id=" + id +
                '}';
    }
}

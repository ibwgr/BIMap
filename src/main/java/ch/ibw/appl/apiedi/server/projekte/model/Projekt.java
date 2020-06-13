package ch.ibw.appl.apiedi.server.angebot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Angebot {
    public String behart;
    public float betrag;

    @Id
    @GeneratedValue
    public Long id;

    public static ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt create(String behart, float betrag) {
        ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt angebot = new ch.ibw.appl.apiedi.server.angebot.model.Leistungprojekt();
        angebot.behart = behart;
        angebot.betrag = betrag;
        return angebot;
    }

    @Override
    public String toString() {
        return "Angebot{" +
                "behart='" + behart + '\'' +
                ", betrag=" + betrag +
                ", id=" + id +
                '}';
    }
}

package ch.ibw.appl.bimap.server.projekte.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Projekt {

    public int idprojekt;
    public int projektnummer;
    public String projektname;
    public int ortid;
    public int plz;
    public String ort;
    public int koordx;
    public int koordy;
    public int realisierungsjahr;
    public float bausumme;
    public int bauherrid;
    public String bauherr;
    public String bauart;
    public String projektverfasser;
    public String leistungen;

    public static Projekt create(int idprojekt, int projektnummer, String projektname, int ortid, int koordx, int koordy, int realisierungsjahr, float bausumme, int bauherrid) {
        Projekt pr = new Projekt();
        pr.idprojekt = idprojekt;
        pr.projektname = projektname;
        pr.projektnummer = projektnummer;
        pr.ortid = ortid;
        pr.koordx = koordx;
        pr.koordy = koordy;
        pr.realisierungsjahr = realisierungsjahr;
        pr.bausumme = bausumme;
        pr.bauherrid = bauherrid;
        return pr;
    }

    @Override
    public String toString() {
        return "Projekt{" +
                "idprojekt=" + idprojekt +
                ", projektnummer=" + projektnummer +
                ", projektname='" + projektname + '\'' +
                ", plz=" + plz +
                ", ort='" + ort + '\'' +
                ", koordx=" + koordx +
                ", koordy=" + koordy +
                ", realisierungsjahr=" + realisierungsjahr +
                ", bausumme=" + bausumme +
                ", bauherr='" + bauherr + '\'' +
                ", bauart='" + bauart + '\'' +
                ", projektverfasser='" + projektverfasser + '\'' +
                ", leistungen='" + leistungen + '\'' +
                '}';
    }
}

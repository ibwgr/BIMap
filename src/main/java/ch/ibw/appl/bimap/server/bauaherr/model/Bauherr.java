package ch.ibw.appl.bimap.server.bauaherr.model;

import javax.persistence.Entity;

@Entity
public class Bauherr {
    public int idbauherr;
    public String bauherr;

    public static Bauherr create(int idbauherr, String bauherr) {
        Bauherr bh = new Bauherr();
        bh.idbauherr = idbauherr;
        bh.bauherr = bauherr;
        return bh;
    }

    @Override
    public String toString() {
        return "Bauherr{" +
                "idbauherr=" + idbauherr +
                ", bauherr='" + bauherr + '\'' +
                '}';
    }
}

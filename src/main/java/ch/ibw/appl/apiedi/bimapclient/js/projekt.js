export class Projekt {
    constructor(id, projektnummer, projektname, ort, bauart, projektverfasser, leistungen, koordX, koordY, realisierungsjahr, bausumme, bauherr) {
        this.id = id;
        this.projektnummer = projektnummer;
        this.projektname = projektname;
        this.ort = ort;
        this.bauart = bauart;
        this.projektverfasser = projektverfasser;
        this.leistungen = leistungen;
        this.koordx = koordX;
        this.koordy = koordY;
        this.realisierungsjahr = realisierungsjahr;
        this.bausumme = bausumme;
        this.bauherr = bauherr;
    }
}

Projekt.fromJson = function (json){
    var obj = JSON.parse (json);
    return new Book (obj.idprojekt, obj.projektnummer, obj.projektname, obj.ort, obj.bauart,
        obj.projektverfasser, obj. leistungen, obj.koordx, ob.koordy, obj.realisierungsjahr, obj.bausumme, obj.bauherr);
};
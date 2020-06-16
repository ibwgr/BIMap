import {Projekt} from "projekt";

let bspdata = [{"idprojekt":1,"projektnummer":502,"projektname":"Kindergarten Taescherloch","ortid":1,"koordx":2759543,"koordy":1220275,"realisierungsjahr":2009,"bausumme":2860000.0,"bauherrid":1,"id":null},{"idprojekt":2,"projektnummer":919,"projektname":"Gewerbehalle Balzers","ortid":2,"koordx":2757459,"koordy":1216049,"realisierungsjahr":2011,"bausumme":3500000.0,"bauherrid":2,"id":null},{"idprojekt":3,"projektnummer":943,"projektname":"MFH Stubi","ortid":3,"koordx":2764691,"koordy":1218983,"realisierungsjahr":2016,"bausumme":7940000.0,"bauherrid":3,"id":null},{"idprojekt":4,"projektnummer":1002,"projektname":"EFH Hasler","ortid":4,"koordx":2756850,"koordy":1232394,"realisierungsjahr":2012,"bausumme":915000.0,"bauherrid":2,"id":null},{"idprojekt":5,"projektnummer":1128,"projektname":"MFH Lampert","ortid":5,"koordx":2757674,"koordy":1225878,"realisierungsjahr":2014,"bausumme":5700000.0,"bauherrid":2,"id":null},{"idprojekt":6,"projektnummer":2047,"projektname":"Stationsumbau Arosa","ortid":6,"koordx":2771084,"koordy":1183922,"realisierungsjahr":2014,"bausumme":2.44E7,"bauherrid":6,"id":null},{"idprojekt":7,"projektnummer":1069,"projektname":"Strassenkorrektion Giarsun - Ardez","ortid":7,"koordx":2808318,"koordy":1183671,"realisierungsjahr":2018,"bausumme":5700000.0,"bauherrid":7,"id":null},{"idprojekt":8,"projektnummer":1083,"projektname":"N13 EP17 Chur Nord - AS Zizers/Untervaz","ortid":8,"koordx":2760953,"koordy":1197348,"realisierungsjahr":2018,"bausumme":3.5E7,"bauherrid":8,"id":null},{"idprojekt":9,"projektnummer":6012,"projektname":"Speichersee Nagens","ortid":9,"koordx":2737162,"koordy":1191112,"realisierungsjahr":2002,"bausumme":3600000.0,"bauherrid":9,"id":null},{"idprojekt":10,"projektnummer":1093,"projektname":"Kreuzung Bahnhofstrasse","ortid":10,"koordx":2738645,"koordy":1250344,"realisierungsjahr":2020,"bausumme":1.65E7,"bauherrid":10,"id":null}]

export function getTestData() {
    let projects = []
    for (let item of bspdata) {
        let project = new Projekt(item)
        projects.push(project)
    }
    return projects
}

export async function getAllProjektData() {
    let projects = [];
    let response = await fetch("hhtp://localhost:2567/projekte");
    let data = await response.json();
    for (let item of data) {
        let project = new Projekt(item)
        projekte.push(project)
    }
    return projects;
}

function filter(object, ...keys) {
    return keys.reduce((result, key) => ({ ...result, [key]: object[key] }), {});
};

export function getAllProjektKoordId() {
    return filter(getAllProjektData(), 'idprojekt', 'koordx', 'koordy')
}

export function getTestKoordId() {
    return filter(getTestData(),'idprojekt', 'koordx', 'koordy')
}

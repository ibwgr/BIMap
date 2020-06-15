import projekt from 'projekt.js';

export async function getAllProjektData() {
    let projekte;
    let response = await fetch("hhtp://localhost:2567/projekte");
    let data = await response.json();
    for (item of data) {
        projekte.push(Projekte.fromJSON(item))
    }
    return projekte;
}



export async function getAllProjektKoordId() {
    getAllProjektData().filter

}

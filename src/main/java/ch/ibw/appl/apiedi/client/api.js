// class Ausgabe {
//     id;
//     date;
//     bemerkung;
//     betrag;
//
//     constructor(id, date, bemerkung, betrag) {
//         this.id = id
//         this.date = date
//         this.bemerkung = bemerkung
//         this.betrag = betrag
//     }
// }




function postResource(ressource) {
    let date = document.getElementById("date").value
    let angebotid = document.getElementById("angebotid").value
    let anzahl = document.getElementById("anzahl").value

    console.log(date + " " + angebotid + anzahl)
    let url = "http://localhost:4567/" + ressource
    fetch(url, {
        method: 'post',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({date: date, angebotid: angebotid, anzahl: anzahl})
    }).then(res=>res.json())
        .then(res => {
            window.location.reload()
            console.log(res);
        });
}


getResource("behandlungen")
getResource("angebote")
getResource("ausgaben")



async function getResource(res) {
    let ausgabe_url = "http://localhost:4567/" + res;
    const response = await fetch(ausgabe_url)
    let data = await response.json()
    for (let item of data) {
        for (variable in item) {
            if (variable !== "id") {
                let el = document.createElement("div")
                el.innerHTML = item[variable]
                document.getElementById(res).appendChild(el)
            }
        }
    }
}

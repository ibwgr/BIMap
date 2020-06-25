import {Bauart} from "./bauart.js"
import {Projekt} from "./projekt.js"
import {Bauherr} from "./bauherr.js"

export class Store {
    constructor() {
        this.existingBauarten = []
        this.existingProjekte = []
        this.existingBauherren = []
        this.projekt;
    }

    async loadAllBauarten() {
        const response = await fetch("http://localhost:2567/bauarten", {
            headers: new Headers({"accept": "application/json"})
        })
        const data = await response.json()
        data.forEach(item => {
            let bauart = new Bauart(item)
            this.existingBauarten.push(bauart)
        })
    }

    async loadAllBauherren() {
        // const response = await fetch("http://localhost:2567/bauherren", {
        //     headers: new Headers({"accept": "application/json"})
        // })
        // const data = await response.json()

        let data = ["te", "st"]
        data.forEach(item => {
            let bauherr = new Bauherr(item)
            this.existingBauherren.push(bauart)
        })
    }

    async loadProjektByFilter(bauherr, bauart, realJahr) {
        if (bauherr === "Bauherr:") {
            bauherr = ""
        }
        if (bauart === "Bauart:") {
            bauart = ""
        }
        if (realJahr === "Realisierungsjahr:") {
            realJahr = ""
        }

        let ReqURL = "http://localhost:2567/projekte" + "?bauherr=" + bauherr + "&bauart=" + bauart + "&realisierungsjahr=" + realisierungsjahr
        const response = await fetch(ReqURL, {
            headers: new Headers({"accept": "application/json"})
        })
        const data = await response.json();
        let filteredProjekte = []
        data.forEach(item => {
            let projekt = new Projekt(item)
            filteredProjekte.push(item)
        })
        this.existingProjekte = filteredProjekte
    }

    getAllBauarten() {
        return this.existingBauarten
    }

    getAllBauherren() {
        return this.existingBauherren
    }

    async loadAllProjekte() {
        const response = await fetch("http://localhost:2567/projekte", {
            headers: new Headers({"accept": "application/json"})
        })
        const data = await response.json()
        data.forEach(item => {
            let projekt = new Projekt(item)
            this.existingProjekte.push(projekt)
        })
    }

    getAllProjekte() {
        return this.existingProjekte
    }

    async loadProjektById(projektId) {
        const response = await fetch("http://localhost:2567/projekte/" + projektId, {
            headers: new Headers({"accept": "application/json"})
        })
        const data = await response.json()
        this.projekt = new Projekt(item)
    }

    getProjektbyId(projektId) {
        return this.projekt
    }


}
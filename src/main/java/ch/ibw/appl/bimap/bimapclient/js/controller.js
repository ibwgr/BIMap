export class Controller {
    constructor(view, store, map) {
        this.view = view
        this.store = store
        this.map = map
    }

    async start() {
        await this.store.loadAllProjekte()
        await this.store.loadAllBauarten()
        await this.store.loadAllBauherren()
        this.view.addBauartToList(this.store.getAllBauarten())
        this.view.addBauherrToList(this.store.getAllBauherren())
        this.view.addRealisierungsjahreToList(this.store.getAllProjekte())
        this.view.createFilter()
        this.map.setMapBackground()
        this.map.setPopUpOverlay(this.store.getAllProjekte())
        this.map.markProjects(this.store.getAllProjekte())
        this.view.registerOnAddItemHandler(async (bauherr, bauart, realJahr) => {
            await this.store.loadProjektByFilter(bauherr, bauart, realJahr)
            this.map.deleteProjectMarks()
            this.map.markProjects(this.store.getAllProjekte())
        })
        document.getElementById("cardswitch").addEventListener('click', event => {
            this.map.changeMap()
            this.changeCardSmall()
        });
    }

    changeCardSmall() {
        let element=document.getElementById('example')
        if (element.src.match("card1"))
        {
            element.src="img/card2.jpg";
        }else{
            element.src="img/card1.jpg";
        }
    }

}

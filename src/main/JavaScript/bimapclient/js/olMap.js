export class Map {
    constructor() {
        this.pixelkarteGrau = ga.layer.create('ch.swisstopo.pixelkarte-grau')
        this.landeskarte = ga.layer.create('ch.swisstopo.pixelkarte-farbe')
        this.Luftbildkarte = ga.layer.create('ch.swisstopo.swissimage')

        this.map = new ga.Map({
            target: 'map',

            view: new ol.View({
                resolution: 200,
                center: [2660000, 1190000]
            }),
        });
    }

    setMapBackground () {
        var landesgrenze = ga.layer.create('ch.swisstopo.swissnames3d')
        var lufbildkarte1946 = ga.layer.create('ch.swisstopo.swissimage-product')
        var relief = ga.layer.create('ch.swisstopo.swissalti3d-reliefschattierung')
        this.map.addLayer(this.pixelkarteGrau);
    }

    setPopUpOverlay(projekte) {
        var container = document.getElementById('popup');
        var content = document.getElementById('popup-content');
        var closer = document.getElementById('popup-closer');

        var overlay = new ol.Overlay({
            element: container,
            autoPan: true,
            zIndex: 10,
            autoPanAnimation: {
                duration: 250
            }
        });
        this.map.addOverlay(overlay)

        closer.onclick = function () {
            overlay.setPosition(undefined);
            closer.blur();
            return false;
        };

// Add the function to open the popup when you click on the marker
        this.map.on('singleclick', function (event) {
            if (this.hasFeatureAtPixel(event.pixel) === true) {
                this.forEachFeatureAtPixel(event.pixel, feature => {
                    let projektId = feature.getId();
                    let projekt = projekte.find(projekt => projekt.idprojekt == projektId)
                    let projektinfo =
                        "<br/><div class='projektInfoTitle' >Projektnummer: </div>" + "<div class='projektInfoText'>"  + projekt.projektnummer + "</div>" +
                        "<br/><div class='projektInfoTitle' >Projektname: </div>" + "<div class='projektInfoText'>" + projekt.projektname + "</div>" +
                        "<br/><div class='projektInfoTitle'>Ort: </div>" + "<div class='projektInfoText'>" + projekt.ort + "</div>" +
                        "<br/><div class='projektInfoTitle'>PLZ: </div>" + "<div class='projektInfoText'>" + projekt.plz + "</div>" +
                        "<br/><div class='projektInfoTitle'>Realisierung: </div>" + "<div class='projektInfoText'>" + projekt.realisierungsjahr + "</div>" +
                        "<br/><div class='projektInfoTitle'>Bauherr: </div>" + "<div class='projektInfoText'>" + projekt.bauherr + "</div>" +
                        "<br/><div class='projektInfoTitle'>Bauart: </div>" + "<div class='projektInfoText'>" + projekt.bauart +" </div>" +
                        "<br/><div class='projektInfoTitle'>Projektverfasser: </div>" + "<div class='projektInfoText'>" + projekt.projektverfasser + "</div>" +
                        "<br/><div class='projektInfoTitle'>Leistungen: </div>" + "<div class='projektInfoText'>" + projekt.leistungen + "</div>" +
                        "<br/><div class='projektInfoTitle'>Bausumme: </div>" + "<div class='projektInfoText'>" + projekt.bausumme + "</div>"

                    content.innerHTML = projektinfo;

                    var coordinate = event.coordinate;
                    overlay.setPosition(coordinate);
                });
            } else {
                overlay.setPosition(undefined);
                closer.blur();
            }
        })
    }

    // Create the layer with the icon
    markProjects(projekte) {
        projekte.forEach(projekt => {
            let feature = new ol.Feature({
                geometry: new ol.geom.Point([projekt.koordx, projekt.koordy])
            })
            feature.setId(projekt.idprojekt)
            var vectorLayer = new ol.layer.Vector({
                source: new ol.source.Vector({
                    features: [feature]
                }),
                zIndex: 10,
                name: "marker",
                style: new ol.style.Style({
                    image: new ol.style.Icon({
                        anchor: [0.5, 0.5],
                        anchorXUnits: 'fraction',
                        anchorYUnits: 'pixels',
                        scale: 0.5,
                        src: './img/map-marker-icon2.png'
                    })
                })
            });
            this.map.addLayer(vectorLayer);
        })
    }
    deleteProjectMarks(projekte) {
        let marks = this.map.getLayers().getArray()
        let filteredMarks = marks.filter(layer => layer.get("name") === "marker")
        filteredMarks.forEach(layer => this.map.removeLayer(layer))
    }

    changeMap() {
        var element = document.getElementById('example')
        if (element.src.match("card2")) {
            this.map.removeLayer(this.Luftbildkarte)
            this.map.addLayer(this.pixelkarteGrau)
        } else {
            this.map.removeLayer(this.pixelkarteGrau)
            this.map.addLayer(this.Luftbildkarte);
        }
    }
}


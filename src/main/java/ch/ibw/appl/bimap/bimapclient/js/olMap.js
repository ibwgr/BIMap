export class Map {
    constructor() {
        this.map = new ga.Map({

            // Define the div where the map is placed
            target: 'map',

            // Create a view
            view: new ol.View({

                // Define the default resolution
                // 10 means that one pixel is 10m width and height
                // List of resolution of the WMTS layers:
                // 650, 500, 250, 100, 50, 20, 10, 5, 2.5, 2, 1, 0.5, 0.25, 0.1
                resolution: 200,

                // Define a coordinate CH1903+ (EPSG:2056) for the center of the view
                center: [2660000, 1190000]
            }),
        });
    }

    setMapBackground () {
        // Create a background layer
        var landeskarte = ga.layer.create('ch.swisstopo.pixelkarte-farbe');
        var Luftbildkarte = ga.layer.create('ch.swisstopo.swissimage')
        var landesgrenze = ga.layer.create('ch.swisstopo.swissnames3d')
        var lufbildkarte1946 = ga.layer.create('ch.swisstopo.swissimage-product')
        var relief = ga.layer.create('ch.swisstopo.swissalti3d-reliefschattierung')

        var pixelkarteGrau = ga.layer.create('ch.swisstopo.pixelkarte-grau')

        this.map.addLayer(pixelkarteGrau);

// Add the background layer in the map

// map.addLayer(landesgrenze);

// Zoom on the position
// var position = [2709136, 1270186];
// map.getView().setCenter(position);
// map.getView().setResolution(100);
    }

    setPopUpOverlay(projekte) {
        var container = document.getElementById('popup');
        var content = document.getElementById('popup-content');
        var closer = document.getElementById('popup-closer');

        var overlay = new ol.Overlay({
            element: container,
            autoPan: true,
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
                        "<b>Projektinfo</b>" +
                        "<br/>Projektnummer: " + projekt.projektnummer +
                        "<br/>Projektname: " + projekt.projektname +
                        "<br/>Ort: " + projekt.ort +
                        "<br/>PLZ: " + projekt.plz +
                        "<br/>Realisierung: " + projekt.realisierungsjahr +
                        "<br/>Bauherr: " + projekt.bauherr +
                        "<br/>Bauart: " + projekt.bauart +
                        "<br/>Projektverfasser: " + projekt.projektverfasser +
                        "<br/>Leistungen: " + projekt.leistungen +
                        "<br/>Bausumme: " + projekt.bausumme

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
                name: "marker",
                style: new ol.style.Style({
                    image: new ol.style.Icon({
                        anchor: [0.5, 0.5],
                        anchorXUnits: 'fraction',
                        anchorYUnits: 'pixels',
                        scale: 0.5,
                        src: './resources/map-marker-icon2.png'
                        //       src: 'http://localhost:63342/server/ch/ibw/appl/bimap/bimapclient/img/map-marker-icon2.png',
                        //       src: '/src/main/resources/img/map-marker-icon2.png',
                        //    src: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB9oLCAwhG8L1qXwAAAJ1SURBVDjLTZPBThRBEIa/6uleYtDVA8QHUGIw4ehVb3Ii+AK+guGid696Ud/AN4BAiFw9ePUAbmK4SIAo7OxuZtnZmZ7umfLQED1U0of6qur/q1oA9PgYWVlBBwNHCGt4v8Ws3GRa9BmPoSimDIc7jEYfqOtD+fw56MYGsruL6GCAPH6Mqjq+f9+jbZ9T1zArYVoo4zFMJsJoBMMh5PkBT55syNu3QZ89QwD07MxxcjJBZJEQFGME7+H8HC4vIc+5LqSMRsJsVuDcsnz7Fozu7zuOjvYoy0WKqeKccP8+3LkDvoHJJEVRwHQqVJVydXWXP392dXXVWcpyjbZ9DiggGIGmgdNTOPkFFxc3MMxmUJaCb5TQrBPCmmU43AKg64QQElhVMBrDZJzgsryBoaohBiFGEHllyfNNOoW2BV9DNU8G3gBlCfM51HWCQ4BOQQxo98Ly+3cfa5UQJCVVCZjP07uuUzQNhAhdB5kBI4qau5Y8hyxLlb3/B3gPtU9gDBDbZJPNQIRr2Viqaor3fWJMyU2T3G8aaGOS1ikYAZOBMSlAiLGwVNUOV1cvaVuIMU0S22uwTbvJDFgLCwsJjhFUQXXbEOMHQoC6VrxPBdr4H5wl8N49WFqC27eh11NcD5z7ZFjoHaJ6QIhCjErbpe4qSa+zcOsWLC4mCaqKdYK1X/D1YTrlN68de/s51byPqoIImUnde700Qa8HoIgIIRT8OFqWeRWMrq8j794HHj1YQuQA1XSNxkCWKSJK26YVqgqdfuHnz2WZV0EfPrz+TE+fIl+/oqurDu/XMGYLkU2c62MtwJQs28aYj1TVoQwGQVdWkONj/gL3ho+XUT2DTgAAAABJRU5ErkJggg=='
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
}

    // let mapDiv = document.getElementById("map")
    // if (mapDiv.hasChildNodes()) {
    //     mapDiv.childNodes.forEach(node => mapDiv.removeChild(node))
    // } else {
    //
    // }

import {Projekt} from "./projekt.js"

export class View {
    constructor(rootSelector, store) {
        const newField = document.querySelector(rootSelector + '.custom-select')
        this.addEventListener(bauherr, bauart, realJahr)
    }

    addBauartToList(bauarten) {
        let typeSelect = document.getElementById("bauart")
        bauarten.forEach(bauart => {
            let option = document.createElement("option")
            option.value = bauart.idbauart
            option.innerText = bauart.bauart
            typeSelect.appendChild(option)
        })
    }

    addBauherrToList(bauherren){
        let typeSelect = document.getElementById("bauherren")
        bauherren.forEach(bauherr => {
            let option = document.createElement("option")
            option.value = bauherren.idbauherr
            option.innerText = bauherren.bauherr
            typeSelect.appendChild(option)
        })
    }

    addRealisierungsjahreToList(projekte) {
        let oldest = projekte.find((a, b) => (a.realisierungsjahr < b.realisierungsjahr) ? a : b)
        let oldestDate = oldest.realisierungsjahr
        let actualDate = new Date().getFullYear()
        let id = 1
        let typeSelect = document.getElementById("realisierungsjahr")
        for (let i = actualDate; i >= oldestDate; i--) {
            let option = document.createElement("option")
            option.value = id
            id++
            option.innerText = i
            typeSelect.appendChild(option)
        }
    }
    
    addEventListener(bauherr, bauart, realJahr) {
        document.getElementById("FilterIt").addEventListener('click', event => {
          this.createMap()
        });
    }
    registerOnAddItemHandler(onAddItemHandler) {
        this.onAddItemHandler = onAddItemHandler
    }

    createFilter() {
        document.getElementById("openbtn").addEventListener('click', event => {
            document.getElementById("mySidenav").style.width = "350px";
        });

        document.getElementById("closebtn").addEventListener('click', event => {
            document.getElementById("mySidenav").style.width = "0";
        });

        /***************************
         /* Dropdown Option
         /***************************/

        var x, i, j, l, ll, selElmnt, a, b, c;
        /*look for any elements with the class "custom-select":*/

        x = document.getElementsByClassName("custom-select");
        l = x.length;
        for (i = 0; i < l; i++) {
            selElmnt = x[i].getElementsByTagName("select")[0];
            ll = selElmnt.length;
            /*for each element, create a new DIV that will act as the selected item:*/
            a = document.createElement("DIV");
            a.setAttribute("class", "select-selected");
            a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
            x[i].appendChild(a);
            /*for each element, create a new DIV that will contain the option list:*/
            b = document.createElement("DIV");
            b.setAttribute("class", "select-items select-hide");
            console.log("babum tsssssss")
            for (j = 1; j < ll; j++) {
                /*for each option in the original select element,
                create a new DIV that will act as an option item:*/
                c = document.createElement("DIV");
                c.innerHTML = selElmnt.options[j].innerHTML;
                c.addEventListener("click", function (e) {
                    /*when an item is clicked, update the original select box,
                    and the selected item:*/
                    var y, i, k, s, h, sl, yl;
                    s = this.parentNode.parentNode.getElementsByTagName("select")[0];
                    sl = s.length;
                    h = this.parentNode.previousSibling;
                    for (i = 0; i < sl; i++) {
                        if (s.options[i].innerHTML == this.innerHTML) {
                            s.selectedIndex = i;
                            h.innerHTML = this.innerHTML;
                            y = this.parentNode.getElementsByClassName("same-as-selected");
                            yl = y.length;
                            for (k = 0; k < yl; k++) {
                                y[k].removeAttribute("class");
                            }
                            this.setAttribute("class", "same-as-selected");
                            break;
                        }
                    }
                    h.click();
                });
                b.appendChild(c);
            }
            x[i].appendChild(b);
            a.addEventListener("click", function (e) {
                /*when the select box is clicked, close any other select boxes,
                and open/close the current select box:*/
                e.stopPropagation();
                closeAllSelect(this);
                this.nextSibling.classList.toggle("select-hide");
                this.classList.toggle("select-arrow-active");
            });
        }

        function closeAllSelect(elmnt) {
            /*a function that will close all select boxes in the document,
            except the current select box:*/
            var x, y, i, xl, yl, arrNo = [];
            x = document.getElementsByClassName("select-items");
            y = document.getElementsByClassName("select-selected");
            xl = x.length;
            yl = y.length;
            for (i = 0; i < yl; i++) {
                if (elmnt == y[i]) {
                    arrNo.push(i)
                } else {
                    y[i].classList.remove("select-arrow-active");
                }
            }
            for (i = 0; i < xl; i++) {
                if (arrNo.indexOf(i)) {
                    x[i].classList.add("select-hide");
                }
            }
        }

        /*if the user clicks anywhere outside the select box,
        then close all select boxes:*/
        document.addEventListener("click", closeAllSelect);

        /***************************
         /* END  Dropdown Option
         /***************************/
    }

    createMap(projekte) {
        var map = new ga.Map({

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

// Create a background layer
        var landeskarte = ga.layer.create('ch.swisstopo.pixelkarte-farbe');
        var Luftbildkarte = ga.layer.create('ch.swisstopo.swissimage')
        var landesgrenze = ga.layer.create('ch.swisstopo.swissnames3d')
        var lufbildkarte1946 = ga.layer.create('ch.swisstopo.swissimage-product')
        var relief = ga.layer.create('ch.swisstopo.swissalti3d-reliefschattierung')

        var pixelkarteGrau = ga.layer.create('ch.swisstopo.pixelkarte-grau')

// Add the background layer in the map
        map.addLayer(pixelkarteGrau);
// map.addLayer(landesgrenze);

// Zoom on the position
// var position = [2709136, 1270186];
// map.getView().setCenter(position);
// map.getView().setResolution(100);


// Create the layer with the icon

        projekte.forEach(projekt => {
            let feature = new ol.Feature({
                geometry: new ol.geom.Point([projekt.koordx, projekt.koordy])
            })
            feature.setId(projekt.idprojekt)
            var vectorLayer = new ol.layer.Vector({
                source: new ol.source.Vector({
                    features: [feature]
                }),
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
            map.addLayer(vectorLayer);
        })
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
        map.addOverlay(overlay)


        closer.onclick = function () {
            overlay.setPosition(undefined);
            closer.blur();
            return false;
        };

// Add the function to open the popup when you click on the marker
        map.on('singleclick', function (event) {
            if (map.hasFeatureAtPixel(event.pixel) === true) {
                map.forEachFeatureAtPixel(event.pixel, feature => {
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
}

// Initialize the popup



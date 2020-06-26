export class View {
    constructor(rootSelector, store) {
        this.addEventListener()
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
        let typeSelect = document.getElementById("bauherr")
        bauherren.forEach(bauherr => {
            let option = document.createElement("option")
            option.value = bauherr.idbauherr
            option.innerText = bauherr.bauherr
            typeSelect.appendChild(option)
        })
    }

    addRealisierungsjahreToList(projekte) {
        let realisierungsjahre = projekte.map(a => a.realisierungsjahr);
        realisierungsjahre = realisierungsjahre.reduce((arr,b) => arr.includes(b) ? arr : [...arr, b], [])
        let oldestDate = realisierungsjahre.sort()[0]
        let typeSelect = document.getElementById("realisierungsjahr")

        realisierungsjahre.forEach(realJahr => {
            let option = document.createElement("option")
            option.innerText = realJahr
            typeSelect.appendChild(option)
        })
    }

    addEventListener() {
        document.getElementById("FilterIt").addEventListener('click', event => {
            let bauherr = document.querySelector("#bauherr1 .select-selected").innerText
            let realJahr = document.querySelector("#realJahr1 .select-selected").innerText
            let bauart = document.querySelector("#bauart1 .select-selected").innerText
            this.onAddItemHandler(bauherr, bauart, realJahr)
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

        //Filter-Fenster aus Vorlage W3Schools
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

        /*a function that will close all select boxes in the document,
            except the current select box:*/
        function closeAllSelect(elmnt) {
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
    }
}


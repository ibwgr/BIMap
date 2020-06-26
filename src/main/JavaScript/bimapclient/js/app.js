import {Store} from './store.js'
import {View} from './view.js'
import {Controller} from './controller.js'
import {Map} from './olMap.js'

let store = new Store()
let view = new View('#the-app', store)
let map = new Map()
new Controller(view, store, map).start()

import {Store} from './store.js'
import {View} from './view.js'
import {Controller} from './controller.js'

let store = new Store()
let view = new View('#the-app', store)
new Controller(view, store).start()

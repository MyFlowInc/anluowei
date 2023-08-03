import localforage from "localforage";

import storage from 'store'
import expirePlugin from 'store/plugins/expire'
store.addPlugin(expirePlugin)

// 导览后的处理
export function TourDone(){
    // @ts-ignore
    storage.set(StorageMap.TOUR, StorageMap.TOURDONE, new Date().getTime() + 1000*60*24*7)

}

export const StorageMap = {
    TOUR: 'tour_status',
    TOURUNDONE: 'undone',
    TOURDONE: 'done'
}


// temp
import _ from 'lodash'

export const getUniqueId = () => 'item_' + _.uniqueId()
export const color16 = () => {
    let r = Math.floor(Math.random() * 256)
    let g = Math.floor(Math.random() * 256)
    let b = Math.floor(Math.random() * 256)
    let color = '#' + r.toString(16) + g.toString(16) + b.toString(16)
    return color
}

// 13位随机字符串 包含大小写字母和数字
export const randomString = (len = 13) => {
    let $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678'
    let maxPos = $chars.length
    let pwd = ''
    for (let i = 0; i < len; i++) {
        pwd = pwd + $chars.charAt(Math.floor(Math.random() * maxPos))
    }
    return pwd
}
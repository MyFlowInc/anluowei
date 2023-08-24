import _ from 'lodash'

export interface ClipboardDataRule {
  op: 'copy' | 'cut'
  url?: string
}

export interface FormatClipboardItem {
  type: string
  value: Blob
  json?: string
}
type ClipboardFirstItem = Omit<FormatClipboardItem, 'json'> & {
  json?: string | ClipboardDataRule
}

// extract blob from clipboard
export async function clipboardRead(): Promise<FormatClipboardItem[]> {
  const result: FormatClipboardItem[] = []
  try {
    const clipboardItems = await navigator.clipboard.read()
    for (const clipboardItem of clipboardItems) {
      for (const type of clipboardItem.types) {
        const blob = await clipboardItem.getType(type)
        let item: FormatClipboardItem = {
          type,
          value: blob,
        }
        if (type === 'text/plain') {
          item.json = await blob.text()
        }
        result.push(item)
      }
    }
  } catch (err: any) {
    console.error(err.name, err.message)
  }
  return result
}

// return first clipboardItem  and  formated
export async function clipboardReadFirstItem(): Promise<void | ClipboardFirstItem> {
  const result: FormatClipboardItem[] = []
  try {
    const clipboardItems = await navigator.clipboard.read()
    for (const clipboardItem of clipboardItems) {
      for (const type of clipboardItem.types) {
        const blob = await clipboardItem.getType(type)
        let item: FormatClipboardItem = {
          type,
          value: blob,
        }
        if (type === 'text/plain') {
          item.json = await blob.text()
        }
        result.push(item)
      }
    }
  } catch (e) {
    console.log(e)
  }

  const firstItem = result.shift()
  if (!firstItem) {
    return
  }
  const { type } = firstItem
  if (type === 'text/plain') {
    const { json } = firstItem
    if (!json) {
      return
    }
    try {
      firstItem.json = JSON.parse(json)
    } catch (e) {}
  }
  return firstItem
}

export async function getRenderTreesFromClipboard() {
  const data = await clipboardReadFirstItem()
  if (!data) {
    return
  }
  const { json } = data
  if (!json || typeof json == 'string') {
    return
  }
  return _.get(json, 'renderTrees')
}

export async function isClipboardEmpty() {
  try {
    const clipboardItems = await navigator.clipboard.read()
    return clipboardItems.length == 0
  } catch (e) {
    console.log(e)
  }
  return true
}

export function clipboardReadText() {
  return navigator.clipboard.readText()
}

export async function clipboardWriteText(newClipText: string) {
  return await navigator.clipboard.writeText(newClipText)
}

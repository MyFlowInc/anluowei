import store from '../../store'
import { User } from '../../store/globalSlice'
import { freshCurTableRows, updateTableRow } from '../../store/workflowSlice'
import { fetchRecordById } from './ds-record'
import _ from 'lodash'
export const msgCenter = (msg: any, user: User, dstId: string) => {
  const { user_id, content } = msg
  console.log('msgCenter', msg, typeof msg.content)
  try {
    const data = JSON.parse(msg.content)
    console.log(data)
    if (!data[0]) return
    const { cmd, recordId } = data[0]

    if (cmd === 'SetRecords') {
      user_id !== user.id && updateRecord(recordId, dstId)
    }
    if (cmd === 'addRecords') {
      user_id !== user.id && deleteRecord(dstId)
    }
    if (cmd === 'AddRecords') {
      user_id !== user.id && addRecord(dstId)
    }
  } catch (error) {
    console.log(error)
  }
}

export const updateRecord = async (recordId: string, dstId: string) => {
  const res = await fetchRecordById({
    recordId,
    dstId,
  })
  console.log('socket updateRecord', res)

  const row = _.get(res, 'data.record.0.data')
  if (row) {
    try {
      store.dispatch(updateTableRow({ ...JSON.parse(row), recordId }))
    } catch (error) {
      console.log(error)
    }
  }
}

export const deleteRecord = async (dstId: string) => {
  console.log('deleteRecord', dstId)

  store.dispatch(freshCurTableRows(dstId))
}

export const addRecord = async (dstId: string) => {
  console.log('addRecord', dstId)

  store.dispatch(freshCurTableRows(dstId))
}

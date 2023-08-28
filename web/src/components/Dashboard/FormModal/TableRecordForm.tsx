import CellEditor from './CellEditor'
import { DragDropContext, Droppable, Draggable } from 'react-beautiful-dnd'
import styled from 'styled-components'
import _ from 'lodash'
import {
  freshCurMetaData,
  selectCurFlowDstId,
  selectCurFlowId,
  selectCurMetaData,
  selectCurMetaId,
  setCurTableColumn,
  syncCurMetaDataColumn,
  WorkFlowFieldInfo,
} from '../../../store/workflowSlice'
import { useAppDispatch, useAppSelector } from '../../../store/hooks'
import { Modal } from 'antd'
import { ExclamationCircleFilled } from '@ant-design/icons'
import {
  UpdateDSMetaParams,
  deleteDSMeta,
  saveDSMeta,
  updateDSMeta,
} from '../../../api/apitable/ds-meta'

// 重新记录数组顺序
const reorder = (
  list: WorkFlowFieldInfo[],
  startIndex: number,
  endIndex: number
) => {
  let result = Array.from(list)
  const [removed] = result.splice(startIndex, 1)
  result.splice(endIndex, 0, removed)
  return result
}
const grid = 0
// 设置样式
const getItemStyle = (isDragging: any, draggableStyle: any) => ({
  // some basic styles to make the items look a bit nicer
  userSelect: 'none',
  padding: grid * 2,
  margin: `0 0 ${grid}px 0`,
  // 拖拽的时候背景变化
  // background: isDragging ? "lightgreen" : "#ffffff",
  // styles we need to apply on draggables
  ...draggableStyle,
})

const getListStyle = () => ({
  // background: 'black',
  padding: grid,
  width: '100%',
})

const ItemSettingRoot = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: #ffffff;
`
const DnDRoot = styled.div`
  flex: 1;
  display: flex;
`

interface TableRecordFormProps {
  dstColumns: WorkFlowFieldInfo[]
  form: { [id: string]: string }
  setForm: (value: any) => void
  modalType: string
}

const TableRecordForm: React.FC<TableRecordFormProps> = (props) => {
  const { dstColumns, form, setForm, modalType } = props

  const dispatch = useAppDispatch()
  const curDstId = useAppSelector(selectCurFlowDstId)
  const curFlowId = useAppSelector(selectCurFlowId)
  const metaId = useAppSelector(selectCurMetaId)
  const curMetaData = useAppSelector(selectCurMetaData)

  const onDragEnd = async (result: any) => {
    console.log('onDragEnd', result)
    if (!result.destination) {
      return
    }
    const res: WorkFlowFieldInfo[] = reorder(
      dstColumns,
      result.source.index,
      result.destination.index
    )
    // TODO update
    try {
      const meta_data = _.cloneDeep(curMetaData)
      let temp = _.get(meta_data, 'views.0') as any
      if (temp && meta_data) {
        temp.columns = res.map((item: any) => {
          return {
            fieldId: item.fieldId,
          }
        })
      }
      await saveDSMeta({
        id: metaId!,
        dstId: curDstId!,
        metaData: JSON.stringify(meta_data),
        revision: 0,
        deleted: false,
        sort: null,
        tenantId: null,
      })
      // 同步状态
      dispatch(setCurTableColumn(res))
      dispatch(syncCurMetaDataColumn(res))
    } catch (error) {
      console.log('onDragEnd error', error)
    }
  }

  const updateFieldHandler = async (item: UpdateDSMetaParams) => {
    try {
      const res = await updateDSMeta(item)
      console.log('updateFieldHandler', item, 'res=', res)
      if (!curDstId) {
        return
      }
      await dispatch(freshCurMetaData(curDstId))
    } catch (error) {
      console.log('updateFieldHandler error', error)
    }
  }
  const { confirm } = Modal

  const deleteFieldHandler = async (item: WorkFlowFieldInfo) => {
    confirm({
      title: '是否确认删除?',
      icon: <ExclamationCircleFilled />,
      okText: '确认',
      okType: 'danger',
      cancelText: '取消',
      onOk: async () => {
        console.log('OK')
        const res = await deleteDSMeta({
          dstId: curDstId!,
          fieldIds: [item.fieldId],
        })
        if (!curDstId) {
          return
        }
        dispatch(freshCurMetaData(curDstId))
      },
      onCancel() {
        console.log('Cancel')
      },
    })
  }

  return (
    <ItemSettingRoot>
      <DnDRoot>
        <DragDropContext
          // onDragStart={onDragStart}
          onDragEnd={onDragEnd}
          // onDragUpdate={onDragUpdate}
        >
          <div style={{ width: '100%' }}>
            <Droppable droppableId="droppable" type="common">
              {(provided, snapshot) => {
                const length = dstColumns.length

                return (
                  <div
                    //provided.droppableProps应用的相同元素.
                    {...provided.droppableProps}
                    // 为了使 droppable 能够正常工作必须 绑定到最高可能的DOM节点中provided.innerRef.
                    ref={provided.innerRef}
                    style={getListStyle()}
                  >
                    {dstColumns.map((item, index) => {
                      return (
                        <Draggable
                          key={'field_' + item.fieldId}
                          draggableId={'field_' + item.fieldId}
                          index={index}
                          isDragDisabled={length === 1}
                        >
                          {(provided, snapshot) => {
                            return (
                              <div
                                ref={provided.innerRef}
                                {...provided.draggableProps}
                                {...provided.dragHandleProps}
                                style={getItemStyle(
                                  snapshot.isDragging,
                                  provided.draggableProps.style
                                )}
                              >
                                <CellEditor
                                  item={item}
                                  updateField={updateFieldHandler}
                                  deleteField={deleteFieldHandler}
                                  form={form}
                                  setForm={setForm}
                                  modalType={modalType}
                                />
                              </div>
                            )
                          }}
                        </Draggable>
                      )
                    })}
                    {provided.placeholder}
                  </div>
                )
              }}
            </Droppable>
          </div>
        </DragDropContext>
      </DnDRoot>
    </ItemSettingRoot>
  )
}

export default TableRecordForm

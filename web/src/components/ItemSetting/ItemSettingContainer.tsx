import { DragDropContext, Droppable, Draggable } from 'react-beautiful-dnd'
import styled from 'styled-components'
import { Button, message } from 'antd'
import { useLocation } from 'react-router'
import _ from 'lodash'

import { useAppSelector, useAppDispatch } from '../../store/hooks'
import {
  freshCurMetaData,
  freshCurTableRows,
  selectCurFlowDstId,
  selectCurTableColumn,
  selectCurTableStatusList,
  setCurTableStatusList,
  WorkFlowStatusInfo,
} from '../../store/workflowSlice'
import { color16, getUniqueId, randomString } from '../../util'
import { PlusOutlined } from '@ant-design/icons'
import lockSvg from './assets/lock.svg'
import { UpdateDSMetaParams, updateDSMeta } from '../../api/apitable/ds-meta'
import SingleItem from './SingleItem'
import { useEffect, useState } from 'react'

// 重新记录数组顺序
const reorder = (list: any, startIndex: number, endIndex: number) => {
  const result = Array.from(list)

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
  width: 252,
})

const ItemSettingRoot = styled.div`
  display: flex;
  flex-direction: column;
  width: calc(100% - 24px);
  height: calc(100% - 68px);
  overflow: auto;
  background-color: #f7f7f8;
  /* padding-right: 24px; */
`
const DnDRoot = styled.div`
  flex: 1;
  display: flex;
  padding-top: 4rem;
  justify-content: center;
  @media (max-width: 576px) {
    padding-top: 2rem;
  }
`

const isEdit = (path: string) => {
  return path.includes('workflow-edit')
}

const ItemSettingContainer: React.FC<any> = () => {
  const location = useLocation()
  const dispatch = useAppDispatch()
  const curTableColumn = useAppSelector(selectCurTableColumn)
  const curFlowDstId = useAppSelector(selectCurFlowDstId)
  const statusList = useAppSelector(selectCurTableStatusList)
  const [ messageApi, contextHolder] = message.useMessage()

  const initTable = async (dstId: string) => {
    dispatch(freshCurMetaData(dstId)).then(() => {
      dispatch(freshCurTableRows(dstId))
    })
  }

  // init table data
  const fetchDatas = async (dstId: string) => {
    await initTable(dstId)
  }

  useEffect(() => {
    curFlowDstId && fetchDatas(curFlowDstId)
  }, [curFlowDstId])

  // 交换 状态 位置
  const onDragEnd = async (result: any) => {
    if (!result.destination) {
      return
    }
    const res: any = reorder(
      statusList,
      result.source.index,
      result.destination.index
    )
    dispatch(setCurTableStatusList(res))
    console.log('onDragEnd', res)
    // 同步数据库
    try {
      await updateField(res)
    } catch (e) {
      console.log(e)
    }
  }
  const clickHandler = async () => {
    const options = [...statusList]
    const newItem = {
      id: 'opt' + randomString(10),
      name: '新状态' + options.length,
      color: 0,
    }
    options.push(newItem)
    console.log('clickHandler', options)
    // 同步数据库
    try {
      await updateField(options)
    } catch (e) {
      console.log(e)
    }
  }
  const addStatus = async (item: WorkFlowStatusInfo) => {
    const options = [...statusList]
    const newItem = {
      id: 'opt' + randomString(10),
      name: '新状态' + options.length,
      color: 0,
    }

    const idx = _.findIndex(options, { id: item.id })
    options.splice(idx + 1, 0, newItem)
    console.log('addStats', item, options)
    // 同步数据库
    try {
      await updateField(options)
    } catch (e) {
      console.log(e)
    }
  }

  const updateStatus = async (newItem: WorkFlowStatusInfo, sync: boolean) => {
    const temp = _.cloneDeep(statusList)
    console.log('updateStatus111', temp)
    const idx = _.findIndex(temp, { id: newItem.id })
    temp.splice(idx, 1, newItem)

    // 同步数据库
    try {
      await updateField(temp)
      console.log('FupdateField', temp)
    } catch (e) {
      console.log(e)
    }
  }
  const updateField = async (options: WorkFlowStatusInfo[]) => {
    // TODO:  26 --> 30 暂不支持修改 功能废弃
    messageApi.warning('请联系管理员进行修改')
    return 
  }
  // 数据库更新字段值
  const updateField2 = async (options: WorkFlowStatusInfo[]) => {
    // TODO:  26 --> 30 暂不支持修改 功能废弃
    
    const dstId = curFlowDstId
    const optionStatusField = _.find(curTableColumn, { type: 26 })
    if (!optionStatusField || !dstId) {
      return
    }
    const temp: UpdateDSMetaParams = {
      dstId,
      fieldId: optionStatusField.fieldId,
      name: optionStatusField.name,
      type: 'OptionStatus',
      property: {
        options: options,
      },
    }
    await updateDSMeta(temp)
    await dispatch(freshCurMetaData(dstId))
  }

  const deleteStatus = async (item: WorkFlowStatusInfo) => {
    console.log('deleteStatus', item)
    const temp = [...statusList]
    const idx = _.findIndex(temp, { id: item.id })
    temp.splice(idx, 1)
    // 同步数据库
    try {
      await updateField(temp)
    } catch (e) {
      console.log(e)
    }
  }
  if (statusList.length === 0) {
    return (
      <ItemSettingRoot>
        <center className="flex justify-center">
          <h3 className="mt-16">无面试状态,请联系管理员添加</h3>
        </center>
      </ItemSettingRoot>
    )
  } else {
    return (
      <div style={{height: '100%'}}>
        {contextHolder}
        <div
          className="flex justify-between hidden"
          style={{ marginBottom: '12px', paddingRight: '24px' }}
        >
          <Button
            style={{ background: '#2845D4' }}
            type="primary"
            icon={<PlusOutlined />}
            onClick={clickHandler}
          >
            新增状态
          </Button>
          <Button
            style={{ background: '#2845D4' }}
            type="primary"
            icon={
              <span
                style={{
                  height: '24px',
                  display: 'inline-block',
                  marginRight: '8px',
                }}
              >
                <img src={lockSvg} style={{ marginBottom: '4px' }} />
              </span>
            }
          >
            锁定
          </Button>
        </div >
        <ItemSettingRoot>
          <center>

            <div className="mt-32">
              {statusList.map((item, index) => {
                const length = statusList.length
                return (
                  <SingleItem
                    key={index}
                    isShowEnd={index !== length - 1}
                    isShowDelete={length > 1}
                    isShowMove={length > 1}
                    item={item}
                    addStatus={addStatus}
                    updateStatus={updateStatus}
                    deleteStatus={deleteStatus}
                  />
                )
              })}
            </div>
          </center>
        </ItemSettingRoot>
      </div>
    )
  }

  // return (
  //   <>
  //     <div
  //       className="flex justify-between hidden"
  //       style={{ marginBottom: '12px', paddingRight: '24px' }}
  //     >
  //       <Button
  //         style={{ background: '#2845D4' }}
  //         type="primary"
  //         icon={<PlusOutlined />}
  //         onClick={ clickHandler}
  //       >
  //         新增状态
  //       </Button>
  //       <Button
  //         style={{ background: '#2845D4' }}
  //         type="primary"
  //         icon={
  //           <span
  //             style={{
  //               height: '24px',
  //               display: 'inline-block',
  //               marginRight: '8px',
  //             }}
  //           >
  //             <img src={lockSvg} style={{ marginBottom: '4px' }} />
  //           </span>
  //         }
  //       >
  //         锁定
  //       </Button>
  //     </div>
  //     <ItemSettingRoot>
  //       {/* <WorkFlowForm statusList={statusList} /> */}
  //       <DnDRoot>
  //         <DragDropContext
  //           // onDragStart={onDragStart}
  //           onDragEnd={onDragEnd}
  //           // onDragUpdate={onDragUpdate}
  //         >
  //           <center>
  //             <Droppable droppableId="droppable" type="common">
  //               {(provided, snapshot) => {
  //                 const length = statusList.length
  //                 return (
  //                   <div
  //                     //provided.droppableProps应用的相同元素.
  //                     {...provided.droppableProps}
  //                     // 为了使 droppable 能够正常工作必须 绑定到最高可能的DOM节点中provided.innerRef.
  //                     ref={provided.innerRef}
  //                     style={getListStyle()}
  //                   >
  //                     {statusList.map((item, index) => {
  //                       return (
  //                         <Draggable
  //                           key={item.id}
  //                           draggableId={item.id}
  //                           index={index}
  //                           isDragDisabled={length === 1}
  //                         >
  //                           {(provided, snapshot) => {
  //                             return (
  //                               <div
  //                                 ref={provided.innerRef}
  //                                 {...provided.draggableProps}
  //                                 {...provided.dragHandleProps}
  //                                 style={getItemStyle(
  //                                   snapshot.isDragging,
  //                                   provided.draggableProps.style
  //                                 )}
  //                               >
  //                                 <SingleItem
  //                                   isShowEnd={index !== length - 1}
  //                                   isShowDelete={length > 1}
  //                                   isShowMove={length > 1}
  //                                   item={item}
  //                                   addStatus={addStatus}
  //                                   updateStatus={updateStatus}
  //                                   deleteStatus={deleteStatus}
  //                                 />
  //                               </div>
  //                             )
  //                           }}
  //                         </Draggable>
  //                       )
  //                     })}
  //                     {provided.placeholder}
  //                   </div>
  //                 )
  //               }}
  //             </Droppable>
  //           </center>
  //         </DragDropContext>
  //       </DnDRoot>
  //     </ItemSettingRoot>
  //   </>
  // )
}

export default ItemSettingContainer

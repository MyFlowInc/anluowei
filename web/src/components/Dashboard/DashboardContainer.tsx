import Header from './Header/Header'
import FlowTable, { FlowItemTableDataType } from './FlowTable/core'
import { useHistory, useParams } from 'react-router'
import styled from 'styled-components'
import { useEffect, useState } from 'react'

import _ from 'lodash'
import {
  WorkFlowStatusInfo,
  flatList,
  freshCurMetaData,
  freshCurTableRows,
  selectCurShowMode,
  selectCurStatusFieldId,
  selectCurTableStatusList,
} from '../../store/workflowSlice'
import { NoStatusData } from './NoStatus'
import { BaseLoading } from '../../BaseUI/BaseLoading'
import { useAppDispatch, useAppSelector } from '../../store/hooks'
import { delay } from '../../util/delay'
import { deleteDSCells } from '../../api/apitable/ds-record'

interface ContainerProps {
  reader: boolean
  writer: boolean
  manager: boolean
  children?: React.ReactNode
}

const DashboardRoot = styled.div`
  width: 100%;
  height: 100%;
  overflow: auto;
  padding-right: 20px;
  .mb-8 {
    margin-bottom: 16px;
  }
  @media (max-width: 720px) {
    .table-list {
      width: 100%;
      overflow: hidden;
      .table-item {
        width: 100%;
        overflow: overlay;
      }
      .card-table {
        width: 720px;
      }
    }
  }
`

const DashboardContainer: React.FC<ContainerProps> = ({
  reader,
  writer,
  manager,
}) => {
  // console.log("reader", reader, "writer", writer, "manager", manager);
  const curShowMode = useAppSelector(selectCurShowMode)
  const statusList = useAppSelector(selectCurTableStatusList) || []

  const curStatusFieldId = useAppSelector(selectCurStatusFieldId) || ''
  const [editFlowItemRecord, setEditFlowItemRecord] = useState<
    FlowItemTableDataType | undefined
  >(undefined)

  const history = useHistory()
  const { dstId } = useParams<{ dstId: string }>()
  const [loading, setLoading] = useState(true)

  // for modal
  const [open, setOpen] = useState(false)
  const [modalType, setModalType] = useState('add')

  const dispatch = useAppDispatch()

  const [flatStatusList, setFlatStatusList] = useState<WorkFlowStatusInfo[]>(
    flatList(statusList)
  )
  useEffect(() => {
    setFlatStatusList(flatList(statusList))
  }, [statusList])

  const deleteFlowItemHandler = async (recordId: string) => {
    const params = {
      dstId,
      recordIds: [recordId],
    }
    await deleteDSCells(params)
    dispatch(freshCurTableRows(dstId!))
  }

  const freshFlowItem = async () => {
    setLoading(true)
    await delay()
    setLoading(false)
    setModalType('add')
  }

  const initTable = async (dstId: string) => {
    dispatch(freshCurMetaData(dstId)).then(() => {
      dispatch(freshCurTableRows(dstId))
    })
  }

  // init table data
  const fetchDatas = async (dstId: string) => {
    await initTable(dstId)
    setLoading(false)
  }

  useEffect(() => {
    dstId && fetchDatas(dstId)
  }, [dstId])

  const jumpToAdd = (id: string) => {
    if (id) {
      // TODO:
      const path = '/dashboard/workflow-edit/' + dstId
      history.push(path)
    }
  }

  const StatusView = () => {
    return (
      <>
        {flatStatusList.length > 0 ? (
          flatStatusList.map((item) => {
            return (
              <div key={'FlowTable_' + item.id} className="table-item">
                <h3 className="mt-2">{item.name}</h3>
                <FlowTable
                  className="mb-2 card-table"
                  title={item.name}
                  dstId={dstId}
                  statusId={item.id}
                  statusFieldId={curStatusFieldId}
                  deleteFlowItem={deleteFlowItemHandler}
                  modalType={modalType}
                  setModalType={setModalType}
                  setOpen={setOpen}
                  setEditFlowItemRecord={setEditFlowItemRecord}
                  reader={reader}
                  writer={writer}
                  manager={manager}
                />
              </div>
            )
          })
        ) : (
          <NoStatusData dstId={dstId} clickHandler={jumpToAdd} />
        )}
      </>
    )
  }
  return (
    <DashboardRoot>
      <Header
        freshFlowItem={freshFlowItem}
        open={open}
        setOpen={setOpen}
        modalType={modalType}
        setModalType={setModalType}
        editFlowItemRecord={editFlowItemRecord}
        manager={manager}
      />
      {loading && <BaseLoading />}
      <div className="table-list">
        {curShowMode == 'list' && (
          <FlowTable
            dstId={dstId}
            show-mode={curShowMode}
            deleteFlowItem={deleteFlowItemHandler}
            modalType={modalType}
            setModalType={setModalType}
            setOpen={setOpen}
            setEditFlowItemRecord={setEditFlowItemRecord}
            reader={reader}
            writer={writer}
            manager={manager}
          />
        )}
        {curShowMode == 'status' && <StatusView />}
      </div>
    </DashboardRoot>
  )
}

export default DashboardContainer

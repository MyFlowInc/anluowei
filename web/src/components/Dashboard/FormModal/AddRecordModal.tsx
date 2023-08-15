import React from 'react'
import _ from 'lodash'
import {  Modal } from 'antd'
import {   useAppSelector } from '../../../store/hooks'
import {
  selectCurTableColumn,
  selectCurTableStatusList,
} from '../../../store/workflowSlice'
import CustomModal from './CustomModal'
import { FlowItemTableDataType } from '../FlowTable/core'

interface AddRecordModalProps {
  open: boolean
  setOpen: (a: boolean) => void
  freshFlowItem: () => void
  modalType: string
  setModalType: (v: string) => void
  editFlowItemRecord: FlowItemTableDataType | undefined
}

const AddRecordModal: React.FC<AddRecordModalProps> = (
  props: AddRecordModalProps
) => {
  const {
    modalType,
    setModalType,
    editFlowItemRecord,
    open,
    setOpen,
    freshFlowItem,
  } = props

  const statusList = useAppSelector(selectCurTableStatusList) || []
  const dstColumns = useAppSelector(selectCurTableColumn) || []
 
  const title = modalType === 'add' ? '录入候选人' : '编辑候选人'
  const params = {
    title,
    open,
    setOpen,
    statusList,
    dstColumns,
    freshFlowItem,
    modalType,
    setModalType,
    editFlowItemRecord,
  }

  const modalRender = () => CustomModal(params)

  return <Modal open={open} modalRender={modalRender} width={820}></Modal>
}

export default AddRecordModal

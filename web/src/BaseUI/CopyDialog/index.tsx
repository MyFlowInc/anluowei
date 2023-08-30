import React, { useEffect, useRef, useState } from 'react'
import { Button, Modal } from 'antd'

interface CopyDialogProps {
  isShow: boolean
  closeModal: () => void
  copyText: string
}

const CopyDialog: React.FC<CopyDialogProps> = (props: CopyDialogProps) => {
  const { isShow, closeModal, copyText } = props
  const spanRef = useRef(null)

  const handleOk = () => {
    closeModal()
  }

  const handleCancel = () => {
    closeModal()
  }
  useEffect(() => {
    console.log(11, spanRef)
    let spanElement = spanRef.current
    if (!spanElement) {
      return
    }
    let range = document.createRange() // 创建一个 Range 对象
    range.selectNodeContents(spanElement) // 将 Range 对象设置为 <span> 元素的内容
    let selection = window.getSelection() as any // 获取当前的 Selection 对象
    selection.removeAllRanges() // 清除现有的选区
    selection.addRange(range) // 添加新的选区
  }, [isShow])

  return (
    <Modal
      title="请复制邀请地址"
      open={isShow}
      footer={null}
      onOk={handleOk}
      onCancel={handleCancel}
    >
      <span ref={spanRef}> {copyText}</span>
    </Modal>
  )
}

export default CopyDialog

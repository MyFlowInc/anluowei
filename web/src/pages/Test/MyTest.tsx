import React, { useEffect, useRef, useState } from 'react'
import { Button, Modal } from 'antd'

const CopyModal: React.FC = () => {
  const [isModalOpen, setIsModalOpen] = useState(true)
  const spanRef = useRef(null)
  const showModal = () => {
    setIsModalOpen(true)
  }

  const handleOk = () => {
    setIsModalOpen(false)
  }

  const handleCancel = () => {
    setIsModalOpen(false)
  }
  useEffect(() => {
    let spanElement = spanRef.current
    if (!spanElement) {
      return
    }
    var range = document.createRange() // 创建一个 Range 对象
    range.selectNodeContents(spanElement) // 将 Range 对象设置为 <span> 元素的内容
    var selection = window.getSelection() as any // 获取当前的 Selection 对象
    selection.removeAllRanges() // 清除现有的选区
    selection.addRange(range) // 添加新的选区
  })

  return (
    <Modal
      title="请按下Ctrl + C"
      open={isModalOpen}
      footer={null}
      onOk={handleOk}
      onCancel={handleCancel}
    >
      <span ref={spanRef}>Some contents...</span>
    </Modal>
  )
}

export default CopyModal

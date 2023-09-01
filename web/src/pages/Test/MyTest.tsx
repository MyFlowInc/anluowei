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
    <>
      <div className="max-w-4xl mx-auto px-10 py-4 bg-white rounded-lg shadow-lg">
        <div className="flex flex-col justify-center py-12 items-center">
          <div className="flex justify-center items-center">
            <img
              className="w-64 h-64"
              src="https://res.cloudinary.com/daqsjyrgg/image/upload/v1690257804/jjqw2hfv0t6karxdeq1s.svg"
              alt="image empty states"
            />
          </div>
          <h1 className="text-gray-500 font-medium text-2xl text-center mb-3">
            暂无邀请信息
          </h1>
          {/* <p className="text-gray-500 text-center mb-6">暂无邀请信息</p> */}
        </div>
      </div>
    </>
  )
}

export default CopyModal

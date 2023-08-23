import dragSvg from '../assets/drag.svg'
import editSvg from '../assets/edit.svg'
import deleteSvg from '../assets/delete.svg'
import addSvg from '../assets/add.svg'
import colorSvg from '../assets/color.svg'
import { SyntheticEvent, useEffect, useState } from 'react'
import { Input } from 'antd'
import { ArrowLine } from './ArrowLine'
import { WorkFlowStatusInfo } from '../../../store/workflowSlice'
import { SingleItemContainer } from './style'
import _ from 'lodash'

interface EditTitleProps {
  info: WorkFlowStatusInfo
  setInfo: (item: any) => void
  type: string
  updateType: (e: SyntheticEvent) => void
}

const EditTitle: React.FC<EditTitleProps> = (props) => {
  const { info, setInfo, type, updateType } = props

  const onChange = (event: SyntheticEvent) => {
    const target = event.target as HTMLInputElement
    const temp = { ...info }
    temp.name = target.value
    setInfo((value: any) => {
      return {
        ...value,
        name: target.value,
      }
    })
    console.log('onChange', temp)
  }

  const cancelBubble = (e: SyntheticEvent) => {
    e.stopPropagation()
  }

  if (type === 'edit') {
    return (
      <Input
        placeholder="请输入"
        defaultValue={info.name}
        onChange={onChange}
        onClick={cancelBubble}
      />
    )
  }
  return (
    <div
      className="item-title"
      style={{ background: '#2492B0' }}
      onDoubleClick={updateType}
    >
      {info.name}
    </div>
  )
}

interface SingleItemProps {
  isShowEnd: boolean
  isShowDelete: boolean
  isShowMove: boolean
  item: WorkFlowStatusInfo
  addStatus: (item: WorkFlowStatusInfo) => void
  updateStatus: (item: WorkFlowStatusInfo, sync: boolean) => void
  deleteStatus: (item: WorkFlowStatusInfo) => void
}

const SingleItem: React.FC<SingleItemProps> = (props) => {
  const { item, isShowEnd, updateStatus, addStatus, deleteStatus } = props

  const [info, setInfo] = useState<any>({ ...item })

  const [hovered, setHovered] = useState(false)
  const [type, setType] = useState('view')

  const imgStyle: React.CSSProperties = {
    visibility: hovered ? 'unset' : 'hidden',
  }

  const cancelInput = () => {
    console.log('do cancle input', info.name)

    if (!info || !info.name) {
      return
    }
    if (info.name !== item.name) {
      updateStatus(info, true)
    }
    setType('view')
    document.removeEventListener('click', cancelInput)
  }

  const updateType = (e: SyntheticEvent) => {
    e.stopPropagation()
    return
    if (type === 'edit') {
      cancelInput()
      setType('view')
    } else {
      setType('edit')
    }
  }

  useEffect(() => {
    if (type === 'edit') {
      document.addEventListener('click', cancelInput)
    }
    return () => {
      document.removeEventListener('click', cancelInput)
    }
  }, [type, info.name])

  const addHandler = (item: WorkFlowStatusInfo) => {
    addStatus(item)
  }
  const children = (children: { id: string; name: string }[]) => {
    return (
      <div className="flex" style={{ transform: 'translate(-29%, 0px)' }}>
        {children.map((child: any) => {
          return (
            <SingleItemContainer
              className="single-item-container"
              key={child.name}
            >
              <div className="single-item">
                <div className="item-title" style={{ background: '#2492B0' }}>
                  {child.name}
                </div>
                <div className="operation-container" style={{visibility: 'hidden'}}>
                  <img
                    src={editSvg}
                    className="item-update"
                  ></img>
                </div>
              </div>
              {isShowEnd && (
                <div className="line-container" style={{ height: '48px' }}>
                  {/* <ArrowLine /> */}
                </div>
              )}
            </SingleItemContainer>
          )
        })}
      </div>
    )
  }
  return (
    <SingleItemContainer className="single-item-container">
      <div
        className="single-item"
        onMouseEnter={() => {
          setHovered(true)
        }}
        onMouseLeave={() => {
          setHovered(false)
        }}
      >
        <EditTitle
          info={info}
          setInfo={setInfo}
          type={type}
          updateType={updateType}
        />
        <div className="operation-container" style={imgStyle}>
          <img
            style={imgStyle}
            src={dragSvg}
            className=" cursor-grab; item-move hidden"
          ></img>
          <img
            style={imgStyle}
            src={editSvg}
            className="item-update hidden"
            onClick={updateType}
          ></img>
          <img
            style={imgStyle}
            src={deleteSvg}
            className="item-update hidden"
            onClick={() => {
              deleteStatus(item)
            }}
          ></img>
          <img
            style={imgStyle}
            src={addSvg}
            className="item-add hidden"
            onClick={() => {
              addHandler(item)
            }}
          ></img>
          <img
            style={imgStyle}
            src={colorSvg}
            className="item-add hidden"
            onClick={() => {}}
          ></img>
        </div>
      </div>
      {isShowEnd && !item.children && (
        <div className="line-container">
          <ArrowLine />
        </div>
      )}
      { item.children && (
        <div
          className="line-container"
          style={{ transform: 'translate(-20.5%, 0px)' }}
        >
          <ArrowLine rotate='rotate(45deg)'/>
          <ArrowLine  rotate='rotate(-45deg)'/>
        </div>
      )}
      {item.children && children(item.children)}
    </SingleItemContainer>
  )
}

export default SingleItem

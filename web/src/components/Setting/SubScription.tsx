import { Button, Modal, Result, Tooltip } from 'antd'
import styled from 'styled-components'
import Svg1 from './assets/1-1.svg'
import Svg2 from './assets/1-2.svg'
import Svg3 from './assets/1-3.svg'
import Svg4 from './assets/1-4.svg'
import Svg5 from './assets/1-5.svg'
import Svg6 from './assets/1-6.svg'
import Svg7 from './assets/1-7.svg'
import Png9 from './assets/1-9.png'
import { useEffect, useState } from 'react'
import PayView from './PayView'
import { userGradeList } from '../../api/shop'
import { useAppSelector } from '../../store/hooks'
import { selectGradeList } from '../../store/globalSlice'
import _ from 'lodash'


const ItemUIROOT = styled.div`
  display: flex;
  height: 32px;
  width: 100%;
  .left {
    width: 50%;
    display: flex;
    align-items: center;
    .icon_1 {
      margin-left: 12px;
      margin-right: 12px;
      width: 10px;
      height: 10px;
    }
  }
  .right {
    width: 50%;
    display: flex;
    justify-content: space-around;
    align-items: center;
  }
  .title {
    font-size: 12px;
    font-weight: normal;
    letter-spacing: 0em;
    color: #000000;
  }
`

const SubCardItem = (props: any) => {
  const { className } = props
  const { icon, title, color } = props
  return (
    <ItemUIROOT className="item-content" style={{ background: color }}>
      <div className="left">
        <img src={icon} className="icon_1" />
        <div className="title">{title}</div>
      </div>
      <div className="right">
        <img src={Svg6} />
        <img src={Svg7} />
      </div>
    </ItemUIROOT>
  )
}

const SubCardUIROOT = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  .list-header {
    margin-top: 12px;
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    .right {
      width: 50%;
      display: flex;
      justify-content: space-around;
      align-items: center;
    }
  }

  .list-content {
    margin-top: 12px;
    width: 100%;
  }
  .divider {
    border-bottom: 1px solid #e5e6eb;
  }
  .picture {
    height: 76px;
    border-radius: 4px;
    margin-top: 10px;
    img {
      width: 300px;
      height: 75px;
    }
  }
  .buy-area {
    display: flex;
    margin-top: 52px;
  }
  .policy-area {
    display: flex;
    margin-top: 12px;
    justify-content: center;
    .title-1 {
      font-size: 12px;
      font-weight: normal;
      letter-spacing: 0em;
      color: #2845d4;
    }
    .title-2 {
      margin: 0px 2px;
      font-size: 12px;
      font-weight: normal;
      letter-spacing: 0em;
      color: #000000;
    }
  }
`

const list = [
  {
    icon: Svg1,
    title: '团队协作',
  },
  {
    icon: Svg2,
    title: '无限量的工作流',
  },
  {
    icon: Svg3,
    title: '无限量的工单',
  },
  {
    icon: Svg4,
    title: '多至7个状态',
  },
  {
    icon: Svg5,
    title: '更多新功能',
  },
]

const SubCard = (props: any) => {
  const { className } = props
  const { setMode, isModalOpen, gradeId, setGradeId } = props

  const gradeList = useAppSelector(selectGradeList)

  useEffect(() => {
    isModalOpen && setMode('card')
  }, [isModalOpen])

  const payHandle = (id: string) => {
    setGradeId(id)
    setMode('pay')
  }

  return (
    <SubCardUIROOT className={className}>
      <div className="list-header">
        <div className="right">
          <div>基础版</div>
          <div style={{ marginRight: '0px' }}>高级版</div>
        </div>
      </div>
      <div className="list-content">
        {list.map((item, index) => {
          return (
            <SubCardItem
              key={index}
              {...item}
              color={index % 2 === 1 ? '#FFFFFF' : '#F1F6FF'}
            />
          )
        })}
      </div>
      <div className="picture">
        <img src={Png9} />
      </div>
      <div className="buy-area">
        <Button
          style={{ width: '140px', marginRight: '20px' }}
          type="default"
          onClick={() => {
            payHandle(_.get(gradeList, '0.id') || '')
          }}
        >
          {_.get(gradeList, '0.name') || '¥10.00/月'}
        </Button>

        <Tooltip placement="top" title={'立省17%，相当于83折~'}>
          <Button
            style={{ width: '140px', background: '#2845D4' }}
            type="primary"
            onClick={() => {
              payHandle(_.get(gradeList, '1.id') || '')
            }}
          >
            {_.get(gradeList, '1.name') || '¥100.00/年'}
          </Button>
        </Tooltip>
      </div>
      <div className="policy-area">
        <span className="title-1">隐私政策</span>
        <span className="title-2">和</span>
        <span className="title-1">隐私政策</span>
      </div>
    </SubCardUIROOT>
  )
}
const SubScription = (props: any) => {
  const { className } = props
  const { isModalOpen, setIsModalOpen } = props
  const [gradeId, setGradeId] = useState<string>('')
  const [timer, setTimer] = useState<any>(null) // 定时器

  const showModal = () => {
    setIsModalOpen(true)
  }

  const handleOk = () => {
    setIsModalOpen(false)
  }

  const handleCancel = () => {
    setIsModalOpen(false)
  }
  const [mode, setMode] = useState<'card' | 'pay'>('card')

  useEffect(() => {
    console.log('isModalOpen-- change', isModalOpen)
    isModalOpen && setMode('card')
    clearInterval(timer)
  }, [isModalOpen])

  return (
    <Modal
      title="订阅"
      open={isModalOpen}
      width={mode === 'card' ? 340 : 640}
      onOk={handleOk}
      onCancel={handleCancel}
      footer={null}
    >
      {mode === 'card' && (
        <SubCard {...{ setMode, isModalOpen, gradeId, setGradeId }} />
      )}
      {mode === 'pay' && <PayView {...{ setMode, isModalOpen, gradeId,timer, setTimer }} />}

    </Modal>
  )
}

export default SubScription

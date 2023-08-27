import { Button, Modal } from 'antd'
import styled from 'styled-components'
import { useEffect, useState } from 'react'
import { PlusOutlined } from '@ant-design/icons'
import img1 from './assets/2-1.png'
import img2 from './assets/2-2.png'
import img3 from './assets/2-3.png'
import img4 from './assets/2-4.png'
import SetFlowName from './SetFlowName'

const Step1 = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  .default {
    width: 140px;
    height: 43px;
    border-radius: 4px;
    opacity: 1;
    background: #f7f8fa;
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 20px;
  }
  .default:hover {
    color: #2845d4;
    border: 1px solid #2845d4;
    cursor: pointer;
  }
  .template-title {
    margin-top: 20px;
    margin-bottom: 20px;
    color: #3d3d3d;
  }
  .template-content {
    display: flex;
  }
  .card {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-right: 40px;
    cursor: pointer;
    .card-img {
      display: flex;
      align-items: center;
      justify-content: center;
      width: 100px;
      height: 100px;
      background: #f7f8fa;
      img {
        width: 60px;
        height: 60px;
      }
    }
    .card-title {
      margin-top: 8px;
    }
  }

  .card:hover .card-img {
    border: 1px solid #2845d4;
    color: #2845d4;
  }
  .card:hover .card-title {
    color: #2845d4;
  }
  .card-choosed {
    border: 1px solid #2845d4;
    color: #2845d4;
  }
  .next-content {
    display: flex;
    justify-content: flex-end;
    margin-top: 20px;
    button {
      width: 80px;
    }
  }
`
const Step2 = styled.div`
  display: flex;
`
const TemplateCard = (props: any) => {
  const { className } = props
  const { img, title, type, setFlowType } = props
  const clickHandler = () => {
    setFlowType(type)
  }
  return (
    <div className="card" onClick={clickHandler}>
      <div className={['card-img', className].join(' ')}>
        <img src={img} />
      </div>
      <div className="card-title">{title}</div>
    </div>
  )
}
const list = [
  {
    img: img1,
    title: '敏捷管理',
    type: '2',
  },
  {
    img: img2,
    title: '人事招聘',
    type: '3',
  },
  {
    img: img3,
    title: '用户反馈',
    type: '4',
  },
  {
    img: img4,
    title: '销售机会',
    type: '5',
  },
]

const AddFlowModal = (props: any) => {
  const { className,  } = props
  let { isModalOpen, setIsModalOpen } = props
  const [flowType, setFlowType] = useState('')
  const [curStep, setCurStep] = useState(2)

 
  const handleCancel = () => {
    setIsModalOpen(false)
    setFlowType('')
    setCurStep(2)
  }

  return (
    <Modal
      title="创建岗位"
      open={isModalOpen}
      width={680}
      onCancel={handleCancel}
      footer={null}
    >
      {curStep === 1 && (
        <Step1>
          <div
            className={[flowType === '1' ? 'card-choosed' : '', 'default'].join(
              ' '
            )}
            onClick={() => {
              setFlowType('1')
            }}
          >
            <PlusOutlined />
            <span style={{ marginLeft: '12px' }}>默认岗位</span>
          </div>
          {/* <div className="template-title">选择模板</div> */}
          <div className="template-content">
            {/* {list.map((item, index) => {
              return (
                <TemplateCard
                  key={index}
                  {...item}
                  className={flowType === item.type ? 'card-choosed' : ''}
                  setFlowType={setFlowType}
                />
              )
            })} */}
          </div>
          <div className="next-content">
            <Button
              onClick={handleCancel}
              style={{ background: '#2845D4' }}
              type="primary"
            >
              取消
            </Button>
            <Button
              style={{
                background: !flowType ? '' : '#2845D4',
                marginLeft: '24px',
              }}
              type="primary"
              disabled={!flowType}
              onClick={() => {
                setCurStep(2)
              }}
            >
              下一步
            </Button>
          </div>
        </Step1>
      )}
      {curStep === 2 && (
        <Step2>
          <SetFlowName {...{handleCancel, isModalOpen}}/>
        </Step2>
      )}
    </Modal>
  )
}

export default AddFlowModal

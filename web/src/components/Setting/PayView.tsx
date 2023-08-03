import styled from 'styled-components'
import pay from './assets/pay.png'
import qrcode from './assets/qrcode.png'
import { useEffect, useState } from 'react'
import { generateOrder, orderDetail, toPayAsPC } from '../../api/shop'
import _ from 'lodash'
import { Alert, Space, Spin, Result, Button } from 'antd'

const PaySuccessView = (props: any) => {
  return (
    <Result
      status="success"
      title="购买成功，恭喜加入会员!"
      subTitle=""
      extra={[
        <Button type="primary" key="console">
          去控制台
        </Button>,
        <Button key="buy">查看订单</Button>,
      ]}
    />
  )
}

const UIROOT = styled.div`
  display: flex;
  flex-direction: column;
  width: 600px;
  background-color: #fff;
  border-radius: 4px;
  overflow: hidden;
  height: auto;
  .pay-img {
    width: 596px;
  }
  .content {
    display: flex;
    justify-content: center;
    margin-top: 42px;
    margin-bottom: 72px;
    align-items: center;
    .qrcode {
      margin-right: 34px;
    }
    .right {
      margin-left: 34px;
      .money {
        align-items: center;
        display: flex;
        .money-1 {
          font-size: 12px;
          font-weight: normal;
          line-height: 17px;
          letter-spacing: 0em;
          color: #3d3d3d;
        }
        .money-2 {
          margin-left: 8px;
          font-size: 24px;
          font-weight: bold;
          line-height: 17px;
          letter-spacing: 0em;
          color: #3d3d3d;
        }
      }
      .rule {
        margin-top: 18px;
        display: flex;
        align-items: center;
        .rule-1 {
          font-size: 12px;
          font-weight: normal;
          line-height: 17px;
          letter-spacing: 0em;
        }
        .rule-2 {
          color: #2845d4;
        }
      }
    }
  }
`
const PayView = (props: any) => {
  const { className } = props
  const { gradeId, timer, setTimer } = props

  const [payState, setPayState] = useState<'wait' | 'done' | 'fail'>('wait') // 定时器

  const initPayment = async () => {
    const res = await generateOrder({ gradeId })
    const orderSn = _.get(res, 'data.orderSn')

    const temp = await toPayAsPC({
      orderSn,
      payType: 1, //1 支付宝支付 2 微信支付
    })

    if (temp.data) {
      window.open(temp.data)
    }
    const t = setInterval(async () => {
      const res = await orderDetail({ orderSn })
      const status = _.get(res, 'data.record.0.status')
      console.log('oder detail', status)
      if (status === 3) {
        clearInterval(timer)
        setPayState('done')
      }
    }, 3000)

    setTimer(t)
  }

  useEffect(() => {
    gradeId && initPayment()
  }, [gradeId])

  return (
    <UIROOT className={className}>
      <img src={pay} className="pay-img" />

      {/* <div className="content">
        <div className="qrcode">
          <img src={qrcode} className="qrcode-img" />
        </div>
        <div className="right">
          <div className="money">
            <div className="money-1">应付金额</div>
            <div className="money-2">1899元</div>
          </div>
          <div className="rule">
            <div className="rule-1">支付即同意</div>
            <div className="rule-2">《Pro服务协议》</div>
          </div>
        </div>
      </div> */}
      {payState === 'wait' && (
        <Space direction="vertical" style={{ width: '100%' }}>
          <Spin tip="订单支付中" size="large">
            <div className="content" />
          </Spin>
        </Space>
      )}
      {payState === 'done' && <PaySuccessView />}
    </UIROOT>
  )
}

export default PayView

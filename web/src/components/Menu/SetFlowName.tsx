import { useState, useEffect } from 'react'
import styled from 'styled-components'
import { Button,   Form, Input, message } from 'antd'
import { useHistory,  } from 'react-router'
import _ from 'lodash'
 

import { useAppSelector, useAppDispatch } from '../../store/hooks'
import {
  setWorkflowList,
  updateCurFlowDstId,
  WorkFlowInfo,
} from '../../store/workflowSlice'
import { addWorkFlow, fetchWorkflowList } from '../../api/apitable/ds-table'

interface WorkFlowFormProps {}
const FormRoot = styled.div`
  display: flex;
  justify-content: center;
  padding-top: 24px;
  padding-left: 24px;
  width: 100%;
  .ml-16 {
    margin-left: 16px;
  }
`
const SetFlowName: React.FC<WorkFlowFormProps> = (props: any) => {
  const {handleCancel}= props
  const [messageApi, contextHolder] = message.useMessage()
  const dispatch = useAppDispatch()
  const history = useHistory()
  const [form] = Form.useForm()
  const [loading, setLoading] = useState(false)
  const initForm = () => {
    form.setFieldValue('dstName', '')
  }

  const cancle = () => {
    // history.goBack()
    setLoading(false)
    handleCancel(false)
  }
  const handleCreate = () => {
    setLoading(true)
    form
    .validateFields()
    .then(async () => {
      const data = form.getFieldsValue(["dstName"]);
      const table = {
        ...data,
      }
     const res = await addWorkFlow(table)
     console.log('addWorkFlow', res)
     messageApi
       .open({
         type: 'success',
         content: '创建成功!',
         duration: 1,
       })
       .then(() => {
         fetchWorkflowList({ pageNum: 1, pageSize: 999 }).then((response) => {
           const data = response.data.record as WorkFlowInfo[]
           const list = data.map((item) => ({
             name: item.dstName,
             url: '/dashboard/workflow-view/' + item.dstId,
             ...item
           }))
           dispatch(setWorkflowList(list))
           if (list.length > 0) {
             const item0 = list[list.length - 1]
             history.push(item0.url)
             dispatch(updateCurFlowDstId(item0.dstId))
           }
         })
       })
       .then(()=>{
        cancle()
       })
    })
  }
  useEffect(() => {
    initForm()
  }, [])


  return (
    <FormRoot>
      {contextHolder}
      <Form
        layout={'horizontal'}
        form={form}
        style={{ width: '100%' }}
      >
        <Form.Item
          label="名称"
          name="dstName"
          rules={[{ required: true, message: '请输入工作流名字' }]}
        >
          <Input />
        </Form.Item>
        <Form.Item>
          <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
      
            <Button
              className="ml-16"
              style={{
                background: '#2845D4',
                marginLeft: '24px',
              }}
              type="primary"
              onClick={cancle}
            >
              取消
            </Button>
            <Button
              className="ml-16 tw-text-white"
              style={{
                background: '#2845D4',
                color: '#fff !important',
              }}
              type="primary"
              onClick={handleCreate}
              loading={loading}
              disabled={loading}
            >
              {'创建'}
            </Button>
          </div>
        </Form.Item>
      </Form>
    </FormRoot>
  )
}

export default SetFlowName

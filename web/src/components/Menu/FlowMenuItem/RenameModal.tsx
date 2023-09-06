import { useState, useEffect } from 'react'
import styled from 'styled-components'
import { Button, Form, Input, Modal, message } from 'antd'
import _ from 'lodash'
import { useAppDispatch, useAppSelector } from '../../../store/hooks'
import { renameWorkflow } from '../../../store/workflowSlice'

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
interface RenameModalProps {
  isRenameModalOpen: boolean
  setIsRenameModalOpen: (value: boolean) => void
  flowInfo: any
  renameWorkFlowHandler: (id: string, name: string) => Promise<any>
}
const RenameModal: React.FC<RenameModalProps> = (props: RenameModalProps) => {
  const {
    isRenameModalOpen,
    setIsRenameModalOpen,
    flowInfo,
    renameWorkFlowHandler,
  } = props

  const [messageApi, contextHolder] = message.useMessage()
  const dispatch = useAppDispatch()

  const [form] = Form.useForm()
  const [loading, setLoading] = useState(false)

  const initForm = () => {
    console.log('flowInfo', flowInfo, form)
    form.setFieldValue('dstName', (flowInfo && flowInfo.name) || '')
  }
  useEffect(() => {
    if(flowInfo && form){
      initForm()
    }
  }, [form, flowInfo])

  const cancle = () => {
    setLoading(false)
    setIsRenameModalOpen(false)
  }

  const handleRenameEdit = () => {
    setLoading(true)
    form.validateFields().then(async () => {
      const data = form.getFieldsValue(['dstName'])
      const { dstName } = data
      try {
        const res = await renameWorkFlowHandler(flowInfo.id, dstName)
        console.log('res', res)
        dispatch(renameWorkflow({
          id: flowInfo.id,
          dstName
        }))
        cancle()
      } catch (error) {
        console.log('error', error)
      }
    })
  }

  return (
    <Modal title="重命名" open={isRenameModalOpen} width={680} footer={null} onCancel={cancle}>
      <FormRoot>
        {contextHolder}
        <Form layout={'horizontal'} form={form} style={{ width: '100%' }}>
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
                onClick={handleRenameEdit}
                loading={loading}
                disabled={loading}
              >
                {'更新'}
              </Button>
            </div>
          </Form.Item>
        </Form>
      </FormRoot>
    </Modal>
  )
}

export default RenameModal

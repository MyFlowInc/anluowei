/**
 * type=3
 */
import React, { useState, useEffect } from 'react'
import { Select, Avatar } from 'antd'
import styled from 'styled-components'
import {
  TableColumnItem,
  selectCurFlowDstId,
  selectCurTableColumn,
  selectInviteMembers,
  selectMembers,
} from '../../../../store/workflowSlice'
import _ from 'lodash'
import { useAppSelector } from '../../../../store/hooks'
import { userInvite } from '../../../../api/apitable/ds-share'

const UIListItem = styled.div`
  display: flex;
  width: fit-content;
  align-items: flex-end;
  border-radius: 4px;
  overflow: hidden;
  width: 100%;
  .container {
    width: 100%;
    height: 56px;
    border-radius: 4px;
    display: flex;
    position: relative;
    justify-content: space-between;
    align-items: center;
  }

  .img-container {
    position: relative;
  }
  .left {
    display: flex;
    .word {
      margin-left: 20px;
      display: flex;
      flex-direction: column;
      justify-content: center;
    }
    .title {
      font-size: 14px;
      font-weight: bold;
      line-height: 16px;
      letter-spacing: 0em;
      color: #3d3d3d;
    }
    .content {
      font-size: 14px;
      font-weight: normal;
      line-height: 16px;
      letter-spacing: 0em;
      color: #666666;
    }
  }
`

interface TypeInterviewerProps {
  mode?: 'multiple'
  cell: TableColumnItem
  form: any
  setForm: any
}

let index = 0

const TypeInterviewer: React.FC<TypeInterviewerProps> = (
  props: TypeInterviewerProps
) => {
  const { mode, cell, form, setForm } = props
  const [value, setValue] = useState<string[]>([])
  const inviteMembers = useAppSelector(selectInviteMembers)
  const dstColumns = useAppSelector(selectCurTableColumn)
  const curDstId = useAppSelector(selectCurFlowDstId)

  // 

  // 初始化
  useEffect(() => {
    const temp = _.get(form, cell.fieldId)
    if (!temp) {
      setValue([])
    } else {
      setValue(temp)
    }
  }, [form])

  const notifyInterviewer = async (userIds: string[]) => {
    try {
      const name = _.find(dstColumns, { name_en: 'interviewer_name' }) as any
      const nameFieldId = name?.fieldId
      if (form[nameFieldId] === undefined) {
        return
      }
      if (typeof userIds === 'object') {
        userIds.forEach((id) => {
          userInvite({
            dstId: curDstId!,
            userId: id,
            interviewUserName: form[nameFieldId],
          } as any)
        })
      }
    } catch (error) {
      console.log(error)
    }
  }
  const handleSelectChange = (value: string[]) => {
    setValue(value)
    setForm({
      ...form,
      [cell.fieldId]: value,
    })
    console.log('do notify', value, props)
    notifyInterviewer(value) // 邀请面试官
  }

  return (
    <Select
      mode={mode}
      style={{ width: '100%' }}
      value={value}
      placeholder="请选择成员"
      onChange={handleSelectChange}
      optionLabelProp="label"
    >
      {inviteMembers.map((member: any) => {
        const user = member.userInfo
        return (
          <Select.Option key={user.id} value={user.id} label={user.nickname}>
            <UIListItem>
              <div className="container">
                <div className="left">
                  <div className="img-container">
                    <Avatar size="large" src={user.avatar} />
                  </div>

                  <div className="word">
                    <div className="title">{user.nickname} </div>
                    <div className="content">{user.phone} </div>
                  </div>
                </div>
              </div>
            </UIListItem>
          </Select.Option>
        )
      })}
    </Select>
  )
}

export default TypeInterviewer

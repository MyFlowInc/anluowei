import { Component, useState, useMemo, memo } from 'react'
import { Button, Tabs } from 'antd'
import styled from 'styled-components'
import Svg1 from './assets/avatar.svg'
import { useAppSelector } from '../../store/hooks'
import { freshUser, selectUser } from '../../store/globalSlice'
import UpdateModal from './UpdateModal'
import { userUpdate } from '../../api/user'

const UIROOT = styled.div`
  display: flex;
  width: fit-content;
  align-items: flex-end;
  background-color: #fff;
  border-radius: 4px;
  overflow: hidden;
  .container {
    width: 596px;
    height: 68px;
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
      display: flex;
      flex-direction: column;
      justify-content: center;
    }
    .title {
      font-size: 14px;
      font-weight: 500;
      line-height: 20px;
      letter-spacing: 0px;
      color: #000000;
    }
    .content {
      font-size: 14px;
      font-weight: normal;
      line-height: 20px;
      letter-spacing: 0px;
      color: #666666;
    }
  }
`
const AvatarSetting = (props: any) => {
  const { className } = props
  const user = useAppSelector(selectUser)
  const [open, setOpen] = useState(false)
  const clickHandler = () => {
    setOpen(true)
  }

  const renameHandler =async  (data: any) => {
    await userUpdate({ nickname: data.nickname, id: user.id })
  }

  return (
    <UIROOT className={className}>
      <div className="container">
        <div className="left">
          <div className="word">
            <div className="title">昵称</div>
            <div className="content">{user.nickname}</div>
          </div>
        </div>

        <Button type="default" onClick={clickHandler}>修改昵称</Button>
      </div>
      <UpdateModal  {...{open, setOpen}} title='修改昵称' updateKey='nickname' label='名称' updateApi={renameHandler}/>
    </UIROOT>
  )
}

export default AvatarSetting

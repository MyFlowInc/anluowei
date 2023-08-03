import { Component, useState, useMemo, memo, useEffect } from 'react'
import { Button, Form, Input, Modal, Tabs, Upload, UploadFile, UploadProps, message } from 'antd'
import styled from 'styled-components'
import Svg1 from './assets/avatar.svg'
import { myFlowUpload } from '../../api/upload'
import { userUpdate } from '../../api/user'
import { useAppDispatch, useAppSelector } from '../../store/hooks'
import { freshUser, selectUser } from '../../store/globalSlice'
import { AsyncThunkAction, Dispatch, AnyAction } from '@reduxjs/toolkit'

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
  .avator-image {
    width: 48px;
    height: 48px;
    border-radius: 100px;
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
  const dispatch = useAppDispatch()

  // const [avatarUrl, setAvatarUrl] = useState(user.avatar)
  const uploadAvatar = async () => {
    const inputTag = document.createElement('input')
    inputTag.type = 'file'
    inputTag.accept = 'image/*'
    inputTag.click()
    inputTag.onchange = async () => {
      let res
      try {
        if (inputTag.files && inputTag.files[0]) {
          console.log(inputTag.files)
          const formData = new FormData()
          formData.append('file', inputTag.files[0])
          const res = await myFlowUpload(formData)
          console.log(res.data.url)
          const avatar = res.data.url
          if (avatar) {
            await userUpdate({ avatar, id: user.id })
            dispatch(freshUser())
          }
        }
      } catch (e) {
        console.log(e, res)
      }
    }
  }

  return (
    <UIROOT className={className}>
      <div className="container">
        <div className="left">
          <div className="img-container">
            <img src={user.avatar || Svg1} className="avator-image" />
          </div>

          <div className="word">
            <div className="title">头像</div>
            <div className="content">支持2M以内的JPG或PNG图片</div>
          </div>
        </div>

        <Button type="default" onClick={uploadAvatar}>
          修改头像
        </Button>
      </div>
    </UIROOT>
  )
}


 
  
export default AvatarSetting
 
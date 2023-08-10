import styled from 'styled-components'
import DefaultAvatarSvg from './assets/avatar.svg'
import { IonIcon } from '@ionic/react'
import { logOutOutline } from 'ionicons/icons'
import { Menu, Modal, Avatar, Button } from "antd";
import { useState } from "react";
import { ExclamationCircleFilled } from "@ant-design/icons";
import { useHistory } from "react-router-dom";
import { useAppSelector } from "../../store/hooks";
import { selectUser } from "../../store/globalSlice";
import { selectCollapsed } from "../../store/globalSlice";
export const UserRoot = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 100%;
  /* padding: 0px 16px; */
  .user-left {
    display: flex;
    align-items: center;

    .user-info {
      margin-left: 8px;
      .user-name {
        font-size: 14px;
        font-weight: 500;
        line-height: 20px;
        letter-spacing: 0px;
      }
      .user-level {
        font-size: 10px;
      }
    }
  }
  .user-log-out {
    margin-left: 16px;
    .log-out-logo {
      width: 18px;
      height: 18px;
    }
  }
`;

const { confirm } = Modal;

const showConfirm = ({ history }: any) => {
  confirm({
    title: "确认要退出登录吗?",
    icon: <ExclamationCircleFilled />,
    okText: "确认",
    cancelText: "取消",
    onOk() {
      history.push("/login");
    },
    onCancel() {
      console.log("Cancel");
    },
  });
};

interface UserTagProps {
  collapsed: boolean;
}

const UserTag = styled.div<UserTagProps>`
  position: relative;
  display: flex;
  justify-content: ${(props) => (props.collapsed ? "center" : "start")};
  align-items: center;
  padding: 16px;
  cursor: pointer;
  transition: padding 0.3s cubic-bezier(0.645, 0.045, 0.355, 1);

  :hover {
    border-radius: 8px;
    background-color: rgba(0, 0, 0, 0.06);
  }

  .user-info {
    position: absolute;
    left: 64px;
    display: ${(props) => (props.collapsed ? "none" : "block")};
    font-size: 16px;
    font-weight: 600;
    white-space: nowrap;
  }
`;

function UserCard() {
  const user = useAppSelector(selectUser);
  const collapsed = useAppSelector(selectCollapsed);
  return (
    <UserTag collapsed={collapsed}>
      <Avatar icon={<DefaultAvatarSvg />} src={user.avatar} />
      <div className="user-info">{user.nickname}</div>
    </UserTag>
  );
}

export default UserCard

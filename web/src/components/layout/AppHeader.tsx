import React from "react";
import { MenuFoldOutlined, MenuUnfoldOutlined } from "@ant-design/icons";
import { Layout } from "antd";
import { useAppSelector } from "../../store/hooks";
import { selectCurFlowName } from "../../store/workflowSlice";
import styled from "styled-components";
import { selectCollapsed,setCollapsed } from "../../store/globalSlice";
import { useDispatch } from "react-redux";
import { useLocation } from "react-router";

interface AppHeaderProps {
  
}

const { Header } = Layout;

const UIContent = styled.div`
  display: flex;
  align-items: center;
  width: 50%;
  height: 64px;
  .title {
    margin-left: 8px;
    font-size: 18px;
    font-weight: bold;
    letter-spacing: 0em;
    color: #000000;
  }
`;

const AppHeader: React.FC<AppHeaderProps> = (props) => {

  let name = useAppSelector(selectCurFlowName);
  const collapsed = useAppSelector(selectCollapsed);
  const dispatch = useDispatch();
  const location = useLocation();

  if(location.pathname.includes('notification')){
    name = name || '通知'
  }
  if(location.pathname.includes('setting')){
    name = name || '设置'
  }
  if(location.pathname.includes('workflow-edit')){
    name = name + ' - 工作流设置'
  }
  // console.log('name',name)
  
  return (
    <Header style={{ padding: 0, background: "#EDEFF3" }}>
      <UIContent>
        {React.createElement(
          collapsed ? MenuUnfoldOutlined : MenuFoldOutlined,
          {
            className: "trigger",
            onClick: () =>  dispatch(setCollapsed(!collapsed)) 
          }
        )}
        <div className="title">{name}</div>
      </UIContent>
    </Header>
  );
};

export default AppHeader;

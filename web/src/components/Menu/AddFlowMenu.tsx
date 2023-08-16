import React, { useContext, useEffect, useRef, useState } from "react";

import styled from "styled-components";
import iconSvg from "./assets/compute.svg";
import addSvg from "./assets/add-icon.svg";
import { Menu, Tooltip } from "antd";

import { selectUser } from "../../store/globalSlice";
import TourContext from "../../context/tour";
import { useAppSelector } from "../../store/hooks";
import { selectCollapsed } from "../../store/globalSlice";
import AddFlowModal from "./AddFlowModal";

const ItemRootUI = styled.div`
  display: flex;
  height: 42px;
  border-radius: 4px;
  opacity: 1;
  align-items: center;
  justify-content: space-between;
  .icon_1 {
    width: 14px;
    height: 14px;
    margin: 0px 10px;
  }
  .title {
    height: 32px;
    font-size: 14px;
    font-weight: bold;
    line-height: 32px;
    letter-spacing: 0em;
    color: #000000;
  }
  .icon_add {
    width: 16px;
    height: 16px;
  }
`;
// 覆盖样式
const RootUI = styled.div`
  .ant-menu-item {
    /* padding-left: 16px !important; */
  }
  .ant-menu-item:hover {
    background-color: unset !important;
  }
`;

const MenuItemLabel = (props: any) => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const user = useAppSelector(selectUser);
  const isAdmin =
    user.roles && user.roles.some((item: any) => item.code === "fe-admin");
  return (
    <ItemRootUI>
      <div className="title">岗位列表</div>
      {isAdmin && (
        <Tooltip title={"新建岗位"} placement="right">
          <img
            src={addSvg}
            onClick={() => {
              setIsModalOpen(true);
            }}
            className="icon_add"
          />
        </Tooltip>
      )}
      <AddFlowModal {...{ isModalOpen, setIsModalOpen }} />
    </ItemRootUI>
  );
};

const AddFlowMenu: React.FC<any> = (props: any) => {
  const ref = useRef(null);
  const { tourRefs, setTourRefs } = useContext(TourContext);
  const collapsed = useAppSelector(selectCollapsed);
  const item = {
    key: "add",
    icon: <img src={iconSvg} className="icon_1" />,
    label: !collapsed ? <MenuItemLabel {...props} /> : null,
  };

  useEffect(() => {
    if (tourRefs && setTourRefs && !tourRefs.includes(ref)) tourRefs?.push(ref);
  }, []);

  return (
    <RootUI ref={ref} id="add_flow_menu">
      <Menu mode="inline" selectable={false} items={[item]} />
    </RootUI>
  );
};

export default AddFlowMenu;

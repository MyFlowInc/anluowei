import React, { useState, useContext, useEffect, useRef } from "react";

import { useHistory } from "react-router";

import AddRecordModal from "../FormModal/AddRecordModal";
import { FlowItemTableDataType } from "../FlowTable/core";
import styled from "styled-components";
import { PlusOutlined } from "@ant-design/icons";
import { Button, notification } from "antd";
import ShowMode from "./ShowMode";
import TourContext from "../../../context/tour";
import FilterArea from "./FilterArea";
import notifySvg from "./assets/notify.svg";
import { useAppSelector } from "../../../store/hooks";
import { selectUser } from "../../../store/globalSlice";
import { getInviteList } from "../../../api/apitable/ds-share";
import { agreeInvite } from "../../../api/apitable/ds-share";
import _ from "lodash";

const SvgIcon = styled.div`
  width: 24px;
  height: 24px;
  border-radius: 4px;
  opacity: 1;
  background: #008bfd;
  display: flex;
  justify-content: center;
  align-items: center;
`;
const NotifyTitle = styled.div`
  height: 24px;
  opacity: 1;
  font-size: 12px;
  font-weight: normal;
  line-height: 24px;
  letter-spacing: 0px;
  color: #666666;
`;
const NotificationRoot = styled.div`
  display: flex;
  flex-direction: column;
  .title {
    font-size: 12px;
    font-weight: 500;
    line-height: 24px;
    letter-spacing: 0em;
    color: #000000;
  }
  .operate {
    display: flex;
    margin-top: 16px;
    justify-content: right;
  }
`;
const Notification = (props: any) => {
  const { className, content, info } = props;
  const curUser = useAppSelector(selectUser);
  const history = useHistory();

  const clickHandler = async () => {
    console.log("clickHandler", curUser.id, info);
    try {
      await agreeInvite({
        userId: curUser.id,
        dstId: info.dstId,
      });
      // history.go(0); // TODO 添加刷新workflow接口
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <NotificationRoot>
      <div className="title">
        <li>{content}</li>
      </div>

      <div className="operate">
        <Button
          style={{ background: "#2845D4", marginRight: "20px" }}
          type="primary"
          onClick={clickHandler}
        >
          同意
        </Button>
        <Button
          style={{ background: "#2845D4", marginRight: "20px" }}
          type="primary"
          onClick={() => {
            history.push("/dashboard/notification");
          }}
        >
          立即查看
        </Button>
      </div>
    </NotificationRoot>
  );
};

const HeaderContainer = styled.div`
  display: flex;
  align-items: center;
  /* justify-content: space-between; */
  width: 100%;
  margin-bottom: 8px;
  .header-right {
    flex: 1;
    display: flex;
    justify-content: space-between;
  }
  .show-mode {
    margin-left: 12px;
    width: fit-content;
  }
`;

interface HeaderProps {
  freshFlowItem: () => void;
  open: boolean;
  setOpen: (v: boolean) => void;
  modalType: string;
  setModalType: (v: string) => void;
  editFlowItemRecord: FlowItemTableDataType | undefined;
  manager: boolean;
}

const Header: React.FC<HeaderProps> = (props) => {
  const {
    freshFlowItem,
    modalType,
    setModalType,
    open,
    setOpen,
    editFlowItemRecord,
    manager,
  } = props;

  const fetchInviteList = async (options: any = {}) => {
    const res = await getInviteList(options);
    const list = _.get(res, "data.record") || [];
    if (list.length > 0) {
      list.length > 3
        ? openNotification(list.slice(0, 3))
        : openNotification(list);
    }
  };

  const ref = useRef(null);
  const { tourRefs, setTourRefs } = useContext(TourContext);
  useEffect(() => {
    if (tourRefs && setTourRefs && !tourRefs.includes(ref)) tourRefs.push(ref);
    // setTourRefs([...tourRefs, ref])
  }, []);

  useEffect(() => {
    // TODO: 演示通知
    fetchInviteList();
  }, []);

  // 通知
  const [api, contextHolder] = notification.useNotification({ maxCount: 3 });

  const openNotification = (inviteList: any) => {
    inviteList.forEach((item: any, i: number) => {
      setTimeout(() => {
        api.open({
          key: item.dstId,
          message: <NotifyTitle>邀请通知</NotifyTitle>,
          description: <Notification content={item.content} info={item} />,
          icon: (
            <SvgIcon>
              <img src={notifySvg} />
            </SvgIcon>
          ),
        });
      }, 500 * i);
    });
  };

  return (
    <HeaderContainer>
      {contextHolder}
      {manager && (
        <Button
          id="add_flow_item"
          ref={ref}
          onClick={() => {
            setOpen(true);
            setModalType("add");
          }}
          style={{ background: "#2845D4" }}
          type="primary"
          icon={<PlusOutlined />}
        >
          新建工单
        </Button>
      )}
      <div className="header-right">
        <ShowMode className="show-mode" />
        <FilterArea className="filter-area" />
      </div>
      <AddRecordModal
        open={open}
        editFlowItemRecord={editFlowItemRecord}
        setOpen={setOpen}
        freshFlowItem={freshFlowItem}
        modalType={modalType}
        setModalType={setModalType}
      />
    </HeaderContainer>
  );
};

export default Header;

import { Button, Dropdown, MenuProps, Tag } from "antd";
import React, { useEffect, useState } from "react";
import styled from "styled-components";
import {
  WorkFlowStatusInfo,
  flatList,
  selectCurStatusFieldId,
  selectCurTableColumn,
} from "../../../store/workflowSlice";
import _ from "lodash";
import { useAppSelector } from "../../../store/hooks";
import { ArrowLeftOutlined, ArrowRightOutlined } from "@ant-design/icons";

const StatusTagRoot = styled.div``;

const genItems = (list: WorkFlowStatusInfo[]): MenuProps["items"] => {
  return list.map((item) => {
    return {
      key: item.id || "", // TODO: ts类型体操 没处理好这个类型
      label: <div>{item.name}</div>,
    };
  });
};

interface StatusTagProps {
  statusList: WorkFlowStatusInfo[];
  form: any;
  setForm: (value: any) => void;
}
export const StatusTag: React.FC<StatusTagProps> = (props) => {
  const { statusList, form, setForm } = props;
  const [name, setName] = useState("未选择");
  const curStatusFieldId = useAppSelector(selectCurStatusFieldId);
  const [showLeft, setShowLeft] = useState(false);
  const [showRight, setShowRight] = useState(false);
  const [showChildren, setShowChildren] = useState(false);

  const [flatStatusList, setFlatStatusList] = useState<WorkFlowStatusInfo[]>(
    flatList(statusList)
  );
  useEffect(() => {
    setFlatStatusList(flatList(statusList));
  }, [statusList]);

  useEffect(() => {
    const optionId = form[curStatusFieldId];
    updateArrowStatus(optionId);
    if (optionId) {
      setName(_.find(flatStatusList, { id: optionId })?.name || "未选择");
    } else {
      setName(flatStatusList[0]?.name);
      handleMenuClick({ key: flatStatusList[0]?.id || "" });
    }
  }, [form]);

  const updateArrowStatus = (optionId: string) => {
    const index = _.findIndex(flatStatusList, { id: optionId });
    const item = flatStatusList[index];

    if (item && item.name === "未开始") {
      setShowRight(true);
    } else {
      setShowRight(false);
    }

    if (index > 0 && index < flatStatusList.length) {
      setShowLeft(true);
    } else {
      setShowLeft(false);
    }

    if (item && item.children && item.children.length > 0) {
      setShowChildren(true);
    } else {
      setShowChildren(false);
    }
  };

  const handleMenuClick = (info: { key: string }) => {
    const id = info.key;
    if (!id) return;
    setForm({
      ...form,
      [curStatusFieldId]: id,
    });
  };
  const leftHandler = () => {
    const optionId = form[curStatusFieldId];
    const index = _.findIndex(flatStatusList, { id: optionId });
    const item = flatStatusList[index];
    // 特殊处理
    if (item.name === "已录取") {
      const ele = _.find(flatStatusList, { name: "进行中" });
      const id = ele?.id;
      if (!id) return;
      setForm({
        ...form,
        [curStatusFieldId]: id,
      });
      return;
    }

    const id = flatStatusList[index - 1]?.id;
    if (!id) return;
    setForm({
      ...form,
      [curStatusFieldId]: id,
    });
  };
  const rightHandler = () => {
    const optionId = form[curStatusFieldId];
    const index = _.findIndex(flatStatusList, { id: optionId });
    const id = flatStatusList[index + 1]?.id;
    if (!id) return;
    setForm({
      ...form,
      [curStatusFieldId]: id,
    });
  };
  const rejectHandler = () => {
    const idx = _.findIndex(flatStatusList, { name: "已淘汰" });
    const id = flatStatusList[idx]?.id;
    if (!id) return;
    setForm({
      ...form,
      [curStatusFieldId]: id,
    });
  };
  const approveHandler = () => {
    const idx = _.findIndex(flatStatusList, { name: "已录取" });
    const id = flatStatusList[idx]?.id;
    if (!id) return;
    setForm({
      ...form,
      [curStatusFieldId]: id,
    });
  };

  return (
    <StatusTagRoot>
      <div>
        {showLeft && (
          <ArrowLeftOutlined
            style={{ marginRight: "8px" }}
            onClick={leftHandler}
          />
        )}
        <Dropdown
          menu={{
            items: genItems(flatStatusList),
            onClick: (info: { key: string }) => {
              handleMenuClick(info);
            },
          }}
          placement="bottom"
        >
          <Tag
            color={`${
              name === "已淘汰"
                ? "red"
                : name === "已录取"
                ? "green"
                : "#2db7f5"
            }`}
            key={1}
          >
            {name}
          </Tag>
        </Dropdown>
        {showRight && <ArrowRightOutlined onClick={rightHandler} />}
        {showChildren && (
          <>
            <Button
              type="dashed"
              size="small"
              style={{ color: "#ff4d4f" }}
              onClick={rejectHandler}
            >
              淘汰
            </Button>
            <Button
              className="ml-2"
              style={{ color: "#52c41a" }}
              type="dashed"
              size="small"
              onClick={approveHandler}
            >
              录取
            </Button>
          </>
        )}
      </div>
    </StatusTagRoot>
  );
};

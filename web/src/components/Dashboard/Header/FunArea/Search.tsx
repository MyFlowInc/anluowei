import React, { useState } from "react";
import { Button, Space, Form, Input, Popover } from "antd";
import {
  SearchOutlined,
  CloseOutlined,
  LeftOutlined,
  RightOutlined,
} from "@ant-design/icons";

import { useAppDispatch } from "../../../../store/hooks";
import { setCurSearchText } from "../../../../store/workflowSlice";

import type { TableColumnItem } from "../../../../store/workflowSlice";

interface SearchContentProps {
  records: any[];
  colunms: TableColumnItem[];
  onClosePop: () => void;
  children?: React.ReactNode;
}

const SearchContent: React.FC<SearchContentProps> = ({
  records,
  colunms,
  onClosePop,
}) => {
  const [form] = Form.useForm();
  const dispatch = useAppDispatch();
  const [total, setTotal] = useState<number>(0);

  const count = (
    <Space.Compact block>
      <Button size="small" type="text" icon={<LeftOutlined />} />
      0/{total} <Button size="small" type="text" icon={<RightOutlined />} />
    </Space.Compact>
  );

  const handleValuesChanged = (changedValues: any, allValues: any) => {
    dispatch(setCurSearchText(changedValues.searchField));
    const RowsNum = records.length;
    const ColsNum = colunms.length;
    let MatchNum = 0;

    const reg: RegExp = new RegExp(changedValues.searchField, "gi");
    for (let i = 0; i < RowsNum; i++) {
      for (let j = 0; j < ColsNum; j++) {
        const record = records[i];
        const colunm = colunms[j];

        if (typeof record[colunm.fieldId] !== `undefined`) {
          const isMatched = reg.test(record[colunm.fieldId]);
          if (isMatched) {
            MatchNum++;
          }
        }
      }
    }
    setTotal(MatchNum);
  };

  const handleClosePop = () => {
    dispatch(setCurSearchText(""));
    form.resetFields();
    onClosePop();
  };

  return (
    <Form
      form={form}
      name="SearchForm"
      onValuesChange={handleValuesChanged}
      style={{ width: 360, padding: "10px" }}
    >
      <Form.Item name="searchField" style={{ margin: 0, padding: 0 }}>
        <Space>
          <Input
            placeholder="在数据表中查找"
            style={{ width: 320 }}
            prefix={<SearchOutlined />}
            suffix={count}
          />
          <Button
            size="small"
            type="text"
            icon={<CloseOutlined />}
            onClick={handleClosePop}
          />
        </Space>
      </Form.Item>
    </Form>
  );
};

interface SearchProps {
  records: any[];
  colunms: TableColumnItem[];
  children?: React.ReactNode;
}

const Search: React.FC<SearchProps> = ({ records, colunms }) => {
  const [open, setOpen] = React.useState<boolean>(false);

  const handleTogglePop = () => {
    setOpen((pre) => !pre);
  };

  return (
    <Popover
      placement="bottom"
      content={
        <SearchContent
          records={records}
          colunms={colunms}
          onClosePop={handleTogglePop}
        />
      }
      trigger="click"
      open={open}
    >
      <Button type="text" icon={<SearchOutlined />} onClick={handleTogglePop}>
        搜索
      </Button>
    </Popover>
  );
};

export default Search;

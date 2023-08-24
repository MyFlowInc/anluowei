import React, { useState } from "react";
import { Button, Space, Form, Input, Popover } from "antd";
import {
  SearchOutlined,
  CloseOutlined,
  LeftOutlined,
  RightOutlined,
} from "@ant-design/icons";
import _ from "lodash";
import { useAppSelector, useAppDispatch } from "../../../../store/hooks";
import {
  setCurSearchText,
  selectMembers,
  selectSearchText,
} from "../../../../store/workflowSlice";
import type { TableColumnItem } from "../../../../store/workflowSlice";

const getMemberList = (value: any, userList: any) => {
  if (
    typeof value === `undefined` ||
    typeof userList === `undefined` ||
    !(value instanceof Array)
  ) {
    return;
  }

  return value.map((item: string) => {
    return userList.filter((m: any) => m.id === item)[0];
  });
};

const getFileName = (url: string) => {
  const file = url.split("/").pop();
  const fileName = file?.split("-")[1] || "";
  return fileName;
};

function encode(keyword: string) {
  const reg = /[\[\(\$\^\.\]\*\\\?\+\{\}\\|\)]/gi;
  return keyword.replace(reg, (key) => `\\${key}`);
}

interface SearchContentProps {
  open: boolean;
  records: any[];
  colunms: TableColumnItem[];
  onClosePop: () => void;
  children?: React.ReactNode;
}

const SearchContent: React.FC<SearchContentProps> = ({
  open,
  records,
  colunms,
  onClosePop,
}) => {
  const [form] = Form.useForm();
  const dispatch = useAppDispatch();
  const searchText = useAppSelector(selectSearchText);
  const userList = useAppSelector(selectMembers);
  const [total, setTotal] = useState<number>(0);

  const count = (
    <Space.Compact block>
      <Button size="small" type="text" icon={<LeftOutlined />} />
      0/{total} <Button size="small" type="text" icon={<RightOutlined />} />
    </Space.Compact>
  );

  const search = (keyword: string) => {
    const RowsNum = records.length;
    const ColsNum = colunms.length;
    let MatchNum = 0;

    for (let i = 0; i < RowsNum; i++) {
      for (let j = 0; j < ColsNum; j++) {
        const record = records[i];
        const colunm = colunms[j];

        switch (colunm.type) {
          case 6:
            const attachmentName =
              record[colunm.fieldId] && getFileName(record[colunm.fieldId]);
            const rega: RegExp = new RegExp(keyword, "gi");
            const isMatch =
              keyword && keyword !== "" ? rega.test(attachmentName) : false;
            if (isMatch) {
              MatchNum++;
            }
            break;
          case 13:
            const memberList = getMemberList(record[colunm.fieldId], userList);
            if (memberList) {
              for (let i = 0; i < memberList.length; i++) {
                const regb: RegExp = new RegExp(keyword, "gi");
                const isMatch =
                  keyword && keyword !== ""
                    ? regb.test(memberList[i].nickname)
                    : false;
                if (isMatch) {
                  MatchNum++;
                  break;
                }
              }
            }
            break;
          case 27: // 不搜索评论内容
            continue;
          default:
            if (typeof record[colunm.fieldId] !== `undefined`) {
              const regc: RegExp = new RegExp(keyword, "gi");
              const isMatched = regc.test(record[colunm.fieldId]);
              if (isMatched) {
                MatchNum++;
              }
            }
        }
      }
    }
    setTotal(MatchNum);
  };

  const resetSearch = () => {
    dispatch(setCurSearchText(""));
    setTotal(0);
  };

  React.useEffect(() => {
    const value = form.getFieldValue("searchField");
    if (open && value && value !== "") {
      search(searchText);
    } else {
      resetSearch();
    }
  }, [records, colunms, open]);

  const handleValuesChanged = (changedValues: any, allValues: any) => {
    const keyword = encode(changedValues.searchField as string);
    dispatch(setCurSearchText(keyword));
    search(keyword);
  };

  const handleClosePop = () => {
    resetSearch();
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
          open={open}
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

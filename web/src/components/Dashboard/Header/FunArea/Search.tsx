import React, { useState } from "react";
import { Button, Space, Form, Input, Popover } from "antd";
import {
  SearchOutlined,
  CloseOutlined,
  LeftOutlined,
  RightOutlined,
} from "@ant-design/icons";
import _ from "lodash";
import { useAppSelector } from "../../../../store/hooks";
import { selectMembers } from "../../../../store/workflowSlice";
import type { TableColumnItem } from "../../../../store/workflowSlice";

import { NumFieldType } from "../../TableColumnRender";

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

const getStatusText = (value: any, fieldConfig: any) => {
  const temp = _.get(fieldConfig, "property.options") || [];
  if (temp.length === 0) {
    return;
  }

  const item0 = temp[0];
  let options: any = [];

  if (typeof item0 === "string") {
    options = temp.map((item: any) => ({
      label: item,
      value: item,
    }));
  }

  if (typeof item0 === "object") {
    options = temp.map((item: any) => ({
      label: item.name,
      value: item.id,
    }));
  }

  return _.find(options, { value: value })?.label || "";
};

function encode(keyword: string) {
  const reg = /[\[\(\$\^\.\]\*\\\?\+\{\}\\|\)]/gi;
  return keyword.replace(reg, (key) => `\\${key}`);
}

interface SearchContentProps {
  open: boolean;
  records: any[];
  columns: TableColumnItem[];
  onClosePop: () => void;
  children?: React.ReactNode;
}

const SearchContent: React.FC<SearchContentProps> = ({
  open,
  records,
  columns,
  onClosePop,
}) => {
  const [form] = Form.useForm();
  const userList = useAppSelector(selectMembers);
  const [matchList, setMatchList] = useState<string[]>([]);
  const [matchIndex, setMatchIndex] = useState<number>(0);

  const previous = () => {
    const curIndex = matchIndex > 0 ? matchIndex - 1 : matchList.length - 1;
    setMatchIndex(curIndex);
    goto(matchList[curIndex]);
  };

  const next = () => {
    const curIndex = matchIndex < matchList.length - 1 ? matchIndex + 1 : 0;
    setMatchIndex(curIndex);
    goto(matchList[curIndex]);
  };

  const matchedStyle = (matchs: string[] = matchList, b: boolean = true) => {
    for (let i = 0; i < matchs.length; i++) {
      const cellId = matchs[i];
      const cIndex = _.get(cellId.split("-"), 2);
      const element = document.getElementById(cellId);
      if (element) {
        if (b) {
          cIndex && cIndex === "0"
            ? element.setAttribute(
                "style",
                "position: sticky; left: 32px; background-color: #B0E0E6;"
              )
            : element.setAttribute("style", "background-color: #B0E0E6;");
        } else {
          element.removeAttribute("style");
          // fixed
          cIndex &&
            cIndex === "0" &&
            element.setAttribute("style", "position: sticky; left: 32px;");
        }
      }
    }
  };

  const goto = (eId: string, matchs: string[] = matchList) => {
    matchedStyle(matchs);
    const element = document.getElementById(eId);
    if (element) {
      element.setAttribute(
        "style",
        "background-color: #87CECB; border: 1px solid #0000FF; font-weight:600; color: #000080;"
      );
      element.scrollIntoView({
        behavior: "smooth",
        block: "end",
        inline: "center",
      });
    }
  };

  const count = (
    <Space.Compact block>
      <Button
        size="small"
        type="text"
        icon={<LeftOutlined />}
        onClick={previous}
      />
      {matchList.length > 0 ? matchIndex + 1 : 0}/{matchList.length}
      <Button
        size="small"
        type="text"
        icon={<RightOutlined onClick={next} />}
      />
    </Space.Compact>
  );

  const initSearchResult = (matchList: string[]) => {
    setMatchList(matchList);
    setMatchIndex(0);
    matchList.length > 0 && goto(matchList[0], matchList);
  };

  const search = (keyword: string) => {
    const RowsNum = records.length;
    const ColsNum = columns.length;
    const list = [];

    for (let i = 0; i < RowsNum; i++) {
      for (let j = 0; j < ColsNum; j++) {
        const record = records[i];
        const colunm = columns[j];

        switch (colunm.type) {
          case NumFieldType.Attachment:
            const attachmentName =
              record[colunm.fieldId] && getFileName(record[colunm.fieldId]);
            if (attachmentName) {
              const rega: RegExp = new RegExp(keyword, "gi");
              const isMatched =
                keyword && keyword !== "" ? rega.test(attachmentName) : false;
              isMatched && list.push(`cell-${i}-${j}`);
            }
            break;
          case NumFieldType.Member:
            const memberList = getMemberList(record[colunm.fieldId], userList);
            if (memberList) {
              for (let k = 0; k < memberList.length; k++) {
                const regb: RegExp = new RegExp(keyword, "gi");
                const isMatched =
                  keyword && keyword !== ""
                    ? regb.test(memberList[k].nickname)
                    : false;
                if (isMatched) {
                  list.push(`cell-${i}-${j}`);
                  break;
                }
              }
            }
            break;
          case NumFieldType.OptionStatus:
          case NumFieldType.InterviewStatus:
          case NumFieldType.InviteStatus:
            const text = getStatusText(
              record[colunm.fieldId],
              colunm.fieldConfig
            );
            if (text && text !== "") {
              const regc: RegExp = new RegExp(keyword, "gi");
              const isMatched =
                keyword && keyword !== "" ? regc.test(text) : false;
              isMatched && list.push(`cell-${i}-${j}`);
            }
            break;

          case NumFieldType.discuss: // 不搜索评论内容
            continue;
          default:
            if (typeof record[colunm.fieldId] !== `undefined`) {
              const regc: RegExp = new RegExp(keyword, "gi");
              const isMatched = regc.test(record[colunm.fieldId]);
              isMatched && list.push(`cell-${i}-${j}`);
            }
        }
      }
    }
    initSearchResult(list);
  };

  const resetSearch = () => {
    matchedStyle(matchList, false);
    form.resetFields();
    setMatchList([]);
  };

  React.useEffect(() => {
    resetSearch();
  }, [records, columns, open]);

  const handleValuesChanged = (changedValues: any, allValues: any) => {
    if (changedValues.searchField && changedValues.searchField !== "") {
      matchedStyle(matchList, false);
      const keyword = encode(changedValues.searchField as string);
      search(keyword);
    } else {
      resetSearch();
    }
  };

  const handleClosePop = () => {
    resetSearch();
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
  columns: TableColumnItem[];
  children?: React.ReactNode;
}

const Search: React.FC<SearchProps> = ({ records, columns }) => {
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
          columns={columns}
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

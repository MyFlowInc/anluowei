import React, { useEffect, useState } from "react";
import { Button, Popover, Form, Typography, Select, Segmented } from "antd";
import { SortAscendingOutlined, CloseOutlined } from "@ant-design/icons";
import ArrowRightFilled from "../assets/ArrowRightFilled";

import { useAppDispatch } from "../../../../store/hooks";
import { setCurTableRows } from "../../../../store/workflowSlice";

import styled from "styled-components";
import _ from "lodash";

import type { SelectProps } from "antd";
import type { TableColumnItem } from "../../../../store/workflowSlice";

const SortSegmented = styled(({ from, to, ...rest }) => (
  <div {...rest}>
    {from}
    <ArrowRightFilled style={{ fontSize: "18px", padding: "0px 8px" }} />
    {to}
  </div>
))`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0px 8px;
  font-size: 18;
  font-weight: 700;

  color: ${(props) => (props.v ? "#0000ff" : "#000000")};
`;

interface SearchContentProps {
  records: any[];
  columns: TableColumnItem[];
  children?: React.ReactNode;
}

const SearchContent: React.FC<SearchContentProps> = ({ records, columns }) => {
  const [form] = Form.useForm();
  const dispatch = useAppDispatch();

  const [sort, set] = useState<string | number | "">("");

  const options: SelectProps["options"] = columns.map((item: any) => {
    return {
      label: item.name,
      value: item.fieldId,
    };
  });

  const handleSortChange = (value: string | number) => {
    set(value as string);

    const fieldId = form.getFieldValue("condition");
    if (fieldId) {
      const sortRecords = records.slice().sort((a: any, b: any) => {
        const x = a[fieldId];
        const y = b[fieldId];
        const reg: RegExp = new RegExp("[a-zA-Z0-9]", "g");
        if (reg.test(x) || reg.test(y)) {
          if (x > y) {
            return value === "asc" ? 1 : -1;
          } else if (x < y) {
            return value === "asc" ? -1 : 1;
          } else {
            return 0;
          }
        } else {
          const num = a[fieldId].localeCompare(b[fieldId], "zh-Hans-CN");
          return value === "asc" ? num : -num;
        }
      });

      dispatch(setCurTableRows(sortRecords));
    }
  };

  useEffect(() => {
    form.resetFields();
  }, [records, columns]);

  return (
    <Form
      form={form}
      name="sortForm"
      style={{ width: 460, margin: "0px 16px" }}
      initialValues={{ condition: "" }}
    >
      <Form.Item>
        <Typography.Text>设置排序条件</Typography.Text>
      </Form.Item>
      <div style={{ display: "flex", justifyContent: "space-around" }}>
        <Form.Item name="condition">
          <Select
            style={{ width: 180 }}
            options={[{ label: "请选择条件", value: "" }, ...options]}
          />
        </Form.Item>
        <Form.Item>
          <Segmented
            defaultValue=""
            options={[
              {
                label: <SortSegmented from="A" to="Z" v={sort === "asc"} />,
                value: "asc",
              },
              {
                label: <SortSegmented from="Z" to="A" v={sort === "desc"} />,
                value: "desc",
              },
            ]}
            onChange={handleSortChange}
          />
        </Form.Item>
        {/* <Button type="text" icon={<CloseOutlined />} /> */}
      </div>
    </Form>
  );
};

interface SortProps {
  records: any[];
  columns: TableColumnItem[];
  children?: React.ReactNode;
}

const Sort: React.FC<SortProps> = ({ records, columns }) => {
  return (
    <Popover
      placement="bottom"
      content={<SearchContent records={records} columns={columns} />}
      trigger="click"
    >
      <Button type="text" icon={<SortAscendingOutlined />}>
        排序
      </Button>
    </Popover>
  );
};

export default Sort;

import { useState } from "react";
import { Popover, Button, Form, Input, Select, Space, Typography } from "antd";
import {
  SearchOutlined,
  CloseOutlined,
  PlusOutlined,
  LeftOutlined,
  RightOutlined,
} from "@ant-design/icons";
import type { SelectProps } from "antd";
import styled from "styled-components";
import Svg1 from "./assets/1.svg";
import Svg2 from "./assets/2.svg";
import Svg3 from "./assets/3.svg";
import Svg4 from "./assets/4.svg";
import { useAppSelector } from "../../../store/hooks";
import { selectCurTableColumn } from "../../../store/workflowSlice";

const UiRoot = styled.div`
  display: flex;
  align-items: center;

  .item {
    display: flex;
    align-items: center;
    cursor: pointer;
  }
  .item-img {
    width: 14px;
    height: 14px;
  }
  .item-title {
    margin-left: 4px;
    font-size: 12px;
    font-weight: normal;
    line-height: 20px;
    letter-spacing: 0px;
    color: #3d3d3d;
  }
  .item-divider {
    width: 0px;
    height: 14px;
    opacity: 1;
    border: 1px solid #cdcdcd;
    margin: 0px 20px;
  }
`;

interface FilterAreaProps {
  className?: string;
}
const list = [
  {
    url: Svg1,
    title: "筛选",
  },
  {
    url: Svg2,
    title: "排序",
  },
  // {
  //   url: Svg3,
  //   title: "高级",
  // },
  {
    url: Svg4,
    title: "搜索",
  },
];

interface FilterContentProps {
  children?: React.ReactNode;
}

const FilterContent: React.FC<FilterContentProps> = () => {
  const [form] = Form.useForm();
  const dstColumns = useAppSelector(selectCurTableColumn);

  return (
    <Form
      form={form}
      name="FilterForm"
      style={{ width: 550, margin: "0px 16px" }}
    >
      <Form.Item>
        <div style={{ display: "flex", justifyContent: "space-between" }}>
          <Typography.Text>设置筛选条件</Typography.Text>
          <div>
            符合以下
            <Select
              popupMatchSelectWidth={false}
              style={{ margin: "0px 8px", width: 70 }}
              dropdownStyle={{ width: 120 }}
            >
              <Select.Option value="all">所有</Select.Option>
              <Select.Option value="one">任一</Select.Option>
            </Select>
            条件
          </div>
        </div>
      </Form.Item>
      <Form.List name="filters">
        {(fields, { add, remove }) => {
          return (
            <>
              {fields.map(({ key, name, ...restField }) => {
                const options: SelectProps["options"] = dstColumns.map(
                  (item) => {
                    return {
                      label: item.name,
                      value: item.fieldId,
                    };
                  }
                );

                return (
                  <div
                    key={key}
                    style={{ display: "flex", justifyContent: "space-around" }}
                  >
                    <Form.Item {...restField} name={[name, "item"]}>
                      <Select
                        style={{ width: 180 }}
                        defaultValue={options[0]}
                        options={options}
                      />
                    </Form.Item>
                    <Form.Item {...restField} name={[name, "op"]}>
                      <Select
                        popupMatchSelectWidth={false}
                        style={{ margin: "0px 8px", width: 90 }}
                        dropdownStyle={{ width: 200 }}
                        defaultValue="eq"
                      >
                        <Select.Option value="eq">等于</Select.Option>
                        <Select.Option value="ne">不等于</Select.Option>
                        <Select.Option value="include">包含</Select.Option>
                        <Select.Option value="notinclude">不包含</Select.Option>
                        <Select.Option value="null">为空</Select.Option>
                        <Select.Option value="notnull">不为空</Select.Option>
                      </Select>
                    </Form.Item>
                    <Form.Item
                      {...restField}
                      name={[name, "content"]}
                      style={{ width: "100%" }}
                    >
                      <Input placeholder="请输入" />
                    </Form.Item>
                    <Button
                      type="text"
                      icon={<CloseOutlined />}
                      onClick={() => remove(name)}
                    />
                  </div>
                );
              })}
              <div>
                <Space align="baseline">
                  <Form.Item>
                    <Button
                      type="dashed"
                      onClick={() => add()}
                      block
                      icon={<PlusOutlined />}
                    >
                      添加条件
                    </Button>
                  </Form.Item>
                </Space>
              </div>
            </>
          );
        }}
      </Form.List>
    </Form>
  );
};

interface SearchContentProps {
  onClosePop: () => void;
  children?: React.ReactNode;
}

const SearchContent: React.FC<SearchContentProps> = ({ onClosePop }) => {
  const count = (
    <Space.Compact block>
      <Button size="small" type="text" icon={<LeftOutlined />} />
      0/0 <Button size="small" type="text" icon={<RightOutlined />} />
    </Space.Compact>
  );

  return (
    <Form name="SearchForm" style={{ width: 360, padding: "10px" }}>
      <Form.Item style={{ margin: 0, padding: 0 }}>
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
            onClick={onClosePop}
          />
        </Space>
      </Form.Item>
    </Form>
  );
};

const FilterArea: React.FC<FilterAreaProps> = (props) => {
  const { className } = props;
  const [open, setOpen] = useState<boolean>(false);

  const handleTogglePop = () => {
    setOpen((pre) => !pre);
  };

  const FilterItem = (props: any) => {
    const { item, showDivider } = props;

    return (
      <div className="item">
        <div className="flex items-center hover:bg-slate-200">
          <img src={item.url} className="item-img" />
          <div className="item-title">{item.title}</div>
        </div>

        {showDivider && <div className="item-divider"></div>}
      </div>
    );
  };

  return (
    <UiRoot className={className}>
      {list.map((item, idx) => {
        if (item.title === "筛选") {
          return (
            <div className="item" key={"FilterItem" + idx}>
              <Popover
                placement="bottom"
                content={<FilterContent />}
                trigger="click"
              >
                <div className="flex items-center hover:bg-slate-200">
                  <img src={item.url} className="item-img" />
                  <div className="item-title">{item.title}</div>
                </div>
              </Popover>
              <div className="item-divider"></div>
            </div>
          );
        } else if (item.title === "搜索") {
          return (
            <div className="item" key={"FilterItem" + idx}>
              <Popover
                placement="bottom"
                content={<SearchContent onClosePop={handleTogglePop} />}
                trigger="click"
                open={open}
              >
                <div
                  className="flex items-center hover:bg-slate-200"
                  onClick={handleTogglePop}
                >
                  <img src={item.url} className="item-img" />
                  <div className="item-title">{item.title}</div>
                </div>
              </Popover>
            </div>
          );
        } else {
          return (
            <FilterItem
              item={item}
              showDivider={idx !== list.length - 1}
              key={"FilterItem" + idx}
            />
          );
        }
      })}
    </UiRoot>
  );
};

export default FilterArea;

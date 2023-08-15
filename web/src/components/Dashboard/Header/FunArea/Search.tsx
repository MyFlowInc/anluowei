import React from "react";
import { Button, Space, Form, Input, Popover } from "antd";
import {
  SearchOutlined,
  CloseOutlined,
  LeftOutlined,
  RightOutlined,
} from "@ant-design/icons";

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

const Search: React.FC = () => {
  const [open, setOpen] = React.useState<boolean>(false);

  const handleTogglePop = () => {
    setOpen((pre) => !pre);
  };

  return (
    <Popover
      placement="bottom"
      content={<SearchContent onClosePop={handleTogglePop} />}
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

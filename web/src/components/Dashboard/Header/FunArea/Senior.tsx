import React from "react";
import { Button } from "antd";
import { CaretUpOutlined } from "@ant-design/icons";

const Senior: React.FC = () => {
  return (
    <Button type="text" icon={<CaretUpOutlined />}>
      高级
    </Button>
  );
};

export default Senior;

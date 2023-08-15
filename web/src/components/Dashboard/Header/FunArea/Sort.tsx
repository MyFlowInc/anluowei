import React from "react";
import { Button, Space } from "antd";

import Svg2 from "../assets/2.svg";

const Sort: React.FC = () => {
  return (
    <Button
      type="text"
      icon={<img src={Svg2} style={{ width: "14px", height: "14px" }} />}
    >
      排序
    </Button>
  );
};

export default Sort;

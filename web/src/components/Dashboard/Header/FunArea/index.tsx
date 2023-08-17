import React from "react";
import { Space } from "antd";

import { useAppSelector } from "../../../../store/hooks";
import {
  selectCurTableColumn,
  selectCurTableRecords,
} from "../../../../store/workflowSlice";

import Filter from "./Filter";
import Sort from "./Sort";
import Senior from "./Senior";
import Search from "./Search";

interface FunAreaProps {
  children?: React.ReactNode;
}

const FunArea: React.FC<FunAreaProps> = () => {
  const dstColumns = useAppSelector(selectCurTableColumn);
  const records = useAppSelector(selectCurTableRecords);

  return (
    <Space>
      <Filter records={records} colunms={dstColumns} />
      <Sort />
      <Senior />
      <Search records={records} colunms={dstColumns} />
    </Space>
  );
};

export default FunArea;

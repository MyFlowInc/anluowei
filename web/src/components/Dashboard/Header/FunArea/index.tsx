import React from "react";
import { Space } from "antd";
import _ from "lodash";
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
      <Filter records={records} columns={dstColumns} />
      <Sort records={records} columns={dstColumns} />
      {/* <Senior /> */}
      <Search records={records} columns={dstColumns} />
    </Space>
  );
};

export default FunArea;

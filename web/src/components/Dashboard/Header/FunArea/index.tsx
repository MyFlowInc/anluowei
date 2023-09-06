import React from "react";
import { Checkbox, Space } from "antd";
import _ from "lodash";
import { useAppDispatch, useAppSelector } from "../../../../store/hooks";
import {
  selectCurTableColumn,
  selectCurTableRecords,
  setCurTableRows,
} from "../../../../store/workflowSlice";

import Filter from "./Filter";
import Sort from "./Sort";
import Senior from "./Senior";
import Search from "./Search";
import ShowMine from "./ShowMine";

interface FunAreaProps {
  children?: React.ReactNode;
}

const FunArea: React.FC<FunAreaProps> = () => {
  const dstColumns = useAppSelector(selectCurTableColumn);
  const records = useAppSelector(selectCurTableRecords);


  return (
    <Space>
      <ShowMine  records={records} columns={dstColumns} />
      <Filter records={records} columns={dstColumns} />
      <Sort columns={dstColumns} />
      {/* <Senior /> */}
      <Search columns={dstColumns} />
    </Space>
  );
};

export default FunArea;

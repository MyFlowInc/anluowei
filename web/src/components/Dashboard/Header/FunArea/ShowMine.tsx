import React, { useEffect, useState } from "react";
 
import { useAppDispatch, useAppSelector } from "../../../../store/hooks";
import Checkbox, { CheckboxChangeEvent } from "antd/es/checkbox";
import {
  TableColumnItem, selectCurFlowDstId, selectCurTableColumn, setCurTableRows,
 
} from "../../../../store/workflowSlice";
import { selectUser } from "../../../../store/globalSlice";
import _ from "lodash";


interface ShowMineProps {
    records: any[];
    columns: TableColumnItem[];
  }
const ShowMine: React.FC<ShowMineProps> = (props) => {
  const { records, columns } = props;
  const dispatch = useAppDispatch();
  const user = useAppSelector(selectUser)
  const dstColumns = useAppSelector(selectCurTableColumn)
  const dstId = useAppSelector(selectCurFlowDstId)
  const [checked, setChecked] = useState(false);
  

  useEffect(() => {
    setChecked(false)
  }, [dstId]);

  const onChange = (e: CheckboxChangeEvent) => {
    console.log(`checked = ${e.target.checked}`);
    const checked = e.target.checked;
    setChecked(checked)

    if(checked) {
        const name = _.find(dstColumns, { name_en: 'interviewer' }) as any
        const nameFieldId = name?.fieldId
        const filterRecords = records.filter((record) => {
            return record[nameFieldId] && record[nameFieldId].length
        }).filter((record) => {
            return record[nameFieldId].includes(user?.id)
        })

        dispatch(setCurTableRows(filterRecords));
    } else{
        dispatch(setCurTableRows(records));

    }
   
  };
  return <Checkbox onChange={onChange} checked={checked}>只显示我的面试</Checkbox>;
};

export default ShowMine;

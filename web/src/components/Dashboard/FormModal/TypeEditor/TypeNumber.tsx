/**
 * type=3
 */

import React, { useState, useRef, useEffect, SyntheticEvent } from "react";
import { Input, InputNumber } from "antd";
import { TableColumnItem } from "../../../../store/workflowSlice";
import _ from "lodash";

interface TypeNumberProps {
  mode?: "multiple";
  cell: TableColumnItem;
  form: any;
  setForm: any;
}

const TypeNumber: React.FC<TypeNumberProps> = (props: TypeNumberProps) => {
  const { cell, form, setForm } = props;

  const onChangeContent = (value: number | null) => {
    setForm({
      ...form,
      [cell.fieldId]: value,
    });
  };

  return (
    <InputNumber
      value={_.get(form , cell.fieldId)}
      placeholder="请输入"
      onChange={onChangeContent}
    />
  );
};

export default TypeNumber;

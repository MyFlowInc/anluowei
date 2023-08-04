/**
 * type=3
 */

import React, { useState, useRef, useEffect, SyntheticEvent } from 'react'
import { DatePicker, DatePickerProps, Input, InputNumber } from 'antd'
import { TableColumnItem } from '../../../../store/workflowSlice'
import _ from 'lodash'
import dayjs from 'dayjs';
// import customParseFormat from 'dayjs/plugin/customParseFormat';
// dayjs.extend(customParseFormat);

interface TypeDateTimeProps {
  mode?: 'multiple'
  cell: TableColumnItem
  form: any
  setForm: any
}

const TypeDateTime: React.FC<TypeDateTimeProps> = (
  props: TypeDateTimeProps
) => {
  const {   cell, form, setForm } = props

  const value =
    (dayjs(form[cell.fieldId]).isValid() && dayjs(form[cell.fieldId])) || null;

  const onChange: DatePickerProps["onChange"] = (date, dateString) => {
    console.log(date, dateString);
    setForm({
      ...form,
      [cell.fieldId]: dateString,
    });
  };

  return (
    <DatePicker
      value={value}
      showTime={{ format: "HH:mm" }}
      format="YYYY-MM-DD HH:mm"
      onChange={onChange}
    />
  );
  
}

export default TypeDateTime

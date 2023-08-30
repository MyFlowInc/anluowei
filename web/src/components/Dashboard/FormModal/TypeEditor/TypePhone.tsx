import React, { useRef, SyntheticEvent } from "react";
import { Input } from "antd";
import { PhoneOutlined } from "@ant-design/icons";
import { TableColumnItem } from "../../../../store/workflowSlice";
import _ from "lodash";

interface TypePhoneProps {
  mode?: "multiple";
  cell: TableColumnItem;
  form: any;
  setForm: any;
}

const TypePhone: React.FC<TypePhoneProps> = (props: TypePhoneProps) => {
  const { cell, form, setForm } = props;
  const el = useRef<any>(null);

  const onChangeContent = (event: SyntheticEvent) => {
    const target = event.target as HTMLInputElement;
    const value = target.value;
    setForm({
      ...form,
      [cell.fieldId]: value,
    });
  };

  return (
    <Input
      placeholder="请输入手机号码"
      value={_.get(form, cell.fieldId)}
      onChange={onChangeContent}
      suffix={<PhoneOutlined />}
    />
  );
};

export default TypePhone;

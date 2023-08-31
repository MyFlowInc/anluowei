import React from "react";
import { Button, Popover, Form, Typography, Select, Input, Space } from "antd";
import { FilterFilled, CloseOutlined, PlusOutlined } from "@ant-design/icons";
import _ from "lodash";

import { useAppDispatch } from "../../../../store/hooks";
import { setCurTableRows } from "../../../../store/workflowSlice";

import type { SelectProps } from "antd";
import type { TableColumnItem } from "../../../../store/workflowSlice";

function encode(keyword: string) {
  const reg = /[\[\(\$\^\.\]\*\\\?\+\{\}\\|\)]/gi;
  return keyword.replace(reg, (key) => `\\${key}`);
}

interface ConditionType {
  conditionName: string;
  conditionOperator: string;
  conditionValue: string;
}

interface FilterTableProps {
  records: any[];
  columns: TableColumnItem[];
  children?: React.ReactNode;
}

const FilterTable: React.FC<FilterTableProps> = ({ records, columns }) => {
  const [form] = Form.useForm();
  const dispatch = useAppDispatch();

  const [firstFieldId, setFirstFieldId] = React.useState<string>();
  const [relation, SetRelation] = React.useState<"either" | "every">("either");
  const [conditions, SetConditions] = React.useState<ConditionType[]>([]);

  const CompareCondition = (record: any, condition: ConditionType): boolean => {
    switch (condition.conditionOperator) {
      case "eq":
        return record[condition.conditionName] === condition.conditionValue;

      case "ne":
        return record[condition.conditionName] !== condition.conditionValue;

      case "include":
        const target = record[condition.conditionName] as string;
        // console.log("search condition", record[condition.conditionName]);
        const rega: RegExp = new RegExp(encode(condition.conditionValue), "gi");
        return rega.test(target);

      case "notinclude":
        const target1 = record[condition.conditionName] as string;
        // console.log("search condition", record[condition.conditionName]);
        const regb: RegExp = new RegExp(encode(condition.conditionValue), "gi");
        return !regb.test(target1);

      case "null":
        return (
          record[condition.conditionName] === undefined ||
          record[condition.conditionName] === ""
        );

      case "notnull":
        return (
          record[condition.conditionName] &&
          record[condition.conditionName] !== ""
        );

      default:
        return true;
    }
  };

  const handleFilter = () => {
    const filterRecords = records.filter((record) => {
      if (conditions.length < 1) {
        return true;
      } else if (conditions.length === 1) {
        return CompareCondition(record, _.get(conditions, 0));
      } else {
        switch (relation) {
          case "either":
            return conditions.some((value: ConditionType) =>
              CompareCondition(record, value)
            );

          case "every":
            return conditions.every((value: ConditionType) =>
              CompareCondition(record, value)
            );

          default:
        }
      }
    });

    console.log("filterRecords", filterRecords);
    dispatch(setCurTableRows(filterRecords));
  };

  const handleRelationChange = (value: "either" | "every") => {
    SetRelation(value);
  };

  const handleValuesChanged = (changedValues: any, allValues: any) => {
    const values = allValues.conditions
      .map((condition: any) => {
        const conditionName =
          condition && condition.conditionName
            ? condition.conditionName
            : firstFieldId;
        const conditionOperator =
          condition && condition.conditionOperator
            ? condition.conditionOperator
            : "eq";
        const conditionValue =
          condition && condition.conditionValue
            ? condition.conditionValue
            : undefined;
        return { conditionName, conditionOperator, conditionValue };
      })
      .filter(
        (v: ConditionType) =>
          v.conditionOperator === "null" ||
          v.conditionOperator === "notnull" ||
          (v.conditionOperator !== "null" && v.conditionValue !== undefined) ||
          (v.conditionOperator !== "notnull" && v.conditionValue !== undefined)
      );

    SetConditions(values);
  };

  React.useEffect(() => {
    conditions.length > 0 && handleFilter();
  }, [relation, conditions]);

  React.useEffect(() => {
    form.resetFields();
    setFirstFieldId(_.get(columns, 0).fieldId);
  }, [records, columns]);

  return (
    <Form
      form={form}
      name="FilterForm"
      style={{ width: 550, margin: "0px 16px" }}
      onValuesChange={handleValuesChanged}
    >
      <Form.Item name="conditionRelation">
        <div style={{ display: "flex", justifyContent: "space-between" }}>
          <Typography.Text>设置筛选条件</Typography.Text>
          <div>
            符合以下
            <Select
              popupMatchSelectWidth={false}
              style={{ margin: "0px 8px", width: 70 }}
              dropdownStyle={{ width: 120 }}
              defaultValue="either"
              onChange={handleRelationChange}
            >
              <Select.Option value="every">所有</Select.Option>
              <Select.Option value="either">任一</Select.Option>
            </Select>
            条件
          </div>
        </div>
      </Form.Item>
      <Form.List
        name="conditions"
        initialValue={[
          {
            conditionName: _.get(columns, 0).fieldId,
            conditionOperator: "eq",
          },
        ]}
      >
        {(fields, { add, remove }) => {
          return (
            <>
              {fields.map(({ key, name, ...restField }) => {
                const options: SelectProps["options"] = columns.map(
                  (item: any) => {
                    return {
                      label: item.name,
                      value: item.fieldId,
                    };
                  }
                );

                return (
                  <div
                    key={key}
                    style={{ display: "flex", justifyContent: "space-around" }}
                  >
                    <Form.Item {...restField} name={[name, "conditionName"]}>
                      <Select style={{ width: 180 }} options={options} />
                    </Form.Item>
                    <Form.Item
                      {...restField}
                      name={[name, "conditionOperator"]}
                    >
                      <Select
                        popupMatchSelectWidth={false}
                        style={{ margin: "0px 8px", width: 90 }}
                        dropdownStyle={{ width: 200 }}
                      >
                        <Select.Option value="eq">等于</Select.Option>
                        <Select.Option value="ne">不等于</Select.Option>
                        <Select.Option value="include">包含</Select.Option>
                        <Select.Option value="notinclude">不包含</Select.Option>
                        <Select.Option value="null">为空</Select.Option>
                        <Select.Option value="notnull">不为空</Select.Option>
                      </Select>
                    </Form.Item>
                    <Form.Item
                      {...restField}
                      name={[name, "conditionValue"]}
                      style={{ width: "100%" }}
                    >
                      <Input placeholder="请输入" />
                    </Form.Item>
                    <Button
                      type="text"
                      icon={<CloseOutlined />}
                      onClick={() => remove(name)}
                    />
                  </div>
                );
              })}
              <div>
                <Space align="baseline">
                  <Form.Item>
                    <Button
                      type="dashed"
                      onClick={() =>
                        add(
                          {
                            conditionName: firstFieldId,
                            conditionOperator: "eq",
                          },
                          fields.length
                        )
                      }
                      block
                      icon={<PlusOutlined />}
                    >
                      添加条件
                    </Button>
                  </Form.Item>
                </Space>
              </div>
            </>
          );
        }}
      </Form.List>
    </Form>
  );
};

interface FilterProps {
  records: any[];
  columns: TableColumnItem[];
  children?: React.ReactNode;
}

const Filter: React.FC<FilterProps> = ({ records, columns }) => {
  return (
    <Popover
      placement="bottom"
      content={<FilterTable records={records} columns={columns} />}
      trigger="click"
    >
      <Button type="text" icon={<FilterFilled />}>
        筛选
      </Button>
    </Popover>
  );
};

export default Filter;

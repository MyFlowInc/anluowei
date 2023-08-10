import React from "react";
import { Button, Tag, Avatar } from "antd";
import { Link } from "react-router-dom";

import { DiscussModal } from "./FormModal/TypeEditor/TypeDiscuss";

import _ from "lodash";
/**
 * The type of field returned by the interface	The type of the corresponding field
      SingleText	single-line text
      Text	Multi-line text
      SingleSelect	Single choice
      MultiSelect	Multiple choice
      Number	Number
      Currency	Currency
      Percent	Percentage
      DateTime	Datetime
      Attachment	Attachment
      Member	Member
      Checkbox	Check
      Rating	Rating
      URL	Website
      Phone	A telephone number.
      Email	Email
      MagicLink	MagicLink
      MagicLookUp	MagicLookUp
      Formula	Intelligent formula
      AutoNumber	Autoincrement number
      CreatedTime	Create Timestamp
      LastModifiedTime	Modify Timestamp
      CreatedBy	Created by
      LastModifiedBy	Updated by
 */
export const NumFieldType = {
  NotSupport: 0,
  Text: 1, // Multi-line text
  Number: 2,
  SingleSelect: 3,
  MultiSelect: 4,
  DateTime: 5,
  Attachment: 6,
  Link: 7,
  URL: 8,
  Email: 9,
  Phone: 10,
  Checkbox: 11,
  Rating: 12,
  Member: 13,
  LookUp: 14,
  // RollUp : 15,
  Formula: 16,
  Currency: 17,
  Percent: 18,
  SingleText: 19, //
  AutoNumber: 20,
  CreatedTime: 21,
  LastModifiedTime: 22,
  CreatedBy: 23,
  LastModifiedBy: 24,
  Cascader: 25,
  OptionStatus: 26, // 状态
  discuss: 27,
  DeniedField: 999, // no permission column
};
export const ReverSedNumFieldType = {
  "0": "NotSupport",
  "1": "Text",
  "2": "Number",
  "3": "SingleSelect",
  "4": "MultiSelect",
  "5": "DateTime",
  "6": "Attachment",
  "7": "Link",
  "8": "URL",
  "9": "Email",
  "10": "Phone",
  "11": "Checkbox",
  "12": "Rating",
  "13": "Member",
  "14": "LookUp",
  "16": "Formula",
  "17": "Currency",
  "18": "Percent",
  "19": "SingleText",
  "20": "AutoNumber",
  "21": "CreatedTime",
  "22": "LastModifiedTime",
  "23": "CreatedBy",
  "24": "LastModifiedBy",
  "25": "Cascader",
  "26": "OptionStatus",
  "27": "discuss",
  "999": "DeniedField",
};

// const reversedObj = Object.fromEntries(
//   Object.entries(NumFieldType).map(([key, value]) => [value, key])
// )

export function TableColumnRender(
  type: number,
  fieldId: string,
  fieldConfig: any,
  reader: boolean,
  writer: boolean,
  manager: boolean
) {
  // console.log(
  //   'TableColumnRender- type - fieldid - fieldMap',
  //   type,
  //   fieldId,
  //   fieldConfig
  // )
  switch (type) {
    case NumFieldType.Text:
      return MultipleText;

    case NumFieldType.SingleText:
      return SingleText;

    case NumFieldType.OptionStatus:
      return SingleSelect(fieldConfig);

    case NumFieldType.Attachment:
      return Attachment;
    case NumFieldType.DateTime:
      return SingleDateTime;
    case NumFieldType.SingleSelect:
      return SingleSelect(fieldConfig);

    case NumFieldType.MultiSelect:
      return MultiSelect(fieldConfig);

    case NumFieldType.Number:
      return SingleNumber;
    case NumFieldType.Link:
      return NetAddress;
    case NumFieldType.Email:
      return SingleText;
    case NumFieldType.Phone:
      return SingleText;
    case NumFieldType.Member:
      return MemberSelect(fieldConfig);
    case NumFieldType.discuss:
      return DiscussComment(fieldConfig, reader, writer, manager);
    default:
      return StringifyTextRender;
  }
}

const SingleText = (value: any, record: any) => {
  // console.log('SingleText=', value, 'record= ', record)
  if (typeof value === "string") {
    return <div>{value}</div>;
  }
  if (_.isArray(value)) {
    return (
      <div>
        {value && value.map && value.map((item: any) => <div>{item.text}</div>)}
      </div>
    );
  }
  return;
};
const MultipleText = (value: any, record: any) => {
  return (
    <div>
      {value && value.map && value.map((item: any) => <div>{item.text}</div>)}
    </div>
  );
};

const SingleSelect = (fieldConfig: any) => {
  const temp = _.get(fieldConfig, "property.options") || [];
  // console.log('temp', temp)
  if (temp.length === 0) {
    return () => {
      return <div></div>;
    };
  }

  const item0 = temp[0];
  let options: any = [];

  if (typeof item0 === "string") {
    options = temp.map((item: any) => ({
      label: item,
      value: item,
    }));
  }

  if (typeof item0 === "object") {
    options = temp.map((item: any) => ({
      label: item.name,
      value: item.id,
    }));
  }

  return (value: any, record: any) => {
    // console.log('SingleSelect', value, record)
    const text = _.find(options, { value: value })?.label || "";
    if (text) {
      return <Tag color="default">{text}</Tag>;
    } else {
      return <div></div>;
    }
  };
};
const MultiSelect = (fieldConfig: any) => {
  const list = _.get(fieldConfig, "property.options") || [];
  const options = list.map((item: any) => ({
    label: item.name,
    value: item.id,
  }));
  return (value: any, record: any) => {
    return (
      <div>
        {value &&
          value.map &&
          value.map((item: any) => <Tag color="cyan">{item}</Tag>)}
      </div>
    );
  };
};

const getFileName = (url: string) => {
  const file = url.split("/").pop();
  const fileName = file?.split("-")[1] || "";
  return fileName;
};

const Attachment = (value: any, record: any) => {
  if (value) {
    const suffix = value.substring(value.lastIndexOf(".") + 1).toLowerCase();

    if (suffix === "pdf" || suffix === "docx" || suffix === "doc") {
      return (
        <Link target="_blank" to={`/preview?url=${value}`} rel="noreferrer">
          {getFileName(value)}
        </Link>
      );
    } else {
      return (
        <a href={value} target="blank">
          {getFileName(value)}
        </a>
      );
    }
  }
};

const SingleNumber = (value: any, record: any) => {
  return <div> {(value && value) || "未输入"}</div>;
};

const SingleDateTime = (value: any, record: any) => {
  return <div> {(value && value) || "未输入"}</div>;
};
const NetAddress = (value: any, record: any) => {
  return (
    <div key={record.key}>
      {value && (
        <a href={`http://${value}`} target="_blank" rel="noreferrer">
          {value}
        </a>
      )}
    </div>
  );
};

const MemberSelect = (fieldConfig: any) => {
  const list = _.get(fieldConfig, "property.options") || [];

  return (value: any, record: any) => {
    if (typeof value === `undefined` || !(value instanceof Array)) {
      return <></>;
    }

    const memberList = value.map((item: string) => {
      return list.filter((m: any) => m.id === item)[0];
    });

    return (
      <div>
        {memberList &&
          memberList.map(
            (member: any) =>
              member && (
                <Tag
                  key={member.id}
                  color="blue"
                  icon={<Avatar src={member.avatar} />}
                >
                  {member.nickname}
                </Tag>
              )
          )}
      </div>
    );
  };
};

const DiscussModalWrap: React.FC<{
  children?: React.ReactNode;
  fieldId: string;
  record: any;
  reader: boolean;
  writer: boolean;
  manager: boolean;
}> = ({ fieldId, record, reader, writer, manager }) => {
  const [open, setOpen] = React.useState<boolean>(false);
  return (
    <div>
      <Button type="text" onClick={() => setOpen(true)}>
        查看评论
      </Button>

      <DiscussModal
        fieldId={fieldId}
        record={record}
        open={open}
        close={() => setOpen(false)}
        reader={reader}
        writer={writer}
        manager={manager}
      />
    </div>
  );
};

const DiscussComment = (
  fieldConfig: any,
  reader: boolean,
  writer: boolean,
  manager: boolean
) => {
  const fieldId = fieldConfig.id;
  return (value: any, record: any) => {
    return (
      <DiscussModalWrap
        fieldId={fieldId}
        record={record}
        reader={reader}
        writer={writer}
        manager={manager}
      />
    );
  };
};

const StringifyTextRender = (value: any, record: any) => {
  return <div>{JSON.stringify(value)}</div>;
};

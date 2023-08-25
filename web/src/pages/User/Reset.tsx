import React from "react";
import { MailOutlined } from "@ant-design/icons";
import { Button, Form, Input, Divider } from "antd";

type FieldType = {
  username?: string;
};

const Reset: React.FC = () => {
  const onFinish = (values: any) => {
    console.log("Success:", values);
  };

  const onFinishFailed = (errorInfo: any) => {
    console.log("Failed:", errorInfo);
  };

  return (
    <div
      style={{
        width: "100%",
        height: "100%",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <div
        style={{
          width: "800px",
          height: "500px",
          backgroundColor: "#fff",
          display: "flex",
          flexDirection: "column",
          justifyContent: "start",
          alignItems: "center",
        }}
      >
        <div style={{ marginTop: "32px" }}>
          <span style={{ fontSize: "32px", fontWeight: 500 }}>输入账号</span>
        </div>
        <Divider />
        <Form
          name="basic"
          layout="vertical"
          style={{ minWidth: 500 }}
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
        >
          <Form.Item<FieldType>
            label="请输入需要找回密码的账号"
            name="username"
            rules={[
              { required: true, message: "请输入账户!" },
              { type: "email", message: "请输入有效的邮箱地址." },
            ]}
          >
            <Input suffix={<MailOutlined />} />
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit" block>
              下一步
            </Button>
          </Form.Item>
        </Form>
      </div>
    </div>
  );
};

export default Reset;

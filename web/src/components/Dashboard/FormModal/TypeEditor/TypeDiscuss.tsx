import React from "react";
import moment from "moment";
import { Modal, List, Avatar, Button, Input, Form, Empty, message } from "antd";
import _ from "lodash";
import styled from "styled-components";
import { selectUser } from "../../../../store/globalSlice";
import { useAppDispatch, useAppSelector } from "../../../../store/hooks";
import {
  updateDSCells,
  UpdateDSCellsParams,
} from "../../../../api/apitable/ds-record";
import {
  TableColumnItem,
  selectCurFlowDstId,
  freshCurTableRows,
} from "../../../../store/workflowSlice";
import {
  SocketMsgType,
  sendWebSocketMsg,
} from "../../../../api/apitable/ws-msg";

const DiscussAvatar = styled(({ src, ...rest }) => (
  <div {...rest}>
    <Avatar src={src} />
  </div>
))`
  display: flex;
  justify-content: center;
  align-items: center;
  padding-right: 16px;
`;

interface DiscussModalProps {
  fieldId: string;
  record: any;
  dstId?: any;
  open: boolean;
  close: () => void;
  children?: React.ReactNode;
  reader: boolean;
  writer: boolean;
  manager: boolean;
}

interface CommentType {
  userId: string;
  username: string;
  avatar: string;
  content: string;
  create_time: string;
}

export const DiscussModal: React.FC<DiscussModalProps> = ({
  open,
  close,
  fieldId,
  record,
  reader,
  writer,
  manager,
}) => {
  const dispatch = useAppDispatch();
  const [form] = Form.useForm();
  const user = useAppSelector(selectUser);
  const curDstId = useAppSelector(selectCurFlowDstId);
  let comments = record[fieldId] || undefined;

  // console.log("record", record, "record fieldId", fieldId);

  const updateComments = async (comment: CommentType) => {
    const { recordId, id, ...rest } = record;
    comments = comments ? [comment, ...comments] : [comment];

    const fields = { ...rest, [fieldId]: comments };
    const params: UpdateDSCellsParams = {
      dstId: curDstId!,
      fieldKey: "id",
      records: [
        {
          recordId,
          fields,
        },
      ],
    };
    try {
      await updateDSCells(params);
      dispatch(freshCurTableRows(curDstId!));
      // 同步
      sendWebSocketMsg({
        user,
        dstId: curDstId!,
        type: SocketMsgType.SetRecords,
        recordId,
        row: fields,
      });
    } catch (error) {
      console.log(error);
    }
  };

  const onFinish = (values: any) => {
    const comment: CommentType = {
      userId: user.id,
      username: user.nickname,
      avatar: user.avatar,
      content: values.content,
      create_time: moment().format("YYYY-MM-DD HH:mm:ss"),
    };
    updateComments(comment);
    form.resetFields();
    // close();
  };

  const DiscussBar = (
    <Form form={form} onFinish={onFinish}>
      <Form.Item
        name="content"
        preserve={false}
        style={{ margin: 0, padding: 0 }}
        rules={[
          { required: true, message: "评论不能为空" },
          { type: "string", whitespace: true, message: "评论不能为空字符" },
        ]}
      >
        <div style={{ display: "flex", width: "100%" }}>
          <DiscussAvatar src={user.avatar} />
          <Input
            style={{ width: "10,0%", flex: 1 }}
            suffix={
              <Button type="text" htmlType="submit">
                评论
              </Button>
            }
          />
        </div>
      </Form.Item>
    </Form>
  );

  return (
    <Modal
      title={`${comments ? comments.length : 0}条评论`}
      open={open}
      onCancel={close}
      width={620}
      footer={writer || manager ? DiscussBar : null}
    >
      {comments ? (
        <div style={{ height: 450, overflow: "auto" }}>
          <List
            itemLayout="horizontal"
            dataSource={comments}
            renderItem={(item: CommentType, index: number) => (
              <List.Item key={`item-${index}`}>
                <List.Item.Meta
                  avatar={<Avatar src={item.avatar} />}
                  title={item.username}
                  description={
                    <>
                      {item.content}
                      <br />
                      {item.create_time}
                    </>
                  }
                />
              </List.Item>
            )}
          />
        </div>
      ) : (
        <div
          style={{
            height: 350,
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
          }}
        >
          <Empty description="暂无评论" />
        </div>
      )}
    </Modal>
  );
};

interface TypeDiscussProps {
  mode?: "multiple";
  cell: TableColumnItem;
  form: any;
  setForm: any;
}

const TypeDiscuss: React.FC<TypeDiscussProps> = (props: TypeDiscussProps) => {
  const { form, cell } = props;
  const [open, setOpen] = React.useState<boolean>(false);

  const handleOpen = () => {
    if (form && form.recordId) {
      setOpen(true);
    } else {
      message.error("创建工单完成后，才能发表评论！", 3);
    }
  };

  return (
    <>
      <Button type="text" onClick={handleOpen}>
        评论
      </Button>
      {form?.recordId && (
        <DiscussModal
          fieldId={cell.fieldId}
          record={form}
          open={open}
          close={() => setOpen(false)}
          reader={true}
          writer={true}
          manager={true}
        />
      )}
    </>
  );
};

export default TypeDiscuss;

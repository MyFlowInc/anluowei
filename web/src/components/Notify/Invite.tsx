import { Button, Dropdown, Modal } from "antd";
import styled from "styled-components";
import _ from "lodash";
import { getInviteList, userInvite, inviteUserList } from "../../api/apitable/ds-share";
import { useEffect, useState } from "react";
import { useAppSelector } from "../../store/hooks";
import { selectUser } from "../../store/globalSlice";

const UIListItem = styled.div`
  display: flex;
  width: fit-content;
  align-items: flex-end;
  background-color: #fff;
  border-radius: 4px;
  overflow: hidden;
  width: 100%;
  .container {
    /* 550 - 42  -42 */
    width: 100%;
    height: 68px;
    border-radius: 4px;
    display: flex;
    position: relative;
    justify-content: space-between;
    align-items: center;
  }

  .img-container {
    position: relative;
    .image {
      width: 42px;
      height: 42px;
    }
  }
  .left {
    display: flex;
    .word {
      margin-left: 20px;
      display: flex;
      flex-direction: column;
      justify-content: center;
    }
    .title {
      font-size: 14px;
      font-weight: bold;
      line-height: 24px;
      letter-spacing: 0em;
      color: #3d3d3d;
    }
    .content {
      font-size: 14px;
      font-weight: normal;
      line-height: 24px;
      letter-spacing: 0em;
      color: #666666;
    }
  }
`;
const ListItem = (props: any) => {
  const { className } = props;
  const { useInfo, editDstId,freshUserList } = props;
  
  const clickHandler = async () => {
    const temp = { dstId: editDstId, userId: useInfo.id + "" , enable:1};
    try {
      const res = await userInvite(temp);
      console.log("clickHandler", useInfo.id, editDstId, res);
      await freshUserList()
    } catch (error) {
      console.log("clickHandler", error);
    }
    return;
  };

  return (
    <UIListItem className={className}>
      <div className="container">
        <div className="left">
          <div className="img-container">
            <img src={useInfo.avatar} className="image" />
          </div>

          <div className="word">
            <div className="title">{useInfo.nickname} </div>
            <div className="content">{useInfo.phone} </div>
          </div>
        </div>
       {useInfo.isInvite  === 0 &&  <Button
          type="primary"
          onClick={clickHandler}
        >
          发送邀请
        </Button>}
        {useInfo.isInvite  === 1 &&  <Button
          type="primary"
          disabled={true}
        >
          已经邀请
        </Button>}
      </div>
    </UIListItem>
  );
};

const UIROOT = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 0px 0px 0px 18px;
  .list-header {
    margin-top: 12px;

    display: flex;
    flex-direction: row;
    justify-content: space-between;
  }
  .list-content {
    margin-top: 12px;
    width: 100%;
    height: 400px;
    overflow-y: auto;
  }
  .divider {
    border-bottom: 1px solid #e5e6eb;
  }
`;

const Invite = (props: any) => {
  const { className } = props;
  const { isInviteModalOpen, setIsInviteModalOpen, editDstId } = props;
  const [useList, setUseList] = useState<any[]>([]);
  const user = useAppSelector(selectUser);

  const showModal = () => {
    setIsInviteModalOpen(true);
  };

  const handleOk = () => {
    setIsInviteModalOpen(false);
  };

  const handleCancel = () => {
    setIsInviteModalOpen(false);
  };

  const fetchUserList = async () => {
 
    const res = await inviteUserList({dstId: editDstId});
    console.log(1111, res.data);

    if (_.get(res, "data")) {
      const temp = res.data.filter((item: any) => {
        return item.id !== user.id;
      });
      setUseList(temp);
    }
  };


  useEffect(() => {
    isInviteModalOpen && fetchUserList();
  }, [isInviteModalOpen]);

 

  return (
    <Modal
      title="邀请成员"
      open={isInviteModalOpen}
      width={566}
      onOk={handleOk}
      onCancel={handleCancel}
    >
      <UIROOT className={className}>
        <div className="list-header">
          <div>用户信息</div>
          {/* <div style={{ marginRight: '48px' }}>权限</div> */}
        </div>
        <div className="list-content">
          {useList.map((item, index) => {
            return (
              <ListItem key={index} useInfo={item} editDstId={editDstId} freshUserList={fetchUserList} />
            );
          })}
        </div>
      </UIROOT>
    </Modal>
  );
};

export default Invite;

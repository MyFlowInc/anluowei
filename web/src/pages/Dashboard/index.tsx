import { useEffect, useState } from "react";
import DashboardContainer from "../../components/Dashboard/DashboardContainer";
import { User, selectUser } from "../../store/globalSlice";
import { useAppSelector } from "../../store/hooks";
import { createWebSocket } from "../../api/apitable/room-server";
import { apitableDeveloperUserList } from "../../api/apitable/ds-share";
import {
  selectCurFlowDstId,
  selectWorkflowList,
} from "../../store/workflowSlice";
import _ from "lodash";
import type { WorkFlowInfo } from "../../store/workflowSlice";

const Page: React.FC = () => {
  const curDstId = useAppSelector(selectCurFlowDstId);
  const user = useAppSelector(selectUser);
  const flowList = useAppSelector(selectWorkflowList);

  const [isReader, setIsReader] = useState<boolean>(false);
  const [isWriter, setIsWriter] = useState<boolean>(false);
  const [isManager, setIsManager] = useState<boolean>(false);

  const fetchUserList = async () => {
    if (flowList.some((flow: WorkFlowInfo) => flow.dstId === curDstId)) {
      setIsReader(true);
      setIsWriter(true);
      setIsManager(true);
    } else {
      const res = await apitableDeveloperUserList(curDstId!);
      if (_.get(res, "data.record")) {
        const userList = res.data.record;
        setIsReader(
          userList.some(
            (item: any) =>
              item.userId === user.id &&
              (item.allowEdit || item.allowManage || item.allowWatch)
          )
        );
        setIsWriter(
          userList.some(
            (item: any) =>
              item.userId === user.id && (item.allowEdit || item.allowManage)
          )
        );
        setIsManager(
          userList.some(
            (item: any) => item.userId === user.id && item.allowManage
          )
        );
      }
    }
  };

  useEffect(() => {
    console.log("Dashboard 初始化");
    fetchUserList();
    if (window.ws) {
      window.ws.close(); // 主动close掉
      console.log("清除上一个client 连接...");
    }
    if (curDstId && user) {
      console.log("启用新的client 连接...");
      createWebSocket(user, curDstId);
    }
    return () => {
      console.log("Dashboard 销毁");
      if (window.ws) {
        window.ws.close(); // 主动close掉
        console.log("client 连接已关闭...");
      }
    };
  }, [curDstId]);

  return (
    <DashboardContainer
      reader={isReader}
      writer={isWriter}
      manager={isManager}
    />
  );
};

export default Page;

import {
  fetchInviteWorkflowList,
  fetchWorkflowList,
} from "../api/apitable/ds-table";
import { WorkFlowInfo } from "../store/workflowSlice";

//TODO: 最大支持 999张表
export async function fetchAllWorkflowList(archive: boolean = false) {
  try {
    const p1 = fetchWorkflowList({
      pageNum: 1,
      pageSize: 999,
      archive: archive ? 1 : 0,
    });
    const p2 = fetchInviteWorkflowList({
      pageNum: 1,
      pageSize: 999,
      archive: archive ? 1 : 0,
    });
    const [response, response2] = await Promise.all([p1, p2]);
    const data = response.data.record as WorkFlowInfo[];
    const data2 = response2.data.record as WorkFlowInfo[];
    const list = [...data, ...data2].map((item: WorkFlowInfo) => ({
      name: item.dstName,
      url: "/dashboard/workflow-view/" + item.dstId,
      ...item,
    }));
    return list;
  } catch (error) {
    throw error;
  }
}

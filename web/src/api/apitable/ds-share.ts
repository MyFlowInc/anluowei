import { apiCall } from '../../network'

interface InviteUserListParams {
  dstId: string
}
export function inviteUserList(params: InviteUserListParams) {
  return apiCall({
    url: 'api/sys/apitableDeveloper/user/list/all',
    method: 'get',
    params,
  })
}

interface UserInviteParams {
  dstId: string
  userId: string
}

// 邀请指定用户  兼容 通知面试官
export function userInvite(data: UserInviteParams) {
  return apiCall({
    url: 'api/sys/apitableDeveloper/save',
    method: 'post',
    data,
  })
}

// 获取全部邀请信息
export function getInviteList(params: any = {}) {
  return apiCall({
    url: 'api/sys/apitableInviteRecord/page',
    method: 'get',
    params: {
      pageNum: 1,
      pageSize: 999,
      ...params,
    },
  })
}

interface AgreeInviteParams {
  dstId: string
  userId: string
}
// 同意邀请
export function agreeInvite(data: AgreeInviteParams) {
  return apiCall({
    url: 'api/sys/apitableDeveloper/enable',
    method: 'post',
    data,
  })
}

// 当前表格协作者列表

export function apitableDeveloperUserList(dstId: string) {
  return apiCall({
    url: 'api/sys/apitableDeveloper/page',
    method: 'get',
    params: { dstId },
  })
}

interface EditInviteUserParams {
  allowEdit?: number
  allowManage?: number
  allowSave?: number
  allowWatch?: number
  id: string
}

// 修改协作者权限
export function editInviteUser(data: EditInviteUserParams) {
  return apiCall({
    url: 'api/sys/apitableDeveloper/edit',
    method: 'PUT',
    data,
  })
}

// 移除协作者
export function deleteInviteUser(params: { id: string }) {
  return apiCall({
    url: 'api/sys/apitableDeveloper/remove',
    method: 'delete',
    params,
  })
}

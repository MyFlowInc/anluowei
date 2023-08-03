import request from "../request";

const Api = {
  edit: "/cms/content/edit",
  info: "/cms/content/info",
  list: "/cms/content/list",
  page: "/cms/content/page",
  remove: "/cms/content/remove",
  save: "/cms/content/save",
};

/** 岗位列表 */
export const page = (data) => {
  return request.request({
    url: Api.page,
    params: data,
    method: "GET",
  });
};

/** 岗位列表 */
export const list = (data) => {
  return request.request({
    url: Api.list,
    params: data,
    method: "GET",
  });
};

/** 新增岗位 */
export const save = (data) => {
  return request.request({
    url: Api.save,
    data: data,
    method: "POST",
  });
};

/** 修改岗位 */
export const edit = (data) => {
  return request.request({
    url: Api.edit,
    data: data,
    method: "PUT",
  });
};

/** 删除岗位 */
export const remove = (data) => {
  return request.request({
    url: Api.remove,
    params: data,
    method: "DELETE",
  });
};

/** 批量删除 */
export const removeBatch = (data) => {
  return request.request({
    url: Api.removeBatch,
    params: data,
    method: "DELETE",
  });
};

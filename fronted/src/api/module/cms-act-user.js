import request from "../request";

const Api = {
  edit: "/sys/activityUser/edit",
  info: "/sys/activityUser/info",
  list: "/sys/activityUser/page",
  page: "/sys/activityUser/page",
  remove: "/sys/activityUser/remove",
  save: "/sys/activityUser/save",
  exportExcel: "/sys/activityUser/export",
};


export const exportExcel = data => {
  request.request({
      url: Api.exportExcel,
      params: data,
      method: 'GET',
      responseType: 'blob',
  }).then((result) => {
        if (!result) return;
        let url = window.URL.createObjectURL(new Blob([result]));
        let link = document.createElement('a');
        link.style.display = 'none';
        link.href = url;
        link.setAttribute('download', '活动人员excel.xlsx');
        document.body.appendChild(link);
        link.click()
        document.body.removeChild(link);
  })
}

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

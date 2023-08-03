package com.workflow.pro.modules.sys.param;

import com.workflow.pro.common.web.interceptor.enums.Scope;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SysRoleGiveRequest {

    private String roleId;

    private List<String> powerIds = new ArrayList<>();

    private Scope scope;

    private List<String> deptIds = new ArrayList<>();

}

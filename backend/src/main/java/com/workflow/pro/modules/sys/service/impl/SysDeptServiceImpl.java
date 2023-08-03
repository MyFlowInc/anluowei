package com.workflow.pro.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.workflow.pro.modules.sys.domain.SysDept;
import com.workflow.pro.modules.sys.repository.SysDeptRepository;
import com.workflow.pro.modules.sys.service.SysDeptService;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptRepository, SysDept> implements SysDeptService {

    @Resource
    private SysDeptRepository sysDeptRepository;

    @Override
    public List<SysDept> tree() {
        return toTree(sysDeptRepository.selectDept(), "0");
    }

    public List<SysDept> toTree(List<SysDept> sysDept, String parent) {
        List<SysDept> list = new ArrayList<>();
        for (SysDept dept : sysDept) {
            if (parent.equals(dept.getParent())) {
                dept.setChildren(toTree(sysDept, dept.getId()));
                list.add(dept);
            }
        }
        return list;
    }

    @Override
    public List<SysDept> treeAndChildren(String parent) {
        List<SysDept> ds = sysDeptRepository.selectDeptByParentId(parent);
        for (SysDept sd : ds) {
            treeAndChildren(sd, ds);
        }
        return ds;
    }

    private void treeAndChildren(SysDept sd, List<SysDept> ds) {
        List<SysDept> dss = sysDeptRepository.selectDeptByParentId(sd.getId());
        if (dss.size() > 0) {
            for (SysDept sdd : dss) {
                ds.add(sdd);
                treeAndChildren(sdd, ds);
            }
        } else {
            return;
        }
    }


    @Override
    public void syncDepartInfo(List<WxCpDepart> Departs) {
        for (WxCpDepart depart : Departs) {
            SysDept dept = new SysDept();
            dept.setId(depart.getId().toString());
            dept.setParent(depart.getParentId().toString());
            dept.setName(depart.getName());
            dept.setEnable(true);
            dept.setSort(depart.getOrder().intValue());
            dept.setAddress("Shang Hai");
            dept.setTenantId("1");
//            dept.setCreateBy("1");
//            dept.setCreateTime(LocalDateTime.now());
//            dept.setUpdateTime(LocalDateTime.now());
            System.out.println(dept);
            saveOrUpdate(dept);
        }
    }

}

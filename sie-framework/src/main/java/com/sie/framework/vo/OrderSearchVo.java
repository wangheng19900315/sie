package com.sie.framework.vo;

import com.sie.framework.base.HqlOperateVo;
import com.sie.util.StringUtil;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangheng on 2017/8/23.
 */
public class OrderSearchVo implements SearchToHqlOperate{

    private String orderCode;
    private String studentChineseName;
    private String projectCode;
    private Integer orderStatus;
    private Integer system;
    private Integer orderType;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getStudentChineseName() {
        return studentChineseName;
    }

    public void setStudentChineseName(String studentChineseName) {
        this.studentChineseName = studentChineseName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getSystem() {
        return system;
    }

    public void setSystem(Integer system) {
        this.system = system;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    @Override
    public List<HqlOperateVo> transToHqlOperateVo() {
        List<HqlOperateVo> operateVos = new ArrayList<>();
        if(StringUtil.isNotBlank(projectCode)){
            operateVos.add(new HqlOperateVo(" inner join   entity.orderDetailEntityList as detail","join",""));
            operateVos.add(new HqlOperateVo("detail.projectEntity.code","like",projectCode));
        }
        if(StringUtil.isNotBlank(orderCode)){
            operateVos.add(new HqlOperateVo("code","like",orderCode));
        }
        if(StringUtil.isNotBlank(studentChineseName)){
            operateVos.add(new HqlOperateVo("studentEntity.chineseName","like",studentChineseName));
        }

        if(orderStatus != null){
            operateVos.add(new HqlOperateVo("status","=",orderStatus.toString()));
        }
        if(system != null){
            operateVos.add(new HqlOperateVo("systemType","=",system.toString()));
        }
        if(orderType != null){
            operateVos.add(new HqlOperateVo("orderType","=",orderType.toString()));
        }

        return operateVos;
    }
}

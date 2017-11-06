package com.sie.framework.vo;

import com.sie.framework.base.HqlOperateVo;
import com.sie.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangheng on 2017/8/23.
 */
public class CrSearchVo implements SearchToHqlOperate{
    private String personName;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Override
    public List<HqlOperateVo> transToHqlOperateVo(){
        List<HqlOperateVo> operateVos = new ArrayList<>();
        if(StringUtil.isNotBlank(personName)){
            operateVos.add(new HqlOperateVo("personName","like",personName));
        }
        return operateVos;
    }
}

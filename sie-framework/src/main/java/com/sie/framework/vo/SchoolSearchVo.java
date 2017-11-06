package com.sie.framework.vo;

import com.sie.framework.base.HqlOperateVo;
import com.sie.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangheng on 2017/8/23.
 */
public class SchoolSearchVo implements SearchToHqlOperate{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<HqlOperateVo> transToHqlOperateVo(){
        List<HqlOperateVo> operateVos = new ArrayList<>();
        if(StringUtil.isNotBlank(name)){
            operateVos.add(new HqlOperateVo("name","like",name));
        }
        return operateVos;
    }
}

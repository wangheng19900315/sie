package com.sie.framework.vo;

import com.sie.framework.base.HqlOperateVo;
import com.sie.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangheng on 2017/8/23.
 */
public class ProjectSearchVo implements SearchToHqlOperate{
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public List<HqlOperateVo> transToHqlOperateVo(){
        List<HqlOperateVo> operateVos = new ArrayList<>();
        if(StringUtil.isNotBlank(code)){
            operateVos.add(new HqlOperateVo("code","like",code));
        }
        return operateVos;
    }
}

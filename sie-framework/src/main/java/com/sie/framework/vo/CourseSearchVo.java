package com.sie.framework.vo;

import com.sie.framework.base.HqlOperateVo;
import com.sie.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangheng on 2017/8/23.
 */
public class CourseSearchVo implements SearchToHqlOperate{
    private String chineseName;

    private String englishName;

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    @Override
    public List<HqlOperateVo> transToHqlOperateVo(){
        List<HqlOperateVo> operateVos = new ArrayList<>();
        if(StringUtil.isNotBlank(chineseName)){
            operateVos.add(new HqlOperateVo("chineseName","like",chineseName));
        }
        if(StringUtil.isNotBlank(englishName)){
            operateVos.add(new HqlOperateVo("englishName","like",englishName));
        }
        return operateVos;
    }
}

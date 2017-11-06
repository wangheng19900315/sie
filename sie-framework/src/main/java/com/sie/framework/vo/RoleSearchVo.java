package com.sie.framework.vo;

import com.sie.framework.base.HqlOperateVo;
import com.sie.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangheng on 2017/8/23.
 */
public class RoleSearchVo implements SearchToHqlOperate{
    private String sieChineseName;
    private String sieEnglishName;

    private String truChineseName;
    private String truEnglishName;

    public String getSieChineseName() {
        return sieChineseName;
    }

    public void setSieChineseName(String sieChineseName) {
        this.sieChineseName = sieChineseName;
    }

    public String getSieEnglishName() {
        return sieEnglishName;
    }

    public void setSieEnglishName(String sieEnglishName) {
        this.sieEnglishName = sieEnglishName;
    }

    public String getTruChineseName() {
        return truChineseName;
    }

    public void setTruChineseName(String truChineseName) {
        this.truChineseName = truChineseName;
    }

    public String getTruEnglishName() {
        return truEnglishName;
    }

    public void setTruEnglishName(String truEnglishName) {
        this.truEnglishName = truEnglishName;
    }

    @Override
    public List<HqlOperateVo> transToHqlOperateVo(){
        List<HqlOperateVo> operateVos = new ArrayList<>();
        if(StringUtil.isNotBlank(sieChineseName)){
            operateVos.add(new HqlOperateVo("sieChineseName","like",sieChineseName));
        }
        if(StringUtil.isNotBlank(sieEnglishName)){
            operateVos.add(new HqlOperateVo("sieEnglishName","like",sieEnglishName));
        }

        if(StringUtil.isNotBlank(truChineseName)){
            operateVos.add(new HqlOperateVo("truChineseName","like",truChineseName));
        }
        if(StringUtil.isNotBlank(truEnglishName)){
            operateVos.add(new HqlOperateVo("truEnglishName","like",truEnglishName));
        }
        return operateVos;
    }
}

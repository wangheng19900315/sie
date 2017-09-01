package com.sie.framework.vo;

import com.sie.framework.base.HqlOperateVo;
import com.sie.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangheng on 2017/8/23.
 */
public class StudentSearchVo implements SearchToHqlOperate{
    private String userID;
    private String chineseName;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    @Override
    public List<HqlOperateVo> transToHqlOperateVo(){
        //组装查询条件
        List<HqlOperateVo> operateVos = new ArrayList<>();
        if(StringUtils.isNotBlank(chineseName)){
            operateVos.add(new HqlOperateVo("chineseName","like",chineseName));
        }
        if(StringUtils.isNotBlank(userID)){
            operateVos.add(new HqlOperateVo("userID","like",userID));
        }
        return operateVos;
    }
}

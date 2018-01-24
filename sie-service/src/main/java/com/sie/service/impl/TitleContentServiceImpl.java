package com.sie.service.impl;

import com.sie.framework.dao.TitleContentDao;
import com.sie.framework.dao.TitleDao;
import com.sie.framework.entity.TitleContentEntity;
import com.sie.framework.entity.TitleEntity;
import com.sie.service.TitleContentService;
import com.sie.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("titleContentService")
public class TitleContentServiceImpl extends BaseServiceImpl<TitleContentEntity,Integer> implements TitleContentService {

    private TitleContentDao titleContentDao;

    @Autowired
    public TitleContentServiceImpl(TitleContentDao titleContentDao){
        super(titleContentDao);
        this.titleContentDao = titleContentDao;
    }

    @Override
    public TitleContentEntity getTitleContentByTitleId(Integer titleId) {
        String hql = "from TitleContentEntity where titleEntity.id = " + titleId;
        List<TitleContentEntity> entityList = titleContentDao.getList(hql);
        if(entityList != null && entityList.size() == 1){
            return entityList.get(0);
        }
        return null;
    }
}

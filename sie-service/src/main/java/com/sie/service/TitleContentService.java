package com.sie.service;

import com.sie.framework.entity.TitleContentEntity;


/**
 * Created by fuqp on 2018/1/21.
 */
public interface TitleContentService extends BaseService<TitleContentEntity, Integer> {

    //获取标题下的标题内容
    TitleContentEntity getTitleContentByTitleId(Integer titleId);

}

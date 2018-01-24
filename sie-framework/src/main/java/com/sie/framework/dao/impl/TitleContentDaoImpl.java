package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.TitleContentDao;
import com.sie.framework.entity.TitleContentEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("titleContentDao")
public class TitleContentDaoImpl extends GenericDaoImpl<TitleContentEntity, Integer> implements TitleContentDao {

}

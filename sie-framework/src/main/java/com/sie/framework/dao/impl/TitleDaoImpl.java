package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.TitleDao;
import com.sie.framework.entity.TitleEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("titleDao")
public class TitleDaoImpl extends GenericDaoImpl<TitleEntity, Integer> implements TitleDao {

}

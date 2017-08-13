package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.CrDao;
import com.sie.framework.entity.CrEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("crDao")
public class CrDaoImpl extends GenericDaoImpl<CrEntity, Integer> implements CrDao {

}

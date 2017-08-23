package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.DormitoryDao;
import com.sie.framework.entity.DormitoryEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("dormitoryDao")
public class DormitoryDaoImpl extends GenericDaoImpl<DormitoryEntity, Integer> implements DormitoryDao {

}

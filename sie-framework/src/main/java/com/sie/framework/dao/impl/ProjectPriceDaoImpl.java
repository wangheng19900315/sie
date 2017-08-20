package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.ProjectPriceDao;
import com.sie.framework.entity.ProjectPriceEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("projectPriceDao")
public class ProjectPriceDaoImpl extends GenericDaoImpl<ProjectPriceEntity, Integer> implements ProjectPriceDao {

}

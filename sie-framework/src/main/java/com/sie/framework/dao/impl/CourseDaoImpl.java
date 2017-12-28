package com.sie.framework.dao.impl;

import com.sie.framework.base.GenericDaoImpl;
import com.sie.framework.dao.CourseDao;
import com.sie.framework.entity.CourseEntity;
import com.sie.framework.type.SystemType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Repository("courseDao")
public class CourseDaoImpl extends GenericDaoImpl<CourseEntity, Integer> implements CourseDao {


    @Override
    public List<CourseEntity> getList(Integer projectId, Integer systemType) {
        String hql = "from CourseEntity where projectId="+projectId +" system="+systemType;
        return this.getList(hql);
    }

    @Override
    public String getNamesByIds(Integer systemType,String courseIds) {
        String hql = "from CourseEntity where id in ("+courseIds +")";
        List<CourseEntity>  courseEntities = this.getList(hql);
        String names = "";
        SystemType type = SystemType.valueOf(systemType);
        switch (type){
            case SIE:
                if(courseEntities.size() > 0){
                    for(CourseEntity courseEntity:courseEntities){
                        names += courseEntity.getSieEnglishName()+",";
                    }
                }
                break;
            case TRU:
                if(courseEntities.size() > 0){
                    for(CourseEntity courseEntity:courseEntities){
                        names += courseEntity.getTruEnglishName()+",";
                    }
                }
                break;
        }
        if(names.length() > 0){
            names = names.substring(0, names.length()-1);
        }
        return names;
    }
}

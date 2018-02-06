package com.sie.service.impl;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.dao.TitleDao;
import com.sie.framework.entity.TitleEntity;
import com.sie.service.TitleService;
import com.sie.service.bean.TitleBean;
import com.sie.service.bean.TreeNode;
import com.sie.util.NumberUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangheng on 2017/8/9.
 */
@Service("titleService")
public class TitleServiceImpl extends BaseServiceImpl<TitleEntity,Integer> implements TitleService {

    private TitleDao titleDao;

    @Autowired
    public TitleServiceImpl(TitleDao titleDao){
        super(titleDao);
        this.titleDao = titleDao;
    }

    @Override
    public List<TreeNode> getTitleTree() {
        String hql = "from TitleEntity where hdelete=0 order by sort asc";
        //获取所有的标题节点
        List<TitleEntity> titleEntities = titleDao.getList(hql);
        //遍历节点生成树
        List<TreeNode> treeNodes = new ArrayList<>();
        for(TitleEntity titleEntity : titleEntities){
            TreeNode treeNode = new TreeNode();
            treeNode.setId(titleEntity.getId());
            treeNode.setName(titleEntity.getName());
            if(titleEntity.getParentTitleEntity() != null){
                treeNode.setpId(titleEntity.getParentTitleEntity().getId());
            }

            treeNodes.add(treeNode);
        }
        return treeNodes;
    }

    @Override
    public TitleBean getTitleBean(Integer id) {
        TitleEntity entity = titleDao.getEntity(id);
        TitleBean bean = new TitleBean();
        try {
            BeanUtils.copyProperties(bean, entity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    public Integer saveTitle(TitleBean titleBean) {
        if(NumberUtil.isSignless(titleBean.getId())){
            TitleEntity oldTitleEntity = this.titleDao.getEntity(titleBean.getId());
            //设置值
            oldTitleEntity.setName(titleBean.getName());
            oldTitleEntity.setSubName(titleBean.getSubName());
            oldTitleEntity.setSort(titleBean.getSort());
            oldTitleEntity.setContent(titleBean.getContent());
            this.titleDao.updateEntity(oldTitleEntity);
            return oldTitleEntity.getId();
        }else{
            TitleEntity titleEntity = new TitleEntity();
            try {
                BeanUtils.copyProperties(titleEntity, titleBean);
                this.titleDao.createEntity(titleEntity);
                return titleEntity.getId();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

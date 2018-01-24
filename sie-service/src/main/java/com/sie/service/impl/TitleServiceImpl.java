package com.sie.service.impl;

import com.sie.framework.base.HqlOperateVo;
import com.sie.framework.dao.TitleDao;
import com.sie.framework.entity.TitleEntity;
import com.sie.service.TitleService;
import com.sie.service.bean.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //获取所有的标题节点
        List<TitleEntity> titleEntities = titleDao.getList(new ArrayList<HqlOperateVo>());
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
}

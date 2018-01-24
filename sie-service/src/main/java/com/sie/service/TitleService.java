package com.sie.service;

import com.sie.framework.entity.TitleEntity;
import com.sie.service.bean.TreeNode;

import java.util.List;


/**
 * Created by fuqp on 2018/1/21.
 */
public interface TitleService extends BaseService<TitleEntity, Integer> {

    List<TreeNode> getTitleTree();

}

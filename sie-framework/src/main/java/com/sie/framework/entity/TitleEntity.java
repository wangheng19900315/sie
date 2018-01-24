package com.sie.framework.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by x on 2017/8/12.
 */
@Entity
@Table(name = "t_title_info")
public class TitleEntity extends BaseEntity{


    private String name;//标题内容
    private String code;//标题编码
    private Integer sort;//标题排序顺序
    private TitleEntity parentTitleEntity;//父标题
    private String subName;//副标题内容

    private String content;//内容
    private String imageUrls;//图片url地址


    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Column(name = "sub_name")
    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "image_urls")
    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", nullable = true, columnDefinition = "COMMENT '父标题'")
    @Where(clause = "h_delete=0")
    public TitleEntity getParentTitleEntity() {
        return parentTitleEntity;
    }

    public void setParentTitleEntity(TitleEntity parentTitleEntity) {
        this.parentTitleEntity = parentTitleEntity;
    }

}

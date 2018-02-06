package com.sie.service.bean;

/**
 * Created by x on 2017/8/12.
 */
public class TitleBean extends BaseBean{
    private String name;//标题内容
    private String code;//标题编码
    private Integer sort;//标题排序顺序
    private String subName;//副标题内容

    private String content;//内容
    private String imageUrls;//图片url地址


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }
}
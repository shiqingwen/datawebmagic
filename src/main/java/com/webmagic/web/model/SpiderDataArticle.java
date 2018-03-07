package com.webmagic.web.model;

import java.util.Date;

/**
 * Created by sqw on 2017/7/24.
 */
public class SpiderDataArticle {

    private int id;
    private int schoolId;
    private int categoryId;
    private String url;
    private String title;
    private String content;
    private Date publishTime;
    private Date createTime;

    private String captureDesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCaptureDesc() {
        return captureDesc;
    }

    public void setCaptureDesc(String captureDesc) {
        this.captureDesc = captureDesc;
    }
}

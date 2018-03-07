package com.webmagic.web.model;

import java.util.Date;

/**
 * Created by cuidong on 2017/7/19 0019.
 * 爬虫配置
 */
public class SpiderDataConfig {
    private Integer id;
    private Integer schoolId;
    private String  schoolName;
    private Integer categoryId;
    private String categoryName;
    private String domain;
    private String pageUrl;
    private String rulePageListUrl;
    private String rulePageListTitle;
    private String ruleTitle;
    private String ruleTime;
    private String ruleContent;
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getRulePageListUrl() {
        return rulePageListUrl;
    }

    public void setRulePageListUrl(String rulePageListUrl) {
        this.rulePageListUrl = rulePageListUrl;
    }

    public String getRulePageListTile() {
        return rulePageListTitle;
    }

    public void setRulePageListTile(String rulePageListTile) {
        this.rulePageListTitle = rulePageListTile;
    }

    public String getRuleTile() {
        return ruleTitle;
    }

    public void setRuleTile(String ruleTile) {
        this.ruleTitle = ruleTile;
    }

    public String getRuleTime() {
        return ruleTime;
    }

    public void setRuleTime(String ruleTime) {
        this.ruleTime = ruleTime;
    }

    public String getRuleContent() {
        return ruleContent;
    }

    public void setRuleContent(String ruleContent) {
        this.ruleContent = ruleContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
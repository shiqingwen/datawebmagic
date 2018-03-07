package com.webmagic.web.model.vo;

/**
 * Created by sqw on 2017/7/19.
 */
public class SpiderDataConfigVO {
    private int schoolId;
    private int categoryId;
    private String domian;
    private String pageUrl;
    private String ruleListPageUrl;
    private String ruleListPageTitle;
    private String ruleTitle;
    private String ruleTime;
    private String ruleContent;

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

    public String getDomian() {
        return domian;
    }

    public void setDomian(String domian) {
        this.domian = domian;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getRuleListPageUrl() {
        return ruleListPageUrl;
    }

    public void setRuleListPageUrl(String ruleListPageUrl) {
        this.ruleListPageUrl = ruleListPageUrl;
    }

    public String getRuleListPageTitle() {
        return ruleListPageTitle;
    }

    public void setRuleListPageTitle(String ruleListPageTitle) {
        this.ruleListPageTitle = ruleListPageTitle;
    }

    public String getRuleTitle() {
        return ruleTitle;
    }

    public void setRuleTitle(String ruleTitle) {
        this.ruleTitle = ruleTitle;
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
}

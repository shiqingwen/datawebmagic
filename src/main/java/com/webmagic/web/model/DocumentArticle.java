package com.webmagic.web.model;

/**
 * @author sqw
 * 文章详情
 */
public class DocumentArticle {

    private Integer id;

    private Integer parse;

    private String template;

    private Integer bookmark;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParse() {
        return parse;
    }

    public void setParse(Integer parse) {
        this.parse = parse;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }

    public Integer getBookmark() {
        return bookmark;
    }

    public void setBookmark(Integer bookmark) {
        this.bookmark = bookmark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}
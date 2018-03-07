package com.webmagic.web.model;

/**
 * @author sqw
 * 文章
 */
public class Document {

    private Integer id;

    private Integer uid;

    private String name;

    private String title;

    private Integer categoryId;

    private String picture;

    private String description;

    private Integer root;

    private Integer pid;

    private Integer modelId;

    private Integer type;

    private Short position;

    private Integer linkId;

    private Integer coverId;

    private Integer display;

    private Integer deadline;

    private Integer attach;

    private Integer view;

    private Integer comment;

    private Integer extend;

    private Integer level;

    private Long createTime;

    private Integer updateTime;

    private Integer status;

    private Integer schoolId;

    private Integer praise;

    private String schoolLx;

    private String recruitLx;

    private String custom;

    private String captureDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getRoot() {
        return root;
    }

    public void setRoot(Integer root) {
        this.root = root;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }


    public Short getPosition() {
        return position;
    }

    public void setPosition(Short position) {
        this.position = position;
    }

    public Integer getLinkId() {
        return linkId;
    }

    public void setLinkId(Integer linkId) {
        this.linkId = linkId;
    }

    public Integer getCoverId() {
        return coverId;
    }

    public void setCoverId(Integer coverId) {
        this.coverId = coverId;
    }


    public Integer getDeadline() {
        return deadline;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }


    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public Integer getExtend() {
        return extend;
    }

    public void setExtend(Integer extend) {
        this.extend = extend;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Integer updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public Integer getAttach() {
        return attach;
    }

    public void setAttach(Integer attach) {
        this.attach = attach;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public String getSchoolLx() {
        return schoolLx;
    }

    public void setSchoolLx(String schoolLx) {
        this.schoolLx = schoolLx == null ? null : schoolLx.trim();
    }

    public String getRecruitLx() {
        return recruitLx;
    }

    public void setRecruitLx(String recruitLx) {
        this.recruitLx = recruitLx == null ? null : recruitLx.trim();
    }

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom == null ? null : custom.trim();
    }

    public String getCaptureDesc() {
        return captureDesc;
    }

    public void setCaptureDesc(String captureDesc) {
        this.captureDesc = captureDesc;
    }
}
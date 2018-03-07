package com.webmagic.web.model.vo;

/**
 * 学校相关
 * @author shiqingwen
 * @Date 2017/12/11 10:30
 */
public class SchoolVO {

    private String schoolName;


    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "SchoolVO{" +
                "schoolName='" + schoolName + '\'' +
                '}';
    }
}

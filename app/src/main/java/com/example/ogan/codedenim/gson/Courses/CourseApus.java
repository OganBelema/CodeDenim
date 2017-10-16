
package com.example.ogan.codedenim.gson.Courses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseApus {

    @SerializedName("CourseId")
    @Expose
    private Integer courseId;
    @SerializedName("CourseCode")
    @Expose
    private String courseCode;
    @SerializedName("CourseDescription")
    @Expose
    private String courseDescription;
    @SerializedName("FileLocation")
    @Expose
    private String fileLocation;
    @SerializedName("CourseName")
    @Expose
    private String courseName;
    @SerializedName("CourseCategoryId")
    @Expose
    private Integer courseCategoryId;
    @SerializedName("ExpectedTime")
    @Expose
    private Integer expectedTime;
    @SerializedName("CategoryName")
    @Expose
    private String categoryName;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(Integer courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }

    public Integer getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Integer expectedTime) {
        this.expectedTime = expectedTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}

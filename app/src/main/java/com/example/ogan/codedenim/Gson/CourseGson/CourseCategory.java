
package com.example.ogan.codedenim.Gson.CourseGson;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseCategory {

    @SerializedName("Courses")
    @Expose
    private List<Object> courses = null;
    @SerializedName("CourseCategoryId")
    @Expose
    private Integer courseCategoryId;
    @SerializedName("CategoryName")
    @Expose
    private String categoryName;

    public List<Object> getCourses() {
        return courses;
    }

    public void setCourses(List<Object> courses) {
        this.courses = courses;
    }

    public Integer getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(Integer courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}

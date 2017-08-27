
package com.example.ogan.codedenim.Gson.Categories;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseCategoryApi {

    @SerializedName("Courses")
    @Expose
    private List<Course> courses = null;
    @SerializedName("CourseCategoryId")
    @Expose
    private Integer courseCategoryId;
    @SerializedName("CategoryName")
    @Expose
    private String categoryName;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
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

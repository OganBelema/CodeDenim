
package com.example.ogan.codedenim.Gson.Categories;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoriesApus {

    @SerializedName("Courses")
    @Expose
    private ArrayList<Course> courses = null;
    @SerializedName("CourseCategoryId")
    @Expose
    private Integer courseCategoryId;
    @SerializedName("CategoryName")
    @Expose
    private String categoryName;

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
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


package com.example.ogan.codedenim.gson.Categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryApus {

    @SerializedName("CategoryName")
    @Expose
    private String categoryName;
    @SerializedName("CourseCategoryId")
    @Expose
    private Integer courseCategoryId;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(Integer courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }

}

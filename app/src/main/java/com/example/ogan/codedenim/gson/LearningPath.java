
package com.example.ogan.codedenim.gson;

import java.math.BigDecimal;

public class LearningPath {

    private int CourseCategoryId;
    private String CategoryName;
    private BigDecimal Amount;
    private String StudentType;
    private String CategoryDescription;
    private String ImageLocation;

    public int getCourseCategoryId() {
        return CourseCategoryId;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public String getStudentType() {
        return StudentType;
    }

    public String getCategoryDescription() {
        return CategoryDescription;
    }

    public String getImageLocation() {
        return ImageLocation;
    }
}

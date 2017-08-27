
package com.example.ogan.codedenim.Gson.Categories;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Course {

    @SerializedName("AssesmentQuestionAnswers")
    @Expose
    private List<Object> assesmentQuestionAnswers = null;
    @SerializedName("Enrollments")
    @Expose
    private List<Object> enrollments = null;
    @SerializedName("Instructors")
    @Expose
    private List<Object> instructors = null;
    @SerializedName("Modules")
    @Expose
    private List<Module> modules = null;
    @SerializedName("CourseId")
    @Expose
    private Integer courseId;
    @SerializedName("CourseCategoryId")
    @Expose
    private Integer courseCategoryId;
    @SerializedName("CourseCode")
    @Expose
    private String courseCode;
    @SerializedName("CourseName")
    @Expose
    private String courseName;
    @SerializedName("CourseDescription")
    @Expose
    private String courseDescription;
    @SerializedName("ExpectedTime")
    @Expose
    private Integer expectedTime;
    @SerializedName("DateAdded")
    @Expose
    private String dateAdded;
    @SerializedName("Points")
    @Expose
    private Integer points;

    public List<Object> getAssesmentQuestionAnswers() {
        return assesmentQuestionAnswers;
    }

    public void setAssesmentQuestionAnswers(List<Object> assesmentQuestionAnswers) {
        this.assesmentQuestionAnswers = assesmentQuestionAnswers;
    }

    public List<Object> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Object> enrollments) {
        this.enrollments = enrollments;
    }

    public List<Object> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Object> instructors) {
        this.instructors = instructors;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(Integer courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public Integer getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Integer expectedTime) {
        this.expectedTime = expectedTime;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

}


package com.example.ogan.codedenim.Gson.CourseGson;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoursesApi {

    @SerializedName("AssesmentQuestionAnswers")
    @Expose
    private List<Object> assesmentQuestionAnswers = null;
    @SerializedName("CourseCategory")
    @Expose
    private CourseCategory courseCategory;
    @SerializedName("Enrollments")
    @Expose
    private List<Object> enrollments = null;
    @SerializedName("Modules")
    @Expose
    private List<Module> modules = null;
    @SerializedName("StudentAssignedCourses")
    @Expose
    private List<Object> studentAssignedCourses = null;
    @SerializedName("TutorCourses")
    @Expose
    private List<Object> tutorCourses = null;
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
    @SerializedName("CourseImage")
    @Expose
    private Object courseImage;
    @SerializedName("FileLocation")
    @Expose
    private String fileLocation;
    @SerializedName("File")
    @Expose
    private Object file;

    public List<Object> getAssesmentQuestionAnswers() {
        return assesmentQuestionAnswers;
    }

    public void setAssesmentQuestionAnswers(List<Object> assesmentQuestionAnswers) {
        this.assesmentQuestionAnswers = assesmentQuestionAnswers;
    }

    public CourseCategory getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(CourseCategory courseCategory) {
        this.courseCategory = courseCategory;
    }

    public List<Object> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Object> enrollments) {
        this.enrollments = enrollments;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<Object> getStudentAssignedCourses() {
        return studentAssignedCourses;
    }

    public void setStudentAssignedCourses(List<Object> studentAssignedCourses) {
        this.studentAssignedCourses = studentAssignedCourses;
    }

    public List<Object> getTutorCourses() {
        return tutorCourses;
    }

    public void setTutorCourses(List<Object> tutorCourses) {
        this.tutorCourses = tutorCourses;
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

    public Object getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(Object courseImage) {
        this.courseImage = courseImage;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Object getFile() {
        return file;
    }

    public void setFile(Object file) {
        this.file = file;
    }

}

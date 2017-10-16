
package com.example.ogan.codedenim.gson.Module;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Module {

    @SerializedName("Course")
    @Expose
    private Course course;
    @SerializedName("Topics")
    @Expose
    private List<Object> topics = null;
    @SerializedName("ModuleId")
    @Expose
    private Integer moduleId;
    @SerializedName("CourseId")
    @Expose
    private Integer courseId;
    @SerializedName("ModuleName")
    @Expose
    private String moduleName;
    @SerializedName("ModuleDescription")
    @Expose
    private String moduleDescription;
    @SerializedName("ExpectedTime")
    @Expose
    private Integer expectedTime;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Object> getTopics() {
        return topics;
    }

    public void setTopics(List<Object> topics) {
        this.topics = topics;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleDescription() {
        return moduleDescription;
    }

    public void setModuleDescription(String moduleDescription) {
        this.moduleDescription = moduleDescription;
    }

    public Integer getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Integer expectedTime) {
        this.expectedTime = expectedTime;
    }

}

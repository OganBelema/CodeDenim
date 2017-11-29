
package com.example.ogan.codedenim.gson;

import java.util.List;

public class Module {

    private int ModuleId;
    private int CourseId;
    private String ModuleName;
    private String ModuleDescription;
    private int ExpectedTime;
    private Course Course;
    private List<Topic> Topics;

    public int getModuleId() {
        return ModuleId;
    }

    public int getCourseId() {
        return CourseId;
    }

    public String getModuleName() {
        return ModuleName;
    }

    public String getModuleDescription() {
        return ModuleDescription;
    }

    public int getExpectedTime() {
        return ExpectedTime;
    }

    public Course getCourse() {
        return Course;
    }

    public List<Topic> getTopics() {
        return Topics;
    }
}

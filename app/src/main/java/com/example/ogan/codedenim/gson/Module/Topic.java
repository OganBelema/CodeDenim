
package com.example.ogan.codedenim.gson.Module;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Topic {

    @SerializedName("MaterialUploads")
    @Expose
    private List<MaterialUpload> materialUploads = null;
    @SerializedName("Module")
    @Expose
    private Module module;
    @SerializedName("Posts")
    @Expose
    private List<Object> posts = null;
    @SerializedName("QuizRules")
    @Expose
    private List<Object> quizRules = null;
    @SerializedName("StudentQuestions")
    @Expose
    private List<Object> studentQuestions = null;
    @SerializedName("StudentTestLogs")
    @Expose
    private List<Object> studentTestLogs = null;
    @SerializedName("SubmitAssignments")
    @Expose
    private List<Object> submitAssignments = null;
    @SerializedName("TopicAssignments")
    @Expose
    private List<Object> topicAssignments = null;
    @SerializedName("TopicQuizzes")
    @Expose
    private List<Object> topicQuizzes = null;
    @SerializedName("TopicId")
    @Expose
    private Integer topicId;
    @SerializedName("ModuleId")
    @Expose
    private Integer moduleId;
    @SerializedName("TopicName")
    @Expose
    private String topicName;
    @SerializedName("ExpectedTime")
    @Expose
    private Integer expectedTime;

    public List<MaterialUpload> getMaterialUploads() {
        return materialUploads;
    }

    public void setMaterialUploads(List<MaterialUpload> materialUploads) {
        this.materialUploads = materialUploads;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Object> getPosts() {
        return posts;
    }

    public void setPosts(List<Object> posts) {
        this.posts = posts;
    }

    public List<Object> getQuizRules() {
        return quizRules;
    }

    public void setQuizRules(List<Object> quizRules) {
        this.quizRules = quizRules;
    }

    public List<Object> getStudentQuestions() {
        return studentQuestions;
    }

    public void setStudentQuestions(List<Object> studentQuestions) {
        this.studentQuestions = studentQuestions;
    }

    public List<Object> getStudentTestLogs() {
        return studentTestLogs;
    }

    public void setStudentTestLogs(List<Object> studentTestLogs) {
        this.studentTestLogs = studentTestLogs;
    }

    public List<Object> getSubmitAssignments() {
        return submitAssignments;
    }

    public void setSubmitAssignments(List<Object> submitAssignments) {
        this.submitAssignments = submitAssignments;
    }

    public List<Object> getTopicAssignments() {
        return topicAssignments;
    }

    public void setTopicAssignments(List<Object> topicAssignments) {
        this.topicAssignments = topicAssignments;
    }

    public List<Object> getTopicQuizzes() {
        return topicQuizzes;
    }

    public void setTopicQuizzes(List<Object> topicQuizzes) {
        this.topicQuizzes = topicQuizzes;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Integer getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Integer expectedTime) {
        this.expectedTime = expectedTime;
    }

}

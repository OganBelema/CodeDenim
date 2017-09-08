
package com.example.ogan.codedenim.Gson.Module;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaterialUpload {

    @SerializedName("MaterialByTutor")
    @Expose
    private Object materialByTutor;
    @SerializedName("TopicMaterialUploadId")
    @Expose
    private Integer topicMaterialUploadId;
    @SerializedName("TopicId")
    @Expose
    private Integer topicId;
    @SerializedName("Tutor")
    @Expose
    private String tutor;
    @SerializedName("FileType")
    @Expose
    private Integer fileType;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("FileLocation")
    @Expose
    private String fileLocation;
    @SerializedName("File")
    @Expose
    private Object file;

    public Object getMaterialByTutor() {
        return materialByTutor;
    }

    public void setMaterialByTutor(Object materialByTutor) {
        this.materialByTutor = materialByTutor;
    }

    public Integer getTopicMaterialUploadId() {
        return topicMaterialUploadId;
    }

    public void setTopicMaterialUploadId(Integer topicMaterialUploadId) {
        this.topicMaterialUploadId = topicMaterialUploadId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


package com.example.ogan.codedenim.Gson.Module;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModuleApus {

    @SerializedName("ModuleName")
    @Expose
    private String moduleName;
    @SerializedName("ModuleDescription")
    @Expose
    private String moduleDescription;
    @SerializedName("ExpectedTime")
    @Expose
    private Integer expectedTime;
    @SerializedName("Topics")
    @Expose
    private List<Topic> topics = null;

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

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

}

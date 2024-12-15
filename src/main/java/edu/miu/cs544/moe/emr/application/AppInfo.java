package edu.miu.cs544.moe.emr.application;

public class AppInfo {
    private String title;
    private String version;
    private String description;

    public AppInfo() {
    }

    public AppInfo(String title, String version, String description) {
        this.title = title;
        this.version = version;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

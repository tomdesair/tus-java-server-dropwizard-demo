package me.desair.dropwizard.views;

import java.util.List;

import io.dropwizard.views.View;

public class SubmissionCompletedView extends View {

    private String creator;

    private String description;

    private List<String> files;

    public SubmissionCompletedView(String creator, String description, List<String> uploads) {
        super("submission-completed.ftl");
        this.creator = creator;
        this.description = description;
        this.files = uploads;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }
}

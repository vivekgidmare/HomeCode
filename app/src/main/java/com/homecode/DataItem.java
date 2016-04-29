package com.homecode;

/**
 * Created by vivek on 29/04/16.
 */
public class DataItem {
    private String title;
    private String description;
    private String className;

    public DataItem(String title, String description, String className) {
        this.title = title;
        this.description = description;
        this.className = className;

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getClassName() {
        return className;
    }
}

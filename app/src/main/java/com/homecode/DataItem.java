package com.homecode;

import java.io.Serializable;

/**
 * Created by vivek on 29/04/16.
 */
public class DataItem implements Serializable{
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

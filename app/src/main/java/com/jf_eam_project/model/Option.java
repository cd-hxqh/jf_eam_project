package com.jf_eam_project.model;

/**
 * Created by think on 2015/12/28.
 */
public class Option extends Entity{
    private String name;
    private String description;
    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

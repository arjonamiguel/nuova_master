package com.nuova.dto;

public class ComboItemDTO {

    private String id;
    private String value;

    public ComboItemDTO() {

    }

    public ComboItemDTO(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

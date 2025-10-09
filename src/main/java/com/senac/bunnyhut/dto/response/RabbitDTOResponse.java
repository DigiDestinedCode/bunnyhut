package com.senac.bunnyhut.dto.response;

public class RabbitDTOResponse {
    private Integer id;
    private String name;
    private String description;
    private String default_img;
    private String alt_img;

    public Integer getid() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public String getdefault_img() {
        return default_img;
    }

    public void setdefault_img(String default_img) {
        this.default_img = default_img;
    }

    public String getalt_img() {
        return alt_img;
    }

    public void setalt_img(String alt_img) {
        this.alt_img = alt_img;
    }
}

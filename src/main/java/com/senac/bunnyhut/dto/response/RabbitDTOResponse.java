package com.senac.bunnyhut.dto.response;

public class RabbitDTOResponse {
    private Integer id;
    private String name;
    private String description;
    private String default_img;
    private String alt_img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDefaultImg() {
        return default_img;
    }

    public void setDefaultImg(String default_img) {
        this.default_img = default_img;
    }

    public String getAltImg() {
        return alt_img;
    }

    public void setAltImg(String alt_img) {
        this.alt_img = alt_img;
    }
}

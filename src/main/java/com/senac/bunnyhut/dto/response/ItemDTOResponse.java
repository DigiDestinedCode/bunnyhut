package com.senac.bunnyhut.dto.response;

public class ItemDTOResponse {
    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private String image_url;
    private Integer status;

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

    public Integer getprice() {
        return price;
    }

    public void setprice(Integer price) {
        this.price = price;
    }

    public String getimage_url() {
        return image_url;
    }

    public void setimage_url(String image_url) {
        this.image_url = image_url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

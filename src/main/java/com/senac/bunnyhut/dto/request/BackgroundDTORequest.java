package com.senac.bunnyhut.dto.request;

public class BackgroundDTORequest {
    private Integer itemId;
    private String background_theme;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getBackgroundTheme() {
        return background_theme;
    }

    public void setBackgroundTheme(String background_theme) {
        this.background_theme = background_theme;
    }
}

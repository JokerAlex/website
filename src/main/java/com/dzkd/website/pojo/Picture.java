package com.dzkd.website.pojo;

public class Picture {
    private Integer pictureId;

    private String pictureName;

    private String pictureCategory;

    private Integer acessId;

    public Picture(Integer pictureId, String pictureName, String pictureCategory, Integer acessId) {
        this.pictureId = pictureId;
        this.pictureName = pictureName;
        this.pictureCategory = pictureCategory;
        this.acessId = acessId;
    }

    public Picture() {
        super();
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName == null ? null : pictureName.trim();
    }

    public String getPictureCategory() {
        return pictureCategory;
    }

    public void setPictureCategory(String pictureCategory) {
        this.pictureCategory = pictureCategory == null ? null : pictureCategory.trim();
    }

    public Integer getAcessId() {
        return acessId;
    }

    public void setAcessId(Integer acessId) {
        this.acessId = acessId;
    }
}
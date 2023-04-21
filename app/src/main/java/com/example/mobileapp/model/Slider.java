package com.example.mobileapp.model;

public class Slider {
   int id;
   String img;
   String name;
   int loaisp_id;

    public Slider(int id, String img, String name, int loaisp_id) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.loaisp_id = loaisp_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLoaisp_id() {
        return loaisp_id;
    }

    public void setLoaisp_id(int loaisp_id) {
        this.loaisp_id = loaisp_id;
    }
}

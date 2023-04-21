package com.example.mobileapp.model;

public class Product {
    int id;
    String ten,anh;
    int gia_sp,gia_km;
    String mota,trangthai,quatang;
    int loaisp_id;

    public Product(int id, String ten,  int gia_sp, int gia_km, String quatang,String anh) {
        this.id = id;
        this.ten = ten;
        this.anh = anh;
        this.gia_sp = gia_sp;
        this.gia_km = gia_km;
        this.quatang = quatang;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public int getGia_sp() {
        return gia_sp;
    }

    public void setGia_sp(int gia_sp) {
        this.gia_sp = gia_sp;
    }

    public int getGia_km() {
        return gia_km;
    }

    public void setGia_km(int gia_km) {
        this.gia_km = gia_km;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getQuatang() {
        return quatang;
    }

    public void setQuatang(String quatang) {
        this.quatang = quatang;
    }

    public int getLoaisp_id() {
        return loaisp_id;
    }

    public void setLoaisp_id(int loaisp_id) {
        this.loaisp_id = loaisp_id;
    }
}

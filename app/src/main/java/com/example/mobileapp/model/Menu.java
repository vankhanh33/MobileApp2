package com.example.mobileapp.model;

public class Menu {
    String menu_ten;
    int menu_anh;

    public Menu(String menu_ten, int menu_anh) {
        this.menu_ten = menu_ten;
        this.menu_anh = menu_anh;
    }

    public String getMenu_ten() {
        return menu_ten;
    }

    public void setMenu_ten(String menu_ten) {
        this.menu_ten = menu_ten;
    }

    public int getMenu_anh() {
        return menu_anh;
    }

    public void setMenu_anh(int menu_anh) {
        this.menu_anh = menu_anh;
    }
}

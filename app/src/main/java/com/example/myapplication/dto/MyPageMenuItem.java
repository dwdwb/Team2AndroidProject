package com.example.myapplication.dto;

import android.net.Uri;

import com.example.myapplication.R;

import java.io.Serializable;

public class MyPageMenuItem implements Serializable {
    int icon;
    String menuText;

    public MyPageMenuItem() {
    }

    public MyPageMenuItem(int icon, String menuText) {
        this.icon = icon;
        this.menuText = menuText;
    }

    public int getIcon() {
        return icon;
    }

    public void setIconPath(int icon) {
        this.icon = icon;
    }

    public String getMenuText() {
        return menuText;
    }

    public void setMenuText(String menuText) {
        this.menuText = menuText;
    }
}

package com.chanmin.todoapp;

/**
 * Created by Administrator on 2017-01-10.
 */
class Item {
    private String title;
    private Boolean check;
    private String color;

    Item(String title, Boolean check, String color) {
        this.title = title;
        this.check = check;
        this.color = color;
    }

    String getTitle() {
        return title;
    }

    Boolean getCheck() {
        return check;
    }

    String getColor() {
        return color;
    }

}

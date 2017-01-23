package com.chanmin.todoapp;

/**
 * Created by Administrator on 2017-01-10.
 */
class Item {
    private String title;
    private String color;
    private Boolean check;

    Item(String title, String color) {
        this.title = title;
        this.color = color;
    }
    void setCheck(Boolean check){
        this.check = check;
    }
    String getTitle() {
        return title;
    }
    String getColor() {
        return color;
    }
    Boolean getCheck() { return check; }

}

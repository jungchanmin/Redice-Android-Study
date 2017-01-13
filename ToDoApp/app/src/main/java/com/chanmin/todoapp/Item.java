package com.chanmin.todoapp;

/**
 * Created by Administrator on 2017-01-10.
 */
class Item {
    private String title;
    private String color;

    Item(String title, String color) {
        this.title = title;
        this.color = color;
    }

    String getTitle() {
        return title;
    }

    String getColor() {
        return color;
    }

}

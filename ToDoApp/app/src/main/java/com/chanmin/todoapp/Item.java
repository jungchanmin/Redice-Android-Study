package com.chanmin.todoapp;

/**
 * Created by Administrator on 2017-01-10.
 */
class Item {
    private String title;
    private Boolean check;

    Item(String title, Boolean check) {
        this.title = title;
        this.check = check;
    }
    public String getTitle(){
        return title;
    }
    public Boolean getCheck(){
        return check;
    }

}

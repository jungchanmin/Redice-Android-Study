package com.chanmin.todoapp;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-01-19.
 */
public class SaveData {
    Boolean[] deleteItem;
    ArrayList<Item> listItem;

    SaveData(Boolean[] deleteItem, ArrayList<Item>listItem) {
        this.deleteItem = deleteItem;
        this.listItem = listItem;
    }
}

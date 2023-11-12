package com.example.manageboard.pojo;

import java.util.ArrayList;

public class ListBoards {
    private ArrayList<ListBoard> model;

    public ListBoards(){
        model = new ArrayList<>();
    }

    public ListBoards(ArrayList<ListBoard> model) {
        this.model = model;
    }

    public ArrayList<ListBoard> getModel() {
        return model;
    }

    public void setModel(ArrayList<ListBoard> model) {
        this.model = model;}
}

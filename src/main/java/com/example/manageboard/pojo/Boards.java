package com.example.manageboard.pojo;

import java.util.ArrayList;

public class Boards {
    private ArrayList<Board> model;

    public Boards(){
        model = new ArrayList<>();
    }

    public Boards(ArrayList<Board> model) {
        this.model = model;
    }

    public ArrayList<Board> getModel() {
        return model;
    }

    public void setModel(ArrayList<Board> model) {
        this.model = model;}
}

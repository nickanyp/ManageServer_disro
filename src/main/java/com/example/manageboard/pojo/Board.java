package com.example.manageboard.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "Board")
public class Board {
    @Id
    private String _id;
    private String board_name;
    private ArrayList<ListBoard> list;

    public Board(){}

    public Board(String _id,String board_name, ArrayList<ListBoard> list) {
        this._id = _id;
        this.board_name = board_name;
        this.list = list;
    }

}

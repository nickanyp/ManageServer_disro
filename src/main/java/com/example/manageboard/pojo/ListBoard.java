package com.example.manageboard.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListBoard {

    @Id
    private String _id;
    private String list_id;
    private String list_name;
    private String descriptions;

    public ListBoard() {}

    public ListBoard(String _id, String list_id, String list_name, String descriptions) {
        this._id = _id;
        this.list_id = list_id;
        this.list_name = list_name;
        this.descriptions = descriptions;
    }

}

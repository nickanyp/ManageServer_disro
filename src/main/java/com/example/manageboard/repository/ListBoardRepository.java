package com.example.manageboard.repository;

import com.example.manageboard.pojo.Board;
import com.example.manageboard.pojo.ListBoard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ListBoardRepository extends MongoRepository<ListBoard, String> {
    @Query(value="{list_name:'?0'}")
    public ListBoard findByName(String name);

    @Query(value="{list_id:'?0'}")
    public ListBoard findByID(String id);
}

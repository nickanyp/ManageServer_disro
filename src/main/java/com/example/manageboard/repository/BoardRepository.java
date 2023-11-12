package com.example.manageboard.repository;

import com.example.manageboard.pojo.Board;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends MongoRepository<Board, String> {
    @Query(value="{board_name:'?0'}")
    public Board findByName(String name);

    @Query(value="{_id:'?0'}")
    public Board findByID(String id);
}

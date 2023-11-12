package com.example.manageboard.repository;

import com.example.manageboard.pojo.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository repository;

    public BoardService(BoardRepository repository) {
        this.repository = repository;
    }

    public List<Board> retrieveBoards() {
        return repository.findAll();
    }

    public Board retrieveBoardByName(String name) {
        return repository.findByName(name);
    }

    public Board createBoard(Board board) {
        return repository.save(board);
    }

    public Board updateBoard(Board board) {
        return repository.save(board);
    }

    public boolean deleteBoard(Board board) {
        try {
            repository.delete(board);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public Board findByName(String name){
        return repository.findByName(name);
    }
    public Board findByID(String id){
        return repository.findByID(id);
    }

}

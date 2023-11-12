package com.example.manageboard.repository;

import com.example.manageboard.pojo.ListBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListBoardService {
    @Autowired
    private ListBoardRepository repository;

    public ListBoardService(ListBoardRepository repository) {
        this.repository = repository;
    }

    public List<ListBoard> retrieveListBoards() {
        return repository.findAll();
    }

    public ListBoard retrieveListBoardByName(String name) {
        return repository.findByName(name);
    }

    public ListBoard createListBoard(ListBoard listboard) {
        repository.insert(listboard);
        return listboard;
    }

    public ListBoard updateListBoard(ListBoard listboard) {
        return repository.save(listboard);
    }

    public boolean deleteListBoard(ListBoard listboard) {
        try {
            repository.delete(listboard);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public ListBoard findByID(String id){
        return repository.findByID(id);
    }
}

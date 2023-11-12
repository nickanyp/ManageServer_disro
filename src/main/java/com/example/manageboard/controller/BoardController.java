package com.example.manageboard.controller;

import com.example.manageboard.pojo.Board;
import com.example.manageboard.pojo.Boards;
import com.example.manageboard.pojo.ListBoard;
import com.example.manageboard.pojo.ListBoards;
import com.example.manageboard.repository.BoardService;
import com.example.manageboard.repository.ListBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;
    private Boards boards = new Boards();

    @Autowired
    private ListBoardService listBoardService;
    private ListBoards listBoards = new ListBoards();
    //list
    private ArrayList<ListBoard> lists = new ArrayList<ListBoard>();
    private ArrayList<ListBoard> startLists = new ArrayList<ListBoard>();
    private ArrayList<ListBoard> oldLists = new ArrayList<ListBoard>();
    private ArrayList<ListBoard> updatelists = new ArrayList<ListBoard>();

    @GetMapping("/boards")
    public List<Board> getBoards() {
        List<Board> boardsList = boardService.retrieveBoards();
        this.boards.setModel((ArrayList<Board>) boardsList);
        return this.boards.getModel();
    }

    @GetMapping("/board/{name}")
    public ResponseEntity<Board> getBoardByName(@PathVariable("name") String name) {
        Board board = boardService.retrieveBoardByName(name);
        return ResponseEntity.ok(board);
    }

    @PostMapping("/addBoard")
    public ResponseEntity<Board> createBoard(@RequestBody MultiValueMap<String, String> formdata) {
        Map<String, String> d = formdata.toSingleValueMap();
        Board board = boardService.createBoard(
                new Board(null, d.get("board_name"), this.startLists)
        );
        System.out.println(d);
        return ResponseEntity.ok(board);
    }

    @PostMapping("/addList/{boardId}")
    public String addList(@PathVariable("boardId") String boardId, @RequestBody MultiValueMap<String, String> formdata){
        Board board = boardService.findByID(boardId);
        if (board != null) {
            Map<String, String> d = formdata.toSingleValueMap();
            ListBoard listBoard = listBoardService.createListBoard(
                    new ListBoard(null, null, d.get("list_name"), d.get("descriptions"))
            );
            this.oldLists = board.getList();
            this.oldLists.add(listBoard);
            Board boardnew = boardService.updateBoard((
                    new Board(boardId, board.getBoard_name(), this.oldLists)
            ));
            System.out.println(boardnew);
            return "join success";
        }
        System.out.println(0);
        return "not found";
    };

    @PostMapping("/updateListBoard/{boardId}")
    public ResponseEntity<Board> updateListBoard(@PathVariable("boardId") String boardId, @RequestBody MultiValueMap<String, String> formdata){
        Board board = boardService.findByID(boardId);
        System.out.println(board);
        if (board != null){
            Map<String, String> d = formdata.toSingleValueMap();
            lists = board.getList();
            for (int i = 0 ; i < lists.size() ; i++) {
                if (lists.get(i).get_id().equals(d.get("id"))) {
                    ListBoard listnew = listBoardService.updateListBoard(
                            new ListBoard(lists.get(i).get_id(), lists.get(i).getList_id(), d.get("list_name"), d.get("descriptions"))
                    );
                    System.out.println(listnew);
                    updatelists = new ArrayList<>();
                    updatelists.add(listnew);
                    lists = updatelists;
                    Board board1 = boardService.updateBoard(
                            new Board(boardId, board.getBoard_name(), lists)
                    );
                    return ResponseEntity.ok(board1);
                }
            }
        }
        return ResponseEntity.ok(null);
    };

    @PostMapping("/deleteBoard/{boardId}")
    public ResponseEntity<Boolean> deleteBoard(@PathVariable("boardId") String boardId) {
        Board board = boardService.findByID(boardId);
        boolean status = boardService.deleteBoard(board);
        if (status) {
            boards.getModel().remove(board);
        }
        return ResponseEntity.ok(status);
    }

    @PostMapping("/deleteListBoard/{boardId}")
    public ResponseEntity<Board> deleteList(@PathVariable("boardId") String boardId,@RequestBody MultiValueMap<String, String> formdata){
        Board board = boardService.findByID(boardId);
        if(board != null){
            Map<String, String> d = formdata.toSingleValueMap();
            lists = board.getList();
            for (int i = 0; i < lists.size(); i++) {
                if (lists.get(i).get_id().equals(d.get("listId"))) {
                    lists.remove(i);
                }
            }
            System.out.println(lists);
            Board boardnew =boardService.updateBoard(
                    new Board(boardId, board.getBoard_name(), lists)
            );
            return ResponseEntity.ok(boardnew);
        }
        return ResponseEntity.ok(null);
    };

}

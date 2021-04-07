package hello.freeboard.domain;

import lombok.Data;

@Data
public class Board {
    private Long id;
    private String boardId;
    private String description;

    public Board(){

    }
    public Board(String boardId, String description){
        this.boardId = boardId;
        this.description = description;
    }
}

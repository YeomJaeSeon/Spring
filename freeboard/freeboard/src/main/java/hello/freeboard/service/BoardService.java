package hello.freeboard.service;

import hello.freeboard.domain.Board;

import java.util.List;

public interface BoardService {
    Board createBoard(Board board);
    List<Board> findAllBoards();
    Board findBoard(Long boardId);
    Board removeBoard(Long boardId);
    Board updateBoard(Board board, Long boardId);
}

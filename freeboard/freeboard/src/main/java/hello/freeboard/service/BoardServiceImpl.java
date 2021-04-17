package hello.freeboard.service;

import hello.freeboard.domain.Board;
import hello.freeboard.repository.BoardRepository;
import hello.freeboard.repository.BoardRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public Board createBoard(Board board){
        boardRepository.save(board);

        return board;
    }

    public List<Board> findAllBoards(){
        List<Board> boards = boardRepository.findAll();
        return boards;
    }

    public Board findBoard(Long boardId){
        Board selectedBoard = boardRepository.findById(boardId);
        return selectedBoard;
    }

    public Board removeBoard(Long boardId){
        Board deletedBoard = boardRepository.delete(boardId);

        return deletedBoard;
    }

    public Board updateBoard(Board board, Long boardId){
        Board updatedBoard = boardRepository.update(board, boardId);
        return updatedBoard;
    }

}

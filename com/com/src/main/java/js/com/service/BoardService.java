package js.com.service;

import js.com.member.Board;
import js.com.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void createBoard(Board board){
        boardRepository.save(board);
    }

    public Long getNextBoardSequence(){
        return boardRepository.findAll().size() + 1L;
    }

    public List<Board> getAllBoards(){
        List<Board> boards = boardRepository.findAll();
        return boards;
    }

    public Board showBoard(Long id){
        return boardRepository.findById(id);
    }

}

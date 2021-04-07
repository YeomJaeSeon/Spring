package hello.freeboard.repository;

import hello.freeboard.domain.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class BoardRepository {
    private static Map<Long, Board> store = new HashMap<>();
    private static Long sequence = 0L;

    public Board save(Board board){
        board.setId(++sequence);
        store.put(board.getId(), board);

        return board;
    }
    public List<Board> findAll(){
        return new ArrayList<>(store.values());
    }
    public Board findById(Long id){
        return store.get(id);
    }

    public Board update(Board updateParam, Long boardId){
        Board board = findById(boardId);
        board.setBoardId(updateParam.getBoardId());
        board.setDescription(updateParam.getDescription());

        return board; // 업데이트된 board
    }

    public Board delete(Long boardId){
        Board board = store.get(boardId); // 삭제할 게시판
        store.remove(boardId);
        return board; // 삭제한 board
    }
    public void clearStore(){
        store.clear();
    }
}

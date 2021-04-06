package js.com.repository;

import js.com.member.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BoardRepository {
    private static Long sequence = 0L;
    private Map<Long, Board> boardStore = new HashMap<>();

    // board create
    public void save(Board board){
        board.setId(++sequence);
        boardStore.put(sequence, board);
    }

    public List<Board> findAll(){
        return new ArrayList<>(boardStore.values());
    }
    public Board findById(Long id){
        return boardStore.get(id);
    }
}

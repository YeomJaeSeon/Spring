package hello.freeboard.repository;

import hello.freeboard.domain.Board;

import java.util.List;

public interface BoardRepository {
    Board save(Board board);
    List<Board> findAll();
    Board findById(Long id);
    Board update(Board updateParam, Long boardId);
    Board delete(Long boardId);
    void clearStore();
}

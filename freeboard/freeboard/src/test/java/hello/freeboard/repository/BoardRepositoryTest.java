package hello.freeboard.repository;


import hello.freeboard.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class BoardRepositoryTest {
    BoardRepository boardRepository = new BoardRepository();

    @AfterEach
    void afterEach(){
        boardRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Board board = new Board("board1", "아니 ㅠㅠ이건아니죠");
        //when
        Board savedBoard = boardRepository.save(board);
        //then
        assertThat(board).isEqualTo(savedBoard);
    }

    @Test
    void findAll(){
        //given
        Board board = new Board("board1", "ㅠㅠㅠ");
        Board board2 = new Board("board2", "ㅎㅎㅎㅎ");

        boardRepository.save(board);
        boardRepository.save(board2);
        //when
        List<Board> savedBoards = boardRepository.findAll();
        //then
        assertThat(savedBoards.size()).isEqualTo(2);
        assertThat(savedBoards).contains(board, board2);
    }

    @Test
    void update(){
        //given
        Board board = new Board("board1", "ㅎㅎㅎㅎ");

        Board savedBoard = boardRepository.save(board);

        Long updateId = savedBoard.getId();
        //when
        Board updateParam = new Board("newBoard1", "gggg");
        boardRepository.update(updateParam, updateId);

        //then
        assertThat(savedBoard.getBoardId()).isEqualTo(updateParam.getBoardId());
        assertThat(savedBoard.getDescription()).isEqualTo(updateParam.getDescription());
    }

    @Test
    void delete(){
        //given
        Board board = new Board("hello", "my name is ...");
        Board savedBoard = boardRepository.save(board);

        Long deleteId = savedBoard.getId();
        //when
        boardRepository.delete(deleteId);

        List<Board> all = boardRepository.findAll();
        //then
        assertThat(all.size()).isEqualTo(0);

    }


}

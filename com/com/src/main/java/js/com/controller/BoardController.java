package js.com.controller;

import js.com.member.Board;
import js.com.service.BoardService;
import js.com.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public String responseBoard(Model model){
        log.info("board 등장!");
        model.addAttribute("nextBoardId", boardService.getNextBoardSequence());

        List<Board> boards = boardService.getAllBoards();

        model.addAttribute("boards", boards);
        return "board/board";
    }

    @GetMapping("/board/{boardId}")
    public String responseCreateBoard(){
        return "board/create-board";
    }

    @PostMapping("/board")
    public String createBoard(
            @ModelAttribute Board board
            ){
        boardService.createBoard(board);
        return "redirect:/board";
    }

    @GetMapping("/existedBoard/{boardId}")
    public String responseShowBoard(@PathVariable String boardId, Model model){
        long boardIdLong = Long.parseLong(boardId);
        Board board = boardService.showBoard(boardIdLong);

        model.addAttribute("board", board);
        return "board/show-board";
    }
}

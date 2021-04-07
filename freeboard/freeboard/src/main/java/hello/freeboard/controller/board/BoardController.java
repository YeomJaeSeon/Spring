package hello.freeboard.controller.board;

import hello.freeboard.domain.Board;
import hello.freeboard.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    /**
     * board페이지 GET
     * board/new-form GET
     * board POST - 글작성
     * board/{boardId} - 게시판 조회(하나) GET
     * board/{boardId} - 게시판 삭제 POST 후 board페이지 GET(1번쨰 컨트롤러)로 리다이렉트
     * board/change/{boardId} - 게시판 수정 페이지로 조회 GET
     * board/change - 게시판 수정 요청 POST 요청후 board페이지로 리다이렉트 (PRG)
     */
    @GetMapping("/board")
    public String responseBoardPage(Model model){
        model.addAttribute("boards", boardService.findAllBoards());
        return "board/index";
    }

    @GetMapping("/board/new-form")
    public String responseBoardNewFormPage(){
        return "board/new-form";
    }

    @PostMapping("/board/new-form")
    public String createBoardAndResponseBoardPage(@ModelAttribute Board board, Model model){
        boardService.createBoard(board);
        return "redirect:/board";
    }

    @GetMapping("/board/{boardId}")
    public String responseSelectedBoard(@PathVariable String boardId, Model model){
        Long longBoardId = Long.parseLong(boardId);
        Board selectedBoard = boardService.findBoard(longBoardId);
        model.addAttribute("board", selectedBoard);

        return "board/board";
    }

    @PostMapping("/board/{boardId}")
    public String requestDeleteBoard(@PathVariable String boardId){
        Long longBoardId = Long.parseLong(boardId);
        boardService.removeBoard(longBoardId);

        return "redirect:/board";
    }

    @GetMapping("/board/change/{boardId}")
    public String responseBoardChangePage(@PathVariable String boardId, Model model){
        Long longBoardId = Long.parseLong(boardId);
        Board board = boardService.findBoard(longBoardId);
        model.addAttribute("board", board);

        return "board/change-form";
    }
    @PostMapping("/board/change/{boardId}")
    public String boardChangeRequest(@ModelAttribute Board board, @PathVariable String boardId){
        Long longBoardId = Long.parseLong(boardId);

        boardService.updateBoard(board, longBoardId);

        return "redirect:/board";
    }
}

package hello.freeboard.controller.board;

import hello.freeboard.domain.Board;
import hello.freeboard.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/boards")
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
    @GetMapping
    public String boards(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes){
        HttpSession session = request.getSession();
        Object loginSession = session.getAttribute("login");
        if(loginSession == null){
            redirectAttributes.addAttribute("status", false);
            return "redirect:/login";
        }
        model.addAttribute("boards", boardService.findAllBoards());
        model.addAttribute("user", loginSession);
        return "board/boards";
    }

    @GetMapping("/new-form")
    public String addForm(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Object loginSession = session.getAttribute("login");
        if(loginSession == null){
            return "redirect:/login";
        }

        model.addAttribute("user", loginSession);
        return "board/new-form";
    }

    @PostMapping("/new-form")
    public String add(@ModelAttribute Board board, Model model){
        if(board.getBoardId() == ""){
            model.addAttribute("error", "제목이 비었습니다.");
            return "board/new-form";
        }
        boardService.createBoard(board);
        return "redirect:/boards";
    }

    @GetMapping("/{boardId}")
    public String board(HttpServletRequest request, @PathVariable Long boardId, Model model){
        HttpSession session = request.getSession();
        Object loginSession = session.getAttribute("login");
        if(loginSession == null){
            return "redirect:/login";
        }

        Board selectedBoard = boardService.findBoard(boardId);
        model.addAttribute("board", selectedBoard);
        model.addAttribute("user", loginSession); // 로그인정보
        //로그아웃때 필요함.

        return "board/board";
    }
    @GetMapping("/{boardId}/delete")
    public String delete(@PathVariable Long boardId){
        boardService.removeBoard(boardId);

        return "redirect:/boards";
    }

    @GetMapping("/{boardId}/edit")
    public String responseBoardChangePage(HttpServletRequest request, @PathVariable Long boardId, Model model){
        HttpSession session = request.getSession();
        Object loginSession = session.getAttribute("login");
        if(loginSession == null){
            return "redirect:/login";
        }

        Board board = boardService.findBoard(boardId);
        model.addAttribute("board", board);
        model.addAttribute("user", loginSession);
        return "board/edit-form";
    }

    @PostMapping("/{boardId}/edit")
    public String boardChangeRequest(@ModelAttribute Board board, @PathVariable Long boardId){
        boardService.updateBoard(board, boardId);

        return "redirect:/boards";
    }
}

package ezen.toyBoard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ezen.toyBoard.domain.BoardVO;
import ezen.toyBoard.service.BoardService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/brd")
public class BoardController {

	private final BoardService bsv;

	@Autowired
	public BoardController(BoardService bsv) {
		this.bsv = bsv;
	}
	
	@GetMapping("/register")
	public String registerForm() {
		return "board/register";
	}
	
	@PostMapping("/insert")
	public String boardAdd(@RequestParam String title,
			@RequestParam String writer,
			@RequestParam String content,
			Model model) {
		BoardVO bvo = new BoardVO();
		bvo.setTitle(title);
		bvo.setWriter(writer);
		bvo.setContent(content);
		log.info("register check 1");
		log.info("bvo = {} ", bvo);
		bsv.register(bvo);
		model.addAttribute("bvo",bvo);
		log.info("register check 4");
		

		return "index";
	}
	
	
	
}
